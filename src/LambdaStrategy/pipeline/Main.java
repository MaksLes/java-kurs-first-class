package LambdaStrategy.pipeline;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        List<Event> events = List.of(
                new Event("EVT-001", "LOGIN", "Nieduane logowanie administratora", true),
                new Event("EVT-002", "NETWORK", "Utrata pakietów na routerze", false),
                new Event("EVT-003", "LOGIN", "Poprawne logowanie operatora", false),
                new Event("EVT-004", "SECURITY", "Wykryto skan portów", true),
                new Event("EVT-005", "SECURITY", "Zmiana reguł zapory", false)
        );

        EventProcessor processor = new EventProcessor();

        //Zestawy filtrów
        List<Predicate<Event>> criticalSecurityFilters = List.of(
                e -> e.isCrititcal(),
                event -> event.type().equals("SECURITY")
        );

        List<Predicate<Event>> loginFilters = List.of(
                e -> e.type().equals("LOGIN")
        );

        //Formattery
        Function<Event, String> alertFormat = e ->
                "ALERT [" + e.id() + "] " + e.description();

        Function<Event, String> auditFormat = e ->
                "AUDIT | typ=" + e.type() + " | krytyczne=" + e.isCrititcal() + " | opis=" + e.description();

        //Odbiornik
        Consumer<String> console = System.out::println;

        //Testy kombinacji
        System.out.println("Krytyczne zdarzenia SECURITY + format alertu");
        processor.process(events, criticalSecurityFilters, alertFormat, console);

        System.out.println("\nZdarzenia LOGIN + format audytowy");
        processor.process(events, loginFilters, auditFormat, console);

        System.out.println("\nBrak filtrów (pusta lista = wszystko przechdzi) + format audytowy");
        processor.process(events, List.of(), auditFormat, console);
    }
}

/**
 * Odpowiedzi na pytania
 *
 * 1. .and() daje modularne, testowalne i dynamicznie rekonfigurowalne predykaty, monolityczny && to sztywny
 * nierozkładalny blok logiki
 *
 * 2. Własny interfejs nadaje semantykę domenową kontraktu kryptograficznego, podczas gdy Function<String, String>
 *     dopuszcza przypadkową podmiane dowolną transformacją tekstu bez wykrycia błędu przez kompilator.
 */

