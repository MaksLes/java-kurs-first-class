public class Liczbowy_Bum {
    public static void main(String[] args) {
        for (int i = 1; i <= 50; i++){

            boolean podzielnaPrzez7 = (i % 7 == 0);
            boolean zawiera7 = String.valueOf(i).contains("7");

            if (podzielnaPrzez7 && zawiera7){
                System.out.println("BUM-CYFRA");
            }
            else if(podzielnaPrzez7){
                System.out.println("BUM");
            }
            else if (zawiera7){
                System.out.println("CYFRA");
            }
            else{
                System.out.println(i);
            }
        }
    }
}
