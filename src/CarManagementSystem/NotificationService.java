package CarManagementSystem;

public class NotificationService {

    private String serviceName;

    /**
     * Konstruktor parametryczny - tworzy usługę z podaną nazwą.
     *
     * @param serviceName nazwa usługi powiadomień
     */
    public NotificationService(String serviceName){
        this.serviceName = serviceName;
    }

    /**
     * Konstruktor bezparametrowy - deleguje do konstruktora parametrycznego,
     * ustawiając domyślną nazwę "Standard-Service".
     *
     * Przykład delegowania konstruktora za pomocą this().
     */
    public NotificationService(){
        this("Standard-Service");
    }

    public String getServiceName(){
        return serviceName;
    }

    public void setServiceName(String serviceName){
        this.serviceName = serviceName;
    }

    @Override
    public String toString(){
        return "NotificationService{serviceName='" + serviceName + "'}";
    }
}
