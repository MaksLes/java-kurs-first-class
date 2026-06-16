package RegistrationValidator;

import RegistrationValidator.exception.DuplicateEmailException;
import RegistrationValidator.exception.RegistrationException;
import RegistrationValidator.exception.ValidationException;
import RegistrationValidator.exception.WeakPasswordException;
import RegistrationValidator.model.PasswordStrength;
import RegistrationValidator.service.UserRegistrationService;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final UserRegistrationService service = new UserRegistrationService();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            switch (sc.nextLine().trim()) {
                case "1" -> handleRegistration();
                case "2" -> handlePasswordCheck();
                case "3" -> service.pritnDataBase();
                case "0" -> { System.out.println("Do widzenia!"); running = false; }
                default -> System.out.println("[BŁĄD] Nieznana opcja.");
            }
        }
        sc.close();
    }

    //Obsługa rejestracji z retry

    private static void handleRegistration() {
        while (true) {
            try{
                System.out.println("\nImię i nazwisko: ");
                String name = sc.nextLine().trim();
                System.out.println("Email: ");
                String email = sc.nextLine().trim();
                System.out.println("Hasło: ");
                String password = sc.nextLine();

                service.registerUser(name, email, password);
                service.pritnDataBase();
                return; //sukces - wychodzimy z pętli retry
            } catch (ValidationException e){
                // Błąd pola - ma sens poprosić o ponowne wprowadzenie danych
                System.out.println("\n[WALIDACJA] Pole '" + e.getField() + "': " + e.getMessage());
                if (!askRetry()) return;

            } catch (WeakPasswordException e){
                //Hasło do poprawy - informujemy o brakujących kryteriach
                System.out.println("\n[HASŁO] " + e.getMessage());
                System.out.println("[HASŁO] " + e.getDetails());
                if (!askRetry()) return;

            } catch (DuplicateEmailException e) {
                // Ten sam email nie przejdzie przy kolejnej próbie - wracamy do menu
                System.out.println("\n[DUPLIKAT] " + e.getMessage());
                System.out.println("[DUPLIKAT] Użyj innego adresu email.");
                return;

            } catch (RegistrationException e) {
                //Ogólny fallback dla nieznanych błędów domenowych
                System.out.println("\n[BŁĄD REJESTRACJI] " + e.getMessage());
                return;
            }

        }
    }

    private static void handlePasswordCheck() {
        System.out.println("\nHasło do sprawdzenia: ");
        String password = sc.nextLine();
        PasswordStrength strength = service.validatePassword(password);
        System.out.printf("[SIŁA HASŁA] %s (%S)%n", strength.getLabel(), strength.name());
    }

    //Helper

    private static boolean askRetry() {
        System.out.println("Spróbowac ponownie? (t/n): ");
        return sc.nextLine().trim().equalsIgnoreCase("t");
    }

    private static void printMenu() {
        System.out.println("\n||================================||");
        System.out.println("|| SYSTEM REJESTRACJI UŻYTKOWNIKA ||");
        System.out.println("||================================||");
        System.out.println("|| 1. Zarejestruj użytkownika     ||");
        System.out.println("|| 2. Sprawdź siłę hasła          ||");
        System.out.println("|| 3. Wyświetl bazę użytkowników  ||");
        System.out.println("|| 0. Wyjście                     ||");
        System.out.println("====================================");
        System.out.println("Wybierz opcję: ");
    }
}
