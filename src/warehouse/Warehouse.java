package warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Warehouse<T extends Product>{

    private final List<T> products = new ArrayList<>();

    public void addProduct(T product){
        products.add(product);
    }

    public void showInventory(){
        System.out.println("\n==== STAN MAGAZYNU ====");
        for(T product : products){
            System.out.println(product);
        }
        System.out.println("Łącznie: " + products.size() + "szt.\n");
    }

    public List<T> getProducts(){
        return Collections.unmodifiableList(products);
    }
}
