package warehouse;

public class Food extends Product{

    private String expirationDate;

    public Food(String name, double basePrice, String expirationDate) {
        super(name, basePrice);
        this.expirationDate = expirationDate;
    }

    public Food(Food other){
        super(other);
        this.expirationDate = other.expirationDate;
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice() * 0.80;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Ważny do: %s | Końcowa: %.2f PLN", expirationDate, calculateFinalPrice());
    }

}
