package ParkFlow;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class PricingService {

    private final Map<String, UnaryOperator<Double>> pricingStrategies =  new HashMap<>();

    public PricingService(){

        pricingStrategies.put("MOTO", price -> price * 0.5);

        pricingStrategies.put("CAR", price -> price);

        pricingStrategies.put("BUS", price -> price + 20);
    }

    public double calculateFee(double basePrice, String vehcileType){

        UnaryOperator<Double> strategy = pricingStrategies.get(vehcileType);

        if(strategy == null){
            throw new IllegalArgumentException("Brak strategii dla: " +  vehcileType);
        }

        return strategy.apply(basePrice);
    }
}
