package warehouse;

public class Main {

    public static void main(String[] args) {

        Warehouse<Electronics> magazyn = new Warehouse<>();

        Electronics laptop = new Electronics("Laptop MacBook Pro", 7999.99, 24);
        Electronics laptopKopia = new Electronics(laptop); //konstruktor kopiujący

        System.out.println("Oryginał: " + laptop.getId() + " Kopia: " + laptopKopia.getId());

        magazyn.addProduct(laptop);
        magazyn.addProduct(laptopKopia);

        magazyn.showInventory();

        for (Electronics p : magazyn.getProducts()) {
            if (p instanceof Shipabble s){
                s.ship("Warszawa, ul. Sezamkowa 10");
            }
        }
    }
}
