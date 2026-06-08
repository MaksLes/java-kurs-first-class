package Lambda;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Predicate;

public class NotificationEngine {

    //Predicate - użytkownik kwalifikuje się do powiadomienia: musi być aktywny oraz pełnoletni
    private final Predicate<User> isEligible = user -> user.isActive() && user.age() >= 18;

    //Function - tworzy spersonalizowany komunikat z danych użytkownika
    private final Function<User, String> buildMessage = user -> "Witaj " + user.username() +
            ", przygotowaliśmy dla Ciebie ofertę!";

    //Consumer - symuluje wysłanie maila
    private final Consumer<String> sendEmail = message -> System.out.println("[WYSŁANO EMAIL]: " + message);

    //Supplier - generuje unikalny token transakcyjny UUID jako String
    private final Supplier<String> tokenGenerator = () -> UUID.randomUUID().toString();

    // Przetwarza listę użytkowników, filtruje niekwalifikujących się, buduje wiadomość, dołącza token
    // i wysyła powiadomienie
    public void processUsers(List<User> users) {
        users.stream()
                .filter(isEligible)
                .map(buildMessage)
                .map(message -> message + " [TOKEN: " + tokenGenerator.get() + "]")
                .forEach(sendEmail);
    }
}
