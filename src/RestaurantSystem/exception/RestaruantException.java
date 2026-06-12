package RestaurantSystem.exception;

public class RestaruantException extends RuntimeException{

    private final String errorCode;

    public RestaruantException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    //Konstruktor z cause - niezbędny do Exception Chaining
    public RestaruantException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
