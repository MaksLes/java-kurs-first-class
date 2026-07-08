package FlatMapAndReduce.ecommerce;

import java.util.DoubleSummaryStatistics;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Product> products = List.of(
          new Product("Klawiatura mechaniczna", 249.99, true),
                new Product("Myszka bezprzewodowa", 89.50, true),
                new Product("Monitor Ultrawide", 1899.00, true),
                new Product("Karta Graficzna RTX 420", 3499.00, false),
                new Product("Podkładka pod mysz", 29.90, true)
        );

        DoubleSummaryStatistics priceStats = products.stream()
                .mapToDouble(Product::price)
                .summaryStatistics();

        System.out.println("Raport Cenowy");
        System.out.println("Najtańszy produkt: " + priceStats.getMin() + " zł");
        System.out.println("Najdroższy produkt: " + priceStats.getMax() + " zł");
        System.out.println("Średnia cena: " + priceStats.getAverage() + " zł\n");

        boolean allInStock = products.stream().allMatch(Product::inStock);
        System.out.println("Czy wszystkie produkty dostępne: " + allInStock);

        boolean hasPremiumProduct = products.stream().anyMatch(p -> p.price() > 1000.0);
        System.out.println("Czy produkt jest premium (>1000 zł): " +  hasPremiumProduct);
    }
}
