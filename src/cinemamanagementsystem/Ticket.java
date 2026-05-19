package cinemamanagementsystem;

public class Ticket {

    private MovieShow show;
    private int seatNumber;
    private double price;

    //Pole statyczne - wspólne dla wszystkich instancji
    private static double totalRevenue = 0.0;

    //Konstruktor standardowy
    public Ticket(MovieShow show, int seatNumber, double price) {
        this.show = show;
        this.seatNumber = seatNumber;
        this.price = price;
        totalRevenue += price; // każdy nowy bilet powiększa przychód
    }

    //Metoda fabryczna - alternatywny sposób tworzenia obiektu
    public static Ticket createVipTicket(MovieShow show, int seatNumber){
        return new Ticket(show, seatNumber, 49.99);
    }

    //Getter statyczny - dostęp bez tworzenia obiektu
    public static double getTotalRevenue() {
        return totalRevenue;
    }

    //Gettery instancyjne
    public MovieShow getShow() { return show; }
    public int getSeatNumber() { return seatNumber; }
    public double getPrice() { return price; }

    @Override
    public String toString(){
        return String.format("Ticket{show='%s', seat=%d, price=%.2f PLN}", show.getTitle(), seatNumber, price);
    }
}
