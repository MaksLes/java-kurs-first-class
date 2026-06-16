package RegistrationValidator.exception;

public class DuplicateEmailException extends RuntimeException {

    private final String email;

    public DuplicateEmailException(String email) {
        super("Email już istnieje w systemie: " + email);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
