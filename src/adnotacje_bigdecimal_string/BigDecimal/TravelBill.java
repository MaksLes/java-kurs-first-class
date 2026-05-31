package adnotacje_bigdecimal_string.BigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TravelBill {

    public static void main(String[] args) {

        BigDecimal accomodation = new BigDecimal("1540.85");
        BigDecimal fuel = new BigDecimal("423.10");
        BigDecimal discount = new BigDecimal("100.00");

        BigDecimal total = accomodation.add(fuel);

        BigDecimal afterDiscount = total.subtract(discount);

        BigDecimal perPerson = afterDiscount.divide(new BigDecimal("4"), 2, RoundingMode.HALF_UP);

        System.out.println("Koszt zakwaterowania: " + accomodation + " zł");
        System.out.println("Koszt paliwa: " + fuel + " zł");
        System.out.println("Rabat: " + discount + " zł");
        System.out.println("Łączny koszt po rabacie: " + afterDiscount + " zł");
        System.out.println("Kwota na osobę: " +  perPerson + " zł");
    }
}
