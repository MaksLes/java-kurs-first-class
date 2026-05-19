package CarManagementSystem;

/**
 * Klasa abstrakcyjna reprezentująca pojazd.
 * Stanowi bazę dla wszystkich typów pojazdów w systemie.
 */
public abstract class Vehicle {

    private final String brand;
    private final String model;

    // Kompozycja – każdy pojazd posiada własną usługę powiadomień
    private final NotificationService notificationService;

    /**
     * Konstruktor parametryczny klasy Vehicle.
     *
     * @param brand marka pojazdu
     * @param model model pojazdu
     */
    public Vehicle(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.notificationService = new NotificationService();
    }

    /**
     * Konstruktor z niestandardową usługą powiadomień.
     *
     * @param brand           marka pojazdu
     * @param model           model pojazdu
     * @param serviceName     nazwa usługi powiadomień
     */
    public Vehicle(String brand, String model, String serviceName) {
        this.brand = brand;
        this.model = model;
        this.notificationService = new NotificationService(serviceName);
    }

    /**
     * Metoda abstrakcyjna – każda podklasa definiuje własny sposób jazdy.
     */
    public abstract void drive();

    // --- Gettery ---

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    /**
     * Zwraca czytelny opis pojazdu wraz z przypisaną usługą powiadomień.
     *
     * @return sformatowany opis pojazdu
     */
    @Override
    public String toString() {
        return "Pojazd: " + brand + " " + model
                + " [Usługa: " + notificationService.getServiceName() + "]";
    }
}