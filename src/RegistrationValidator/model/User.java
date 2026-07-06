package RegistrationValidator.model;

public class User {

    private final String name;
    private final String email;
    private final String hashedPassword;

    public User(String name, String email, String hashedPassword) {
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getHashedPassword() { return hashedPassword; }

    @Override
    public String toString() {
        return "User { name='" + name + "', email='" + email + "', password='" + hashedPassword + "'}";
    }
}
