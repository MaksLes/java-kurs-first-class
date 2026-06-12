package RestaurantSystem.exception;

public class ReservationValidationException extends RestaruantException {

    public ReservationValidationException(String message, String errorCode) {
        super(message, errorCode);
    }
}
