import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] sala = new int[5][5];

        sala[0][1] = 1;
        sala[2][3] = 1;
        sala[4][0] = 1;

        boolean dziala = true;

        while (dziala){
            System.out.println("\n===MENU===");
            System.out.println("1. Wypisz salę");
            System.out.println("2. Rezerwuj miejsce");
            System.out.println("3. Oblicz cenę biletów");
            System.out.println("4. Wyjście");
            System.out.println("5. Wybierz opcję");

            int wybor = sc.nextInt();

            switch (wybor){
                case 1:
                    wypiszSale(sala);
                    break;

                    case 2:
                        rezerwujMiejsce(sala, sc);
                        break;

                        case 3:
                            obliczCeny(sc);
                            break;

                            case 4:
                                dziala = false;
                                System.out.println("Zamykanie programu...");
                                break;

                                default:
                                    System.out.println("Niepoprawna opcja.");

            }
        }
    }

    public static void wypiszSale(int[][] sala){
        System.out.println("\n 0  1  2  3  4");
        for (int r = 0; r < sala.length; r++){
            System.out.println(r + " ");
            for (int c = 0; c < sala[r].length; c++){
                if (sala[r][c] == 1){
                    System.out.println("[X]");
                } else {
                    System.out.println("[ ]");
                }
            }
            System.out.println();
        }
    }
    public static void rezerwujMiejsce(int[][] sala, Scanner sc){
        System.out.println("Podaj numer rzędu (0-4): ");
        int r = sc.nextInt();

        System.out.println("Podaj numer kolumny (0-4): ");
        int c = sc.nextInt();

        if (r < 0 || r >= sala.length || c < 0 || c >+ sala[0].length){
            System.out.println("Błędne współrzędne!");
            return;
        }
        if (sala[r][c] == 1){
            System.out.println("To miejsce jest już zajęte!");
        } else {
            sala[r][c] = 1;
            System.out.println("Miejsce zostało zarezerwowane.");
        }
    }

    public static double cenaBiletu(int n, double cenaPodstawowa){
        if (n == 1) return cenaPodstawowa;
        return cenaBiletu(n - 1, cenaPodstawowa) * 0.95;
    }

    public static void wypiszCeny(int n, double cenaPodstawowa){
        if (n == 0) return;

        wypiszCeny(n - 1, cenaPodstawowa);

        double cena = cenaBiletu(n, cenaPodstawowa);
        System.out.printf("Bilet %d: %.2f zł%n", n, cena);
    }

    public static double sumaBiletow(int n, double cenaPodstawowa){
        if (n == 0) return 0;
        return cenaBiletu(n, cenaPodstawowa) + sumaBiletow(n - 1, cenaPodstawowa);
    }

    public static void obliczCeny(Scanner sc){
        double cenaPodstawowa = 10.85;

        System.out.println("Ile biletów chcesz kupić? ");
        int ile = sc.nextInt();

        wypiszCeny(ile, cenaPodstawowa);

        double suma = sumaBiletow(ile, cenaPodstawowa);
        System.out.printf("Łączna cena: %.2f zł%n", suma);
    }


}
