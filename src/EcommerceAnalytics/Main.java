package EcommerceAnalytics;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Product> products = ProductRepository.getAll();

        query1_topBetsellers(products);
        query2_premiumCategory(products);
        query3_lowStockReport(products);
        query4_availableCountPerCategory(products);
        query5_financialReportPerCategory(products);
        query6_affordableHighRated(products);
        query7_validateAllRated(products);
        query8_mostExpensiveProduct(products);
        query9_partitionByPriceSegment(products);
        query10_textualReport(products);
    }

    //1. Top 5 bestsellerów według przychodu
    private static void query1_topBetsellers(List<Product> products) {
        System.out.println("1) Top 5 bestsellerów (przychód = price * sold)");

        //Comparator.comparingDouble(Product::revenue).reversed() - sortowanie malejące po wyliczonej wartości nie po
        //polu bezpośrednio przechowywanym w rekordzie
        List<Product> top5 = products.stream()
                .sorted(Comparator.comparingDouble(Product::revenue).reversed())
                .limit(5)
                .toList();

        top5.forEach(p -> System.out.printf(" %-20s | przychód: %.2f zł%n", p.name(), p.revenue()));
        System.out.println();
    }

    //2. Kategoria z najwyższą średnią oceną
    private static void query2_premiumCategory(List<Product> products) {
        System.out.println("2) Kategoria premium (najwyższa ocena)");

        //Krok A: groupingBy tworzy Map<String, Double> - kategoria -> średnia ocena
        Map<String, Double> avgRatingByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.averagingDouble(Product::rating)));

        //krok B: szukamy wpisu (Entry) o maksymalnej wartości w mapie
        Optional<Map.Entry<String, Double>> bestCategory = avgRatingByCategory.entrySet().stream().max(Map.Entry.comparingByValue());

        bestCategory.ifPresent(entry ->
                System.out.printf(" Kategoria: %s | średnia ocena: %.3f%n%n", entry.getKey(), entry.getValue()));
    }

    //3. Raport magazynowy - niski stan
    public static void query3_lowStockReport(List<Product> products) {
        System.out.println("3) Raport magazynowy (stock < 10, sortowanie rosnące)");

        List<Product> lowStock = products.stream()
                .filter(p -> p.stock() < 10)
                .sorted(Comparator.comparingInt(Product::stock))
                .toList();

        lowStock.forEach(p -> System.out.printf(" %-20s | stock: %d%n", p.name(), p.stock()));
        System.out.println();
    }

    //4. Liczba dostępnych produktów per kategoria
    private static void query4_availableCountPerCategory(List<Product> products) {
        System.out.println("4) Dostępne produkty (stock > 0) per kategoria");

        //Collectors.filtering - downstream collector filtrujący wewnątrz grupy, w przeciwieństwie do filter() przed
        // groupingBy, który usunąłby produkt z całego raportu, a nie tylko z konkretnego zliczenia
        Map<String, Long> availableCount = products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.filtering(p -> p.stock() > 0, Collectors.counting())));

        availableCount.forEach((category, count) -> System.out.println(" " + category + ": " + count + " dostępnych"));
        System.out.println();
    }

    //5. Raport finansowy (przychód + średnia cena) per kategoria
    private static void query5_financialReportPerCategory(List<Product> products) {
        System.out.println("5) Raport finansowy per kategoria");

        //Collectors.teeing - jedno przejście po strumieniu, daw równoległe kolektory (suma przychodu + średnia cena),
        //połączone funkcją merge w jeden obiekt wynikowy CategoryStats
        Map<String, CategoryStats> financials = products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.teeing(
                        Collectors.summingDouble(Product::revenue),
                        Collectors.averagingDouble(Product::price),
                        CategoryStats::new)));

        financials.forEach((category, stats) -> System.out.printf(" %-12s | przychód: %10.2f | śr. cena: %8.2f zł%n",
                category, stats.totalRevenue(), stats.avaragePrice()));
        System.out.println();
    }

    //6. Okazje - wysoka cena + niska cena
    private static void query6_affordableHighRated(List<Product> products) {
        System.out.println("6) Okazje (rating >= 4.5 AND price < 500)");

        List<Product> deals = products.stream()
                .filter(p -> p.rating() >= 4.5 && p.price() < 500)
                .sorted(Comparator.comparingDouble(Product::price))
                .toList();

        deals.forEach(p -> System.out.printf(" %-20s | %.2f zł | ocena: %.1f%n", p.name(), p.price(), p.rating()));
        System.out.println();
    }

    //7. Walidacja jakości
    private static void  query7_validateAllRated(List<Product> products) {
        System.out.println("7) Walidacja: wszytkie produkty mają ocenę > 0");

        //allMatch to operacja short-circuit - zatrzyma się na pierwszym produkcie niespełniającym warunku,
        //nie przechodząc całej listy
        boolean allRated = products.stream().allMatch(p -> p.rating() > 0);

        System.out.println(" Wynik " + allRated + "\n");
    }

    //8. Najdroższy produkt
    private static void query8_mostExpensiveProduct(List<Product> products) {
        System.out.println("8) Najdroższy produkt w sklepie");

        //max() zwraca Optional<Product> - pusta lista nie rzuca wyjątku, lecz sygnalizuje kontrolowany brak wyniku
        Optional<Product> mostExpensive = products.stream()
                .max(Comparator.comparingDouble(Product::price));

        mostExpensive.ifPresentOrElse(
                p -> System.out.printf(" %s | cena: %.2f zł%n%n", p.name(), p.price()),
                () -> System.out.println(" Brak produktów w sklepie.\n")
        );
    }

    //9. Segmentacja premium / standard
    private static void query9_partitionByPriceSegment(List<Product> products) {
        System.out.println("9) Segmentacja: Premium (>1000zł) vs Standard");

        //partitioningBy dzieli strumień na dokładnie dwie grupy (true/false) w przeciwieństwie do groupingBy, który mógłby
        //dać dowolną liczbę grup
        Map<Boolean, Long> segments = products.stream()
                .collect(Collectors.partitioningBy(p -> p.price() > 1000, Collectors.counting()));

        System.out.println(" Premium (>1000 zł): " + segments.get(true) + " produktów");
        System.out.println(" Standard (<=1000 zł): " + segments.get(false) + " produktów\n");
    }

    //10. Raport tekstowy - top produkt per kategoria
    private static void query10_textualReport(List<Product> products) {
        System.out.println("10) Raport tekstowy - lider oceny w każdej kategorii");

        //groupingBy + maxBy dla każdej kategorii wybieramy jeden produkt o najwyższej ocenie. Wynikiem jest Optional<Product>
        // bo maxBy teoretycznie mogłby nie znaleźć elementu w pustej grupie
        Map<String, Optional<Product>> topRatedPerCategory = products.stream()
                .collect(Collectors.groupingBy(Product::category, Collectors.maxBy(Comparator.comparingDouble(Product::rating))));

        topRatedPerCategory.forEach((category, optionalProduct) ->
                optionalProduct.ifPresent(p ->
                        System.out.printf(" W kategorii %-12s najwyżej oceniany jest \"%s\" (ocena: %.1f)%n",
                                category, p.name(), p.rating())));
    }
}
