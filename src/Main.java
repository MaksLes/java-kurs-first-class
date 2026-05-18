import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Lista typu bazowego - przechowuje różne podtypy
        List<Product> products = new ArrayList<>();

        products.add(new Electronics("Laptop Dell XPS", 5499.99, "Komputery", 24));
        products.add(new Electronics("Słuchawki AirPods Max", 2999.99, "Audio", 12));
        products.add(new FoodProduct("Mąka Basia", 4.50, "Pieczywo",
                LocalDate.of(2025, 6, 1)));
        products.add(new Product("Zeszyt A4", 11.99, "Artykuły biurowe"));
        products.add(new Product("Zestaw płyt CD/DVD", 47.99, "Artykuły biurowe"));

        System.out.println("=== Lista Produktów ===\n");

        //Pętla z polimorfizmem - każdy p.getDescription() wywołuje właściwą metodę
        for (Product p : products) {
            System.out.println(p.getDescription());
        }
    }
}
