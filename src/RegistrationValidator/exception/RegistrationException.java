package RegistrationValidator.exception;

public class RegistrationException extends RuntimeException {

    public RegistrationException(String message) {
        super(message);
    }

    //Konstruktor z case potrzebny do Exception chaining w głębszych warstwach
    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
