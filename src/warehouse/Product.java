package warehouse;

public abstract class Product {

    private final String id;
    private String name;
    private double basePrice;

    private static int counter = 0;

    public Product(String name, double basePrice) {
        counter++;
        this.id = "PROD-" + counter;
        this.name = name;
        this.basePrice = basePrice;
    }

    public Product(Product other){
        this.id = other.id;
        this.name = other.name;
        this.basePrice = other.basePrice;
    }

    public abstract double calculateFinalPrice();

    public String getId(){ return id; }
    public String getName(){ return name; }
    public double getBasePrice(){ return basePrice; }

    @Override
    public String toString(){
        return String.format("[%s] %-22s | baza: %.2f PLN", id , name, basePrice);
    }
}
