package CinemaFlow;

import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //Zadanie 1: Code Review
        /**
         * Wyjątek: IllegalStateException
         * Strumień jest jednorazowy: anyMatch() zamyka movieStream, a druga operaja count na tym samym obiekcie rzuca
         * wyjątek. println z totalMovies nigdy się nie wykonuje
         *
         * Poprawka: budować strumień od nowa dla każdej operacji albo zebrać dane do listy:
         * List<Movie> movies = moviesList.stream().peek(...).toList();
         * boolean isAnyLong = movies.stream().anyMatch(...);
         * long totalMovies = movies.size()
         */

        //Przygotowanie danych
        List<Cinema> cinemas = List.of(
                new Cinema("Warszawa", List.of(
                        new Movie("Diuna: cz. 3", "SciFi", 165, 8.4),
                        new Movie("Shrek 6", "Animacja", 88, 8.1),
                        new Movie("Nokturn", "Horror", 118, 6.8)
                )),
                new Cinema("Łódź",  List.of(
                        new Movie("Diuna: cz. 3", "SciFi", 165, 8.4),
                        new Movie("Toy Story 5", "Animacja", 92, 7.9),
                        new Movie("Ostatni Bastion", "Akcja", 142, 7.2)
                )),
                new Cinema("Kraków", List.of(
                        new Movie("Król Lew", "Animacja", 95, 8.6),
                        new Movie("Cisza przed Świtem", "Thriller", 121, 7.5)
                ))
        );

        zadanie2A_uniqueCategories(cinemas);
        zadanie2B_averageRating(cinemas);

        List<Movie> allMovies = cinemas.stream()
                .flatMap(cinema -> cinema.movies().stream())
                .toList();

        zadanie3A_genreReport(allMovies);
        zadanie3B_hitsPartition(allMovies);
        zadanie4_shortestMoviesReduce(allMovies);
    }

    //Zadanie 2A: Unikalne kategorie
    private static void zadanie2A_uniqueCategories(List<Cinema> cinemas) {
        System.out.println("2A) Unikalne kategorie w sieci");

        //flatMap: Stream<Cinema> -> Stream<Movie>
        //toSet() automatycznie eliminuje duplikaty kategorii między kinami
        Set<String> categories = cinemas.stream()
                .flatMap(cinema -> cinema.movies().stream())
                .map(Movie::category)
                .collect(Collectors.toSet());

        System.out.println(" " + categories + "\n");
    }

    //Zadanie 2B Średnia ocen sieci
    private static void zadanie2B_averageRating(List<Cinema> cinemas) {
        System.out.println("2B) Średnia ocen wszystkich filmów w sieci");

        //mapToDouble konwertuje na DoubleStream - average() zwraca OptionalDouble, bo pusta sieć kin nie ma matematycznie
        // zdefiniowanej średniej
        OptionalDouble avgRating = cinemas.stream()
                .flatMap(cinema -> cinema.movies().stream())
                .mapToDouble(Movie::rating)
                .average();

        avgRating.ifPresentOrElse(
                avg -> System.out.printf(" Średnia ocena %.2f%n%n", avg),
                () -> System.out.println(" Brak filmów w sieci.\n")
        );
    }

    //Zadanie 3A Raport gatunkowy
    private static void zadanie3A_genreReport(List<Movie> allMovies) {
        System.out.println("3A) Raport gatunkowy (liczba filmów per kategoria)");

        Map<String, Long> genreReport = allMovies.stream()
                .collect(Collectors.groupingBy(Movie::category, Collectors.counting()));

        genreReport.forEach((category, count) ->
                System.out.println(" " + category + ": " + count));
        System.out.println();
    }

    //Zadanie 3B Kategoryzacja hitów
    private static void zadanie3B_hitsPartition(List<Movie> allMovies) {
        System.out.println("3B) Hity (rating >= 8.0) vs pozostałe");

        //partitioningBy dzieli na dokładnie dwie grupy (true/false klucze), Collectors.mapping jako downstream wyciąga
        //tylko title nie cały obiekt Movie
        Map<Boolean, List<String>> partitioned = allMovies.stream()
                .collect(Collectors.partitioningBy(
                        movie -> movie.rating() >= 8.0,
                        Collectors.mapping(Movie::title, Collectors.toList())
                ));

        System.out.println("Hity: " + partitioned.get(true));
        System.out.println("Pozostałe: " +  partitioned.get(false) + "\n");
    }

    //Zadanie 4 Własna redukcja
    private static void zadanie4_shortestMoviesReduce(List<Movie> allmovies) {
        System.out.println("4) SMS - 3 najkrótsze filmy");

        //sorted() rosnąco po czasie trwania, limit(3) bierze tylko trzy pierwsze, reduce bez identity zwraca
        //Optional<String>. Pusta lista nie ma wyniku
        var result = allmovies.stream()
                .sorted((m1, m2) -> Integer.compare(m1.durationMin(), m2.durationMin()))
                .limit(3)
                .map(Movie::title)
                .reduce((title1, title2) -> title1 + " - " + title2);

        result.ifPresentOrElse(
                sms -> System.out.println(" SMS: " + sms),
                () -> System.out.println(" Brak filmów do kampanii SMS.")
        );
    }

}
