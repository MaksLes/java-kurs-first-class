package aeroflow.model;

import java.util.Objects;

public class Passport {

    private final String passportNumber;
    private final String holderName;

    public Passport(String passportNumber, String holderName) {
        this.passportNumber = passportNumber;
        this.holderName = holderName;
    }

    public String getPassportNumber() { return passportNumber; }
    public String getHolderName() { return holderName; }

    //Unikalność wyłącznie po numerze paszportu - literówka nie tworzy nowego dokumentu
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport)) return false;
        Passport other = (Passport) o;
        return this.passportNumber.equals(other.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportNumber);
    }

    @Override
    public String toString() {
        return "Passport{nr'" + passportNumber + "', holder='" + holderName + "'}";
    }
}
