package LambdaStrategy.cipher;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        List<Message> messages = List.of(
                new Message("MSG-101", "atak o świcie", 8),
                new Message("MSG-202", "przesyłka odebrana", 3),
                new Message("MSG-303", "zmiana lokalizacji", 9),
                new Message("MSG-404", "spotkanie odwołane", 5)
        );

        MessageDispatcher dispatcher = new MessageDispatcher();

        //Strategia 1: Odwrócenie + wersaliki
        EncryptionStrategy reverseUpper = text ->
                new StringBuilder(text).reverse().toString().toUpperCase();

        //Strategia 2: Leet Speak
        EncryptionStrategy leetSpeak = text -> text
                .replace('a', '4')
                .replace('e', '3')
                .replace('i', '1')
                .replace('o', '0');

        //Strategia 3: Ukrycie środka
        //Warunek brzegowy:  tekty krótsze niż 3 znaki nie mają środka do ukrycia
        EncryptionStrategy obfuscate = text ->
                text.length() < 3 ? text : text.charAt(0) + "***" + text.charAt(text.length() - 1);

        //Flitry
        Predicate<Message> highSecurity = m -> m.securityLevel() > 5;
        Predicate<Message> allMessages = m -> true;

        //Odbiornik
        Consumer<String> consoleSender = text -> System.out.println(" -> " + text);

        //Testy kombinacji
        System.out.println("Wysokie bezpieczeństwo (>5) + Reverse/Upper");
        dispatcher.dispatch(messages, reverseUpper, highSecurity, consoleSender);

        System.out.println("\nWszystkie wiadomości + Leet Speak");
        dispatcher.dispatch(messages, leetSpeak, allMessages, consoleSender);

        System.out.println("\nWysokie bezpieczeństwo (>5) + Obfuscation");
        dispatcher.dispatch(messages, obfuscate, highSecurity, consoleSender);
    }
}
