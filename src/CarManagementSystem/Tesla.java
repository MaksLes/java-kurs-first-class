package CarManagementSystem;

public class Tesla extends Car implements Electric{

    /**
     * Konstruktor klasy Tesla.
     *
     * @param model model pojazdu Tesla
     */
    public Tesla(String model){
     super("Tesla", model, "Tesla-Notification-Service");
    }

    /**
     * Nadpisanie metody drive() - Tesla jedzie na silniku elekttrycznym.
     */
    @Override
    public void drive() {
        System.out.println("Jadę samochodem elektrycznym: " + getBrand() + " " + getModel() + " (ciężko mnie ugasić)");
    }

    /**
     * Nadpisanie metody charge() z interfejsu Electric.
     */
    @Override
    public void charge(){
        System.out.println("Ładowanie akumulatorów Tesli...");
    }
}
