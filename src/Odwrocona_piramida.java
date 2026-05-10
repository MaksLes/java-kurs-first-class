public class Odwrocona_piramida {
    public static void main(String[] args) {

        int wysokosc = 5;

        for (int i = wysokosc; i >= 1; i--){

            for (int j = 0; j < wysokosc - i; j++){
                System.out.print(" ");
            }

            for (int k = 0; k < (2 * i - 1); k++){
                System.out.print("*");
            }

            System.out.println();
        }
    }
}
