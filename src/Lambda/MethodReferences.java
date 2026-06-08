package Lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReferences {

    public static void demonstrate() {

        Function<String, String> toUpper = String::toUpperCase;
        Comparator<Integer> compare = Integer::compare;
        Function<User, String> getUsername = User::username;
        Supplier<ArrayList<String>> newList = ArrayList::new;
        Consumer<String> print = System.out::println;
    }
}
