package TryWithResources;

public class PortValidator {

    public static int parseAndValidatePort(String rawPort) {

        //Ochrona przed null i pustym ciągiem znaków
        if (rawPort == null || rawPort.isBlank()) {
            throw new IllegalArgumentException("Port nie może być pusty!");
        }

        int port;
        try{
            port = Integer.parseInt(rawPort);
        } catch (NumberFormatException e){
            //Zamieniamy techniczny NumberFormatException na czytelny komunikat
            throw new IllegalArgumentException("Port musi być liczbą! Otrzymano: " + rawPort);
        }

        //Porty 0-1023 są zarezerwowane przez system operacyjny
        if (port < 1024 || port > 65535) {
            throw new IllegalArgumentException("Port poza bezpiecznym zakresem (1024 - 65535)!");
        }

        return port;
    }
}
