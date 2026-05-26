package warehouse;

public class Electronics extends Product implements Shipabble {

    private int warrantyMonths;

    public Electronics(String name, double basePrice, int warrantyMonths) {
        super(name, basePrice);
        this.warrantyMonths = warrantyMonths;
    }

    public Electronics(Electronics other){
        super(other);
        this.warrantyMonths = other.warrantyMonths;
    }

    @Override
    public double calculateFinalPrice(){
        return getBasePrice() * 1.10;
    }

    @Override
    public void ship(String address) {
        System.out.printf("Wysyłka [%s] \"%s\" -> %s%n", getId(), getName(), address);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Gwarancja: %d mmies. | Końcowa: %.2f PLN", warrantyMonths, calculateFinalPrice());
    }


}
