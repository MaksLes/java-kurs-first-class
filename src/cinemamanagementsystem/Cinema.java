package cinemamanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private String name;
    private Manager manager;
    private List<Ticket> soldTickets;

    public Cinema(String name, Manager manager){
        this.name = name;
        this.manager = manager;
        this.soldTickets = new ArrayList<>();
    }

    public  void addTicket(Ticket ticket){
        soldTickets.add(ticket);
    }

    public void printSummary(){
        System.out.println("=== Podsumowanie kina: " + name + " ===");
        System.out.println("Liczba sprzedanych biletów : " + soldTickets.size());
        System.out.printf("Łączny przychód          : %.2f PLN%n", Ticket.getTotalRevenue());
        System.out.printf("Pensja managera (%s)   : %.2f PLN%n", manager.getName(), manager.calculateSalary());
        System.out.println("\nSzczegóły biletów:");
        for (Ticket t :  soldTickets){
            System.out.println(" " + t);
        }
    }

    //Gettery
    public String getName() { return name; }
    public Manager getManager() { return manager; }
    public List<Ticket> getSoldTickets() { return soldTickets; }
}
