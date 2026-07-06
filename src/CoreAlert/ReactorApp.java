package CoreAlert;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ReactorApp {

    public static void main(String[] args) {

        double currentCoreTemp = 30.0;
        System.out.println("Status reaktora: BEZPIECZNY. Temperatura: " + currentCoreTemp + "C");

        /**
         * ANTYWZORZEC 1 - Diagnoza
         * BŁĄD: Supplier<String> emergencyAction = ReactorDiagnostics.triggerEmergencyCooling("WaterCooling");
         *
         * Prawa strona to wywołanie metody. Supplier<String> dostaje gotowy wynik, a nie przepis jego obliczenie.
         * Metoda odpaliła się zanim jakikolwiek alarm mógł być sprawdzony.
         *
         * NAPRAWA: owijamy wywołanie lambdą - metoda odpali się tylko wtedy gdy zostanie wywołane .get() czyli
         * wyłącznie wewnątrz bloku if
         */
        Supplier<String> emergencyAction =
                () -> ReactorDiagnostics.triggerEmergencyCooling("WaterCooling");

        if (currentCoreTemp > 500){
            System.out.println("!!! ALARM !!!");
            emergencyAction.get(); //metoda odpala się dopiero tutaj
        }
        System.out.println("[OK] Procedura chłodzenia nie uruchomiona - temperatura bezpieczna.\n");

        /**
         * ANTYWZORZEC 2 - Diagnoza
         * BŁĄD: int[] warningCounter = {0}; warningCounter[0]++;
         *
         * Zmienna lokalna w lambdzie musi być effectively final czyli kompilator nie pozwala na modyfikację int
         * bezpośrednio wewnątrz forEach.
         *
         * NAPRAWA: AtomicInteger z java.util.concurrent.atomic - incrementAndGet() to jedna atomowa operacja bezpieczna
         * wątkowo.
         */
        List<Double> temperatureHistory = List.of(26.4, 24.1, 28.9, 21.0, 31.5);
        AtomicInteger warningCounter = new AtomicInteger(0);

        temperatureHistory.forEach(temp -> {
            if (temp > 25.0){
                warningCounter.incrementAndGet(); // atomowe: read+increment+write w jednym kroku
            }
        });
        System.out.println("Liczba odczytów ostrzegawczych: " + warningCounter.get() + "\n");

        /**
         * ANTYWZORZEC 3 - Diagnoza
         * BŁĄD: AlertService alertService = null;
         *       Consumer<String> criticalAlertHandler = alertService::logAlert;
         *
         * Method Reference nie rzuca NPE przy definicji - przechwytuje wartość zmiennej alertService czyli null
         * jako odbiorcę i zapisuje ją w obiekcie funkcyjnym. Dopiero gdy JVM probuje wywołać logAlert() na tym null
         * receiverze podczas .accept(), rzuca NPE.
         *
         * NAPRAWDA: lambda z jednym jawnym null-checkiem - NPE nigdy nie zostanie rzucony, a brak serwisu jest obsługiwany
         * jako znana kontrolowana sytuacja
         */
        AlertService alertService = null; //serwis niezainicjalizowany

        Consumer<String> criticalAlertHandler = message -> {
            if (alertService != null){
                alertService.logAlert(message);
            } else {
                //zamiast crashu - czytelny komunikat ostrzegawczy
                System.err.println("[WARN] AlertService niedostępny - alert pominięty: " + message);
            }
        };

        try {
            criticalAlertHandler.accept("Przekroczono ciśnienie w komorze!");
        } catch (NullPointerException e) {
            System.err.println("CRASH! System monitorowania padł z powodu NPE!");
        }
    }
}
