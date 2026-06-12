package TryWithResources;

public class Main {
    public static void main(String[] args) {

        String[] testValues = {"8080", "tekst", "999999", null};

        for (String rawPort : testValues) {
            System.out.println("\nTest dla wartości: [" + rawPort + "]");

            //każda iteracja otwiera i zamyka swój własny zasób
            //close() jest wywoływane automatycznie przed blokiem catch, niezależnie od tego czy wyjątek wystąpił
            try (ConfigFileConnection connection = new ConfigFileConnection()){
                int port = PortValidator.parseAndValidatePort(rawPort);
                System.out.println("[SUKCES] Port zatwierdzony doo nasłuchu: " + port);
            } catch (IllegalArgumentException e){
                System.out.println("[BŁĄD] " + e.getMessage());
            }
        }
    }
}
