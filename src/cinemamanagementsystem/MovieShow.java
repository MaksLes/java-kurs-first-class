package cinemamanagementsystem;

public class MovieShow {

    private String title;
    private String time;
    private int availableSeats;

    //Konstruktor główny
    public MovieShow(String title, String time, int availableSeats){
        this.title = title;
        this.time = time;
        this.availableSeats = availableSeats;
    }

    //Konstruktor skrócony - deleguje do długiego przez this
    public MovieShow(String title, String time){
        this(title, time, 100); //domyślna liczba miejsc 100
    }

    //Gettery
    public String getTitle() { return title; }
    public String getTime () { return time; }
    public int getAvailableSeats() { return availableSeats; }

    //Setter z walidacją
    public void setTitle(int availableSeats){
        if (availableSeats >= 0){
            this.availableSeats = availableSeats;
        }
    }

    @Override
    public String toString(){
        return String.format("MovieShow{title='%s', time='%s', seats=%d}", title, time, availableSeats);
    }
}
