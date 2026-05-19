package cinemamanagementsystem;

public class Main {

    public static void main(String[] args) {

        //Seanse
        MovieShow show1 = new MovieShow("Interstellar", "18:00", 120);
        MovieShow show2 = new MovieShow("Bad Boys", "20:30"); //domyślne 100 miejsc

        //Manager
        Manager manager = new Manager("Anna Kowalska", 6000.00, 1500.00);

        //Kino
        Cinema cinema = new Cinema("Cinema City", manager);

        //Bilety przez konstruktor standardowy
        cinema.addTicket(new Ticket(show1, 15, 29.99));
        cinema.addTicket(new Ticket(show1, 16, 29.99));
        cinema.addTicket(new Ticket(show1, 5, 24.99));

        //Bilety przez metodę fabryczną
        cinema.addTicket(Ticket.createVipTicket(show1, 1));
        cinema.addTicket(Ticket.createVipTicket(show2, 2));

        //Podsumowanie
        cinema.printSummary();

        //Weryfikacja dostępu statycznego bez obiektu
        System.out.printf("%nStatyczny przychód: %.2f PLN%n", Ticket.getTotalRevenue());
    }
}
