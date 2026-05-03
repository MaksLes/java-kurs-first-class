import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Wczytywanie danych od użytkownika
        System.out.println("Podaj wagę [kg]: ");
        double waga = scanner.nextDouble();

        System.out.println("Podaj wzrost [cm]: ");
        double wzrostCm = scanner.nextDouble();

        //sprawdzenie poprawności wzrostu
        if (wzrostCm < 30 || wzrostCm > 240){
            System.out.println("Ostrzeżenie: Podany wzrost jest nieprawidłowy (zakres 30-240 cm).");
            scanner.close();
            return;
        }

        //przeliczenie wzrostu na metry
        double wzrostM = wzrostCm / 100.0;

        //Obliczenie BMI
        double bmi = waga / (wzrostM * wzrostM);

        //Wyświetlenie wyniku
        System.out.printf("BMI: %.2f%n", bmi);

        //Kategorie BMI
        if (bmi < 18.5){
            System.out.println("Kategoria: Niedowaga");
        } else if (bmi < 25.0) {
            System.out.println("Kategoria: Norma");
        } else if (bmi < 30.0) {
            System.out.println("Kategoria: Nadwaga");
        } else {
            System.out.println("Kategoira: Otyłość");
        }

        scanner.close();
    }
}
