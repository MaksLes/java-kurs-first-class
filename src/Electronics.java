public class Electronics extends Product{
    private int warranty; //gwarancja w miesiącach

    public Electronics(String name, double price, String category, int warranty){
        super(name, price, category); //Wywołanie konstruktora Product
        this.warranty = warranty;
    }

    public int getWarranty() { return warranty; }

    @Override
    public String getDescription(){
        return super .getDescription() +
                " | Gwarancja: %d mies.".formatted(warranty);
    }
}
