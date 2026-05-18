public class SalaKinowa {
    public static void main(String[] args) {

        int liczbaRzedow = 3;
        int liczbaMiejsc = 4;

        //pętla zewnętrzna - rzędy

        for (int rzad = 1; rzad <= liczbaRzedow; rzad++) {

            System.out.print("Rząd: " + rzad + ":");

            // pętla wewnętrzna miejsca

            for (int miejsce = 1; miejsce <= liczbaMiejsc; miejsce++) {

                String kodMiejsca = "R" + rzad + "M" + miejsce;

                if ((rzad + miejsce) % 2 == 0){
                    System.out.print(kodMiejsca + " [X]");
                } else {
                    System.out.print(kodMiejsca + " [ ]");
                }
            }

            System.out.println();
        }





    }
}
