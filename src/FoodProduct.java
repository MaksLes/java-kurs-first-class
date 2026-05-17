import java.time.LocalDate;

public class FoodProduct  extends Product{
    private LocalDate expiryDate;

    public FoodProduct(String name, double price, String category,  LocalDate expiryDate ){
        super(name, price, category);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() { return expiryDate; }

    @Override
    public String getDescription(){
        return super.getDescription() +
                " | Data ważności: %s".formatted(expiryDate);
    }
}
