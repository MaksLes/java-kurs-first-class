package RestaurantSystem;

import RestaurantSystem.exception.ReservationValidationException;
import RestaurantSystem.exception.RestaruantException;
import RestaurantSystem.model.Table;
import RestaurantSystem.service.ReservationService;

public class Main {

    public static void main(String[] args) {

        ReservationService service = new ReservationService();

        //Test 1: ścieżka sukcesu
        System.out.println("Test 1: Poprawna rezerwacja");
        try {
            Table stolik5 = new Table(5);
            service.createReservation("Kowalski", 4, stolik5);
        } catch (RestaruantException e){
            System.out.println("[BŁĄD] " + e.getMessage());
        }

        //Test 2: błędna liczba gości
        System.out.println("\n Test 2: Błędna liczba gości (guestCount = -2)");
        try{
            Table stolik3 = new Table(3);
            service.createReservation("Nowak", -2, stolik3);
        } catch (ReservationValidationException e){
            //Bardziej szczegółowy catch musi być przed RestaurantException
            System.out.println("[WALIDACJA] Kod błędu: " + e.getErrorCode());
            System.out.println("[WALIDACJA] Szczegóły: " + e.getMessage());
        } catch (RestaruantException e){
            System.out.println("[BŁĄD] " + e.getMessage());
        }

        //Test 3: podwójna rezerwacja tego samego stolika
        System.out.println("\n Test 3: Podwójna rezerwacja stolika nr 7");
        try {
            Table stolik7 = new Table(7);
            service.createReservation("Wiśniewski", 2, stolik7); //pierwsza - OK
            service.createReservation("Zielińska", 3, stolik7); // druga - konflikt
        } catch (ReservationValidationException e){
            System.out.println("[WALIDACJA] " + e.getMessage());
        } catch (RestaruantException e){
            //Exception chaining w akcji - getCause() odkrywa pierwotny wyjątek
            System.out.println("[BŁĄD GŁÓWNY] " + e.getMessage());
            System.out.println("[PIERWOTNA PRZYCZYNA] " + e.getCause().getMessage());
        }

        //Test 4: null jako clientName
        System.out.println("\n Test 4: null jako clientName");
        try {
            Table stolik2 = new Table(2);
            service.createReservation(null,3, stolik2);
        } catch (NullPointerException e){
            System.out.println("[NPE] " + e.getMessage());
        } catch (RestaruantException e){
            System.out.println("[BŁĄD] " + e.getMessage());
        }
    }
}
