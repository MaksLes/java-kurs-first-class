package CarManagementSystem;

public class Car extends Vehicle {

    /**
     * Konstruktor klasy Car.
     *
     * @param brand marka samochodu
     * @param model model samochodu
     */
    public Car(String brand, String model){
        super(brand, model);
    }

    /**
     * Konstruktor klasy Car z niestandardową usługą powiadomień
     *
     * @param brand     marka samochodu
     * @param model     model samochodu
     * @param serviceName nazwa usługi powiadomień
     */
    public Car(String brand, String model, String serviceName){
        super(brand, model, serviceName);
    }

    /**
     * Implementacja metody drive() dla pojazdu spalinowego
     */
    @Override
    public void drive(){
        System.out.println("Jadę samochodem spalinowym: " + getBrand() + " " + getModel());
    }
}
