package ParkFlow;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Function<String, String> plateFormatterLambda =
                rawPlate -> rawPlate.replace(" ", "").toUpperCase();

        //Wariant Method Reference - typ: referencja instancyjna na typie (unbound)
        //Gdyby String posiadał metodę normalizePlate(), poprawna referencja wyglądałaby tak:
        //  Function<String, String> plateFormatterMethodRef = String::normalizePlate;
        //JVM automatycznie rozpoznaje, że parametr String przekazany do .apply() jest obiektem
        //wywołującym - analogicznie do String::toUpperCase poniżej:
        Function<String, String> plateFormatterMethodRef = String::toUpperCase;

        System.out.println("Lambda: " + plateFormatterLambda.apply("ab 12 cd"));
        System.out.println("Method Ref (unbound): " + plateFormatterMethodRef.apply("ab 12 cd"));

        //Część 3 Predykaty

        System.out.println("Walidacja Biletów");

        System.out.println(TicketValidator.isValidTicket.test("PARK-123"));
        System.out.println(TicketValidator.isValidTicket.test("TEST-000"));
        System.out.println(TicketValidator.isValidTicket.test("XYZ-999"));

        //Część 4 Disptach Table

        PricingService pricingService = new PricingService();

        double basePrice = 10.0;

        System.out.println("\nCennik");

        System.out.println("MOTO: " + pricingService.calculateFee(basePrice, "MOTO"));
        System.out.println("CAR: " + pricingService.calculateFee(basePrice, "CAR"));
        System.out.println("BUS: " + pricingService.calculateFee(basePrice, "BUS"));


    }
}

/**
 * Pytanie 1.1
 *
 * Lambda bierze zmienną tylko jako kopię wartości nie jako żywe połączenie.
 * Dlatego zmienna musi byc effectively final czyli nie może się już zmienić po przypisaniu.
 * Inaczej lambda mogłaby działać na starych danych
 *
 * Naprawa:
 *
 * AtomicInteger counter = new AtomicInteger(0);
 * plates.forEach(plate -> {
 *     Syste.out.println("Zarejestrowano wjazd: " + plate);
 *     counter.incrementAndGet();
 * }
 *
 * Pytanie 1.2
 * String liczy się od razu, zanim jeszcze metoda go użyje.
 * Supplier<String> liczy się dopiero gdy wywołamy .get() czyli tylko gdy naprawdę trzeba.
 * Przydaje się to np. przy logowaniu, jak log jest wyłączony to drogie obliczenie w ogóle się nie wykona.
 */