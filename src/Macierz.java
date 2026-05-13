import java.util.Arrays;
import java.util.Scanner;

public class Macierz {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //Tworzenie macierzy 3x3
        int[][] macierz = new int [3][3];

        //Wczytywanie danych
        System.out.println("Podaj 9 liczb do macierzy 3x3");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                macierz[i][j] = sc.nextInt();
            }
        }

        //Wyświetlanie macierzy
        System.out.println("\nMacierz:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%4d", macierz[i][j]);
            }
            System.out.println();
        }

        //Transpozycja macierzy
        int[][] transpozycja = new int[3][3];

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                    transpozycja[i][j] = macierz[j][i];
            }
        }

        System.out.println("\nTranspozycja macierzy:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%4d", transpozycja[i][j]);
            }
            System.out.println();
        }

        //Obrót macierzy o 90 stopni w prawo
        int [][] obrot = new int [3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                obrot[i][j] = macierz[2 - j][i];
            }
        }
        System.out.println("\n Macierz obrócona o 90 stopni w prawo:");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%4d", obrot[i][j]);
            }
            System.out.println();
        }

        //Suma każdego wiersza
        System.out.println("\n Suma wierszy:");

        for (int i = 0; i < 3; i++) {
            int sumaWiersza = 0;

            for (int j = 0; j < 3; j++) {
                sumaWiersza += macierz[i][j];
            }
            System.out.println("Wiersz " + (i + 1) + ": " + sumaWiersza);
        }

        //Suma każdej kolumny
        System.out.println("\nSuma kolumn:");

        for (int j = 0; j < 3; j++) {
            int sumaKolumny = 0;

            for (int i = 0; i < 3; i++) {
                sumaKolumny += macierz[i][j];
            }

            System.out.println("Kolumna " + (j + 1) + ": " + sumaKolumny);
        }

        //Sprawdzenie symetryczności macierzy
        boolean symetryczna = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (macierz[i][j] != macierz[j][i]){
                        symetryczna = false;
                }
            }
        }

        //Wynik
        if (symetryczna) {
            System.out.println("\nMacierz jest symetryczna.");
        } else {
            System.out.println("\nMacierz jest nie symetryczna");
        }

        sc.close();
    }
}
