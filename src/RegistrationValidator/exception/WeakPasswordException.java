package RegistrationValidator.exception;

public class WeakPasswordException extends RuntimeException {

    private final int score;
    private final String details;

    public WeakPasswordException(int score, String details) {
        super("Hasło jest za słabe (wynik: " + score + "/4)");
        this.score = score;
        this.details = details;
    }

    public int getScore() {
        return score;
    }

    public String getDetails() {
        return details;
    }
}
