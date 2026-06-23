package aeroflow;

import aeroflow.model.Aircraft;
import aeroflow.model.Passport;
import aeroflow.service.FlightControl;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        //Część 2: Passport - blokowanie duplikatów w HashSet
        System.out.println("CZĘŚĆ 2: Passport - kontrakt equals/hashCode");

        Set<Passport> passports = new HashSet<>();
        Passport p1 = new Passport("EE99212", "Jan Kowalski");
        Passport p2 = new Passport("EE99212", "Jan Kowalskii"); //literówka w nazwisku

        passports.add(p1);
        passports.add(p2); // powinno zostać zablokowane

        System.out.println("Dodano 2 paszporty z tym samym numerem.");
        System.out.println("p1.equals(p2) : " + p1.equals(p2));
        System.out.println("p1.hashCode() : " + p1.hashCode());
        System.out.println("p2.hashCode() : " + p2.hashCode());
        System.out.println("Rozmiar zbioru : " + passports.size());
        System.out.println(passports.size() == 1
                ? "[OK] Duplikat zablokowany poprawnie."
                : "[BŁĄD] Duplikat przeszedł - kontrakt złamany!");

        //Część 3: FlightControl
        System.out.println("\nCZĘŚĆ 3: FlightControl - system kontroli lotów");

        FlightControl control = new FlightControl();

        System.out.println("\nOdprawa pasażerów");
        control.assignPassengerToGate("GATE-A17", "Kowalski Jan");
        control.assignPassengerToGate("GATE-A17", "Nowak Anna");
        control.assignPassengerToGate("GATE-B03", "Wiśniewski Piotr");
        control.assignPassengerToGate("GATE-A17", "Zielińska Maria");
        control.assignPassengerToGate("GATE-C21", "Lewadnowski Tomasz");

        control.printGates();

        System.out.println("\nRejestracja do startu");
        control.registerDeparture(new Aircraft("LH104", 2));
        control.registerDeparture(new Aircraft("FR812", 5)); //absolutny priorytet
        control.registerDeparture(new Aircraft("LOT221", 3));
        control.registerDeparture(new Aircraft("W62137", 5)); //absolutny priorytet
        control.registerDeparture(new Aircraft("BA007", 1));

        //Oczekiwana kolejność FR812(5) -> W62137(5) -> LOT221(3) -> LH104(2) -> BA007(1)
        control.processTakeoffs();
    }
}
