import java.util.Scanner;

public class Automatic_timetable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Podaj numer dnia tygodnia (1-7): ");
        int day = sc.nextInt();

        String dayName = switch (day){
            case 1 -> "Poniedziałek";
            case 2 -> "Wtorek";
            case 3 -> "Środa";
            case 4 -> "Czwartek";
            case 5 -> "Piątek";
            case 6 -> "Sobota";
            case 7 -> "Niedziela";
            default -> null;
        };

        if (dayName == null){
            System.out.println("Błąd! Podaj liczbę z zakresu 1-7!");
            return;
        }

        boolean weekend = (day == 6 || day == 7);

        String FirstDeparture = switch (day){
            case 1, 2, 3, 4, 5 -> "5:30";
            case 6 -> "7:00";
            case 7 -> "9:00";
            default -> "";
        };

        System.out.println("Dzień: " + dayName);
        System.out.println("Typ dnia: " + (weekend ? "Weekend" : "Dzień roboczy"));
        System.out.println("Pierwszy autobus: " + FirstDeparture);
        sc.close();
    }
}
