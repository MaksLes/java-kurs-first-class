public class Product {
    private String name;
    private double price;
    private String category;

    //Konstruktor
    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    //Gettery
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }

    //Metoda do przesłonięcia w podklasach
    public String getDescription(){
        return "Produkt: %s | Cena: %.2f zł | Kategoria: %s"
                .formatted(name, price, category);
    }
}
