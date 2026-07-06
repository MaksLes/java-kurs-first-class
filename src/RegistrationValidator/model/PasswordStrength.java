package RegistrationValidator.model;

public enum PasswordStrength {
    WEAK("Słabe"),
    OK("Wystarczające"),
    STRONG("Silne");

    private final String label;

    PasswordStrength(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
