package LambdaStrategy.pipeline;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class EventProcessor {

    public void process(List<Event> events, List<Predicate<Event>> filters, Function<Event, String> formatter,
                        Consumer<String> output){

        //Kompozycja predykatów: startujemy od predykatu neutralnego (zawsze true), następnie sklejamy wszystkie filtry
        // metodą .and() w jeden łańcuch logiczny. Wynikowy predykat zwraca true tylko gdy każdy filtr
        // z listy zwróci true.
        Predicate<Event> combinedFilter = e -> true;
        for (Predicate<Event> filter : filters) {
            combinedFilter = combinedFilter.and(filter);
        }

        //Klasyczna pętla - bez stream API
        for (Event event : events) {
            if (combinedFilter.test(event)) {
                String formatted = formatter.apply(event);
                output.accept(formatted);
            }
        }
    }
}
