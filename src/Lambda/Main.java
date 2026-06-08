package Lambda;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Własny interfejs funkcyjny AlertTransformer
        // Lambda łączy prefix z wiadomością, zamienia na UPPERCASE, trim() usuwa spacje
        AlertTransformer transformer =
                (message, prefix) -> (prefix + " " + message).toUpperCase().trim();

        System.out.println("AlertTransformer");
        System.out.println(transformer.transform("  system uruchomiony  ", "[INFO]"));
        // -> [INFO] SYSTEM URUCHOMIONY


        // NotificationEngine - wbudowane interfejsy funkcyjne
        System.out.println("\nNotificationEngine");

        List<User> users = List.of(
                new User("jan_kowalski",  25, true,  "jan@example.com"),
                new User("anna_nowak",    16, true,  "anna@example.com"),
                new User("piotr_wisniew", 30, false, "piotr@example.com"),
                new User("maria_wójcik",  22, true,  "maria@example.com")
        );

        NotificationEngine engine = new NotificationEngine();
        engine.processUsers(users);


        //Kompozycja predykatów - requiresImmediateAction
        System.out.println("\nAlertFilter - requiresImmediateAction");

        List<SystemAlert> alerts = List.of(
                new SystemAlert("AUTH",     7, "ERROR: token expired"),
                new SystemAlert("DATABASE", 2, "Connection established"),
                new SystemAlert("AUTH",     3, "User logged in"),
                new SystemAlert("CACHE",    6, "Timeout exceeded"),
                new SystemAlert("DATABASE", 5, "ERROR: deadlock detected")
        );

        alerts.forEach(alert -> {
            boolean action = AlertFilter.requiresImmediateAction.test(alert);
            System.out.printf("%-10s | severity: %d | %-35s -> %s%n",
                    alert.serviceName(),
                    alert.severityLevel(),
                    alert.logMessage(),
                    action ? "WYMAGA AKCJI" : "pomiń"
            );
        });


        //Referencje do metod - demonstracja
        MethodReferences.demonstrate();
        System.out.println("\nMethod References");
        System.out.println("String::toUpperCase     -> " + ((java.util.function.Function<String,String>) String::toUpperCase).apply("hello"));
        System.out.println("Integer::compare        -> " + ((java.util.Comparator<Integer>) Integer::compare).compare(3, 7));
        System.out.println("User::username          -> " + ((java.util.function.Function<User,String>) User::username).apply(users.get(0)));
        System.out.println("ArrayList::new          -> " + ((java.util.function.Supplier<java.util.ArrayList<String>>) java.util.ArrayList::new).get());
        System.out.print  ("System.out::println     -> "); ((java.util.function.Consumer<String>) System.out::println).accept("działa!");
    }
}
