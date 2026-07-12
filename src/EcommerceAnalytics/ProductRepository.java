package EcommerceAnalytics;

import java.util.List;

public class ProductRepository {

    public static List<Product> getAll(){
        return List.of(

                //ELEKTRONIKA
                new Product("E1", "Laptop MacBook Pro 15", "Elektronika", 4200.00, 5, 40, 4.7),
                new Product("E2", "iPhone 17 Air", "Elektronika", 2200.00, 15, 120, 4.3),
                new Product("E3", "Tablet iPad Air", "Elektronika", 1500.00, 8, 60, 4.6),
                new Product("E4", "AirPods 3", "Elektronika", 350.00, 50, 200, 4.2),

                //KSIĄŻKI
                new Product("K1", "Ostry Cień Mgły", "Książki", 39.90, 100, 300, 4.8),
                new Product("K2", "Książka kucharska", "Książki", 59.90, 40, 90, 4.4),
                new Product("K3", "Encyklopedia PWN", "Książki", 129.90, 0, 20, 4.1), //wyprzedana
                new Product("K4", "Komiks Batman", "Książki", 49.90, 60, 150, 4.6),

                //AGD
                new Product("A1", "Lodówka Samsung", "AGD", 3200.00, 3, 15, 4.5),
                new Product("A2", "Pralka Bosch", "AGD", 2100.00, 6, 25, 4.3),
                new Product("A3", "Mikrofalówka LG", "AGD", 450.00, 20, 70, 4.0),
                new Product("A4", "Odkurzacz Dyson", "AGD", 1800.00, 4, 30, 4.7),

                //SPORT
                new Product("S1", "Rower górski", "Sport", 2500.00, 7, 18, 4.6),
                new Product("S2", "Hantle 20kg", "Sport", 199.00, 25, 80, 4.2),
                new Product("S3", "Mata do jogi", "Sport", 89.00, 60, 200, 4.4),
                new Product("S4", "Piłka siatkowa", "Sport", 79.00, 45, 90, 4.1),

                //ZABAWKI
                new Product("Z1", "Klocki LEGO", "Zabawki", 349.00, 30, 110, 4.8),
                new Product("Z2", "Lalka interaktywna", "Zabawki", 199.00, 12, 45, 4.0),
                new Product("Z3", "Autko RC", "Zabawki", 259.00, 2, 15, 4.3),
                new Product("Z4", "Puzzle 1000 elem.", "Zabawki", 49.00, 80, 130, 4.5)

        );
    }
}
