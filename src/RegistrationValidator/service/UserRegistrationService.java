package RegistrationValidator.service;

import RegistrationValidator.exception.DuplicateEmailException;
import RegistrationValidator.exception.ValidationException;
import RegistrationValidator.exception.WeakPasswordException;
import RegistrationValidator.model.PasswordStrength;
import RegistrationValidator.model.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class UserRegistrationService {

    //x@y.z - minimum jeden znak przed @, między @ a kropką, i po kropce
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");

    // \\p{L} obsługuje litery Unicode - łapie polskie znaki (ą, ę, ł..)
    private static final Pattern NAME_PATTERN =
            Pattern.compile("^[\\p{L}][\\p{L}\\s-]{1,99}$");

    private static final Pattern SPECIAL_CHAR_PATTERN =
            Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]");

    //Symulacja bazy danych - email jako unikalny klucz
    private final Map<String, User> database = new HashMap<>();

    //Główna metoda biznesowa
    public void registerUser(String name, String email, String password) {
        // Guard clauses - fail-fast każda walidacja kończy metodę natychmiast
        validateName(name);
        validateEmail(email);
        checkEmailUniqueness(email);
        validatePasswordStrict(password);

        String hashedPassword = "hashed_" + password; //symulacja hashowania
        database.put(email, new User(name, email, hashedPassword));
        System.out.println("[SUKCES] Zarejestrowano użytkownika: " + name);
    }

    // Walidacje (prywatne)

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ValidationException("name", "Imię/nazwisko nie może być puste!");
        }
        if (name.length() < 2) {
            throw new ValidationException("name", "Imię/nazwisko musi mieć co najmniej 2 znaki.");
        }
        if (name.length() > 100){
            throw new ValidationException("name", "Imię/nazwisko nie może przekraczać 100 znaków.");
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new ValidationException("name", "Imię/nazwisko może zawierać tylko litery, spacje i myślniki.");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new ValidationException("email", "Email nie może być pusty!");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("email", "Email ma nieprawidłowy format (wymagane: x@y.z). Podano: " + email);
        }
    }

    private void checkEmailUniqueness(String email) {
        if (database.containsKey(email)){
            throw new DuplicateEmailException(email);
        }
    }

    private void validatePasswordStrict(String password) {
        if (password == null || password.isBlank()) {
            throw new ValidationException("password", "Hasło nie może być puste!");
        }
        int score = calculatePasswordScore(password);
        //Rejestracja wymaga spełnienia wszystkich 4 kryteriów
        if (score < 4) {
            throw new WeakPasswordException(score, buildMissingDetails(password));
        }
    }

    // Ocena siły hasła

    public PasswordStrength validatePassword(String password) {
        if (password == null || password.isBlank()) return  PasswordStrength.WEAK;;
        int score = calculatePasswordScore(password);
        if (score <= 1) return PasswordStrength.WEAK;
        if (score <= 3) return PasswordStrength.OK;
        return PasswordStrength.STRONG;
    }

    //Helpery prywatne

    private int calculatePasswordScore(String password) {
        int score = 0;
        if (password.length() >= 8) score++;
        if (password.chars().anyMatch(Character::isUpperCase)) score++;
        if (password.chars().anyMatch(Character::isDigit)) score++;
        if (SPECIAL_CHAR_PATTERN.matcher(password).find()) score++;
        return score;
    }

    private String buildMissingDetails(String password) {
        StringBuilder sb = new StringBuilder("Brakuje: ");
        if (password.length() < 8) sb.append("[min. 8 znaków] ");
        if (password.chars().noneMatch(Character::isUpperCase)) sb.append("[wielka litera] ");
        if (password.chars().noneMatch(Character::isDigit)) sb.append("[cyfra] ");
        if (!SPECIAL_CHAR_PATTERN.matcher(password).find()) sb.append("[znak specjalny] ");
        return sb.toString().trim();
    }

    //Widok bazy

    public void pritnDataBase(){
        if (database.isEmpty()){
            System.out.println("[BAZA] Brak zarejestrowanych użytkowników.");
            return;
        }
        System.out.println("[BAZA] Zarejestrowani użytkownicy (" + database.size() + "):");
        database.values().forEach(u -> System.out.println(" ->" + u));
    }

    public Map<String, User> getDatabase() {
        return Collections.unmodifiableMap(database);
    }
}
