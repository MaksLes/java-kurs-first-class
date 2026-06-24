package TreeSetTrap;

import TreeSetTrap.model.Player;

import java.util.Comparator;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        demonstrateBug();
        demonstrateFix();
    }

    // Część 1 - reprodukcja błędu

    private static void demonstrateBug() {
        System.out.println("[BŁĄD] Komparator jednokolumnowy (tylko score)");

        //TreeSet używa komparatora wyłącznie do oceny unikalności
        //Gdy Comparator zwróci 0, TreeSet uznaje obiekty za duplikaty i odrzuca drugi element
        TreeSet<Player> buggyLeaderboard = new TreeSet<>(
                Comparator.comparingInt(Player::getScore).reversed()
        );

        buggyLeaderboard.add(new Player("Michał", 1500));
        buggyLeaderboard.add(new Player("Monika", 1200));
        buggyLeaderboard.add(new Player("Aleksandra", 1200));
        buggyLeaderboard.add(new Player("Maksymilian", 900));

        System.out.println("Oczekiwano 4 graczy, w zbiorze jest: " + buggyLeaderboard.size());
        System.out.println("Aleksandra zniknęła - komparator zwrócił 0 dla Monika vs Aleksandra,");
        System.out.println(" TreeSet potraktował to jako: 'ten obiekt już istnieje')\n");

        printLeaderboard(buggyLeaderboard);
    }

    // Część 2 - poprawka

    private static void demonstrateFix() {
        System.out.println("\nPOPRAWKA: Komparator wielokolumnowy (score + nickname)");

        //przechodzimy do porównania po nickname alfabetycznie. Teraz komparator nigdy nie zwróci 0
        // dla dwórch różnych graczy (chyba że mają identyczny nickname i wynik - co jest faktycznym duplikatem)
        TreeSet<Player> fixedLeaderboard = new TreeSet<>(
                Comparator.comparingInt(Player::getScore).reversed().thenComparing(Player::getNickname)
        );

        fixedLeaderboard.add(new Player("Michał", 1500));
        fixedLeaderboard.add(new Player("Monika", 1200));
        fixedLeaderboard.add(new Player("Aleksandra", 1200)); // tym razem zostanie zachowana
        fixedLeaderboard.add(new Player("Maksymilian", 900));

        System.out.println("Graczy w zbiorze: " +  fixedLeaderboard.size() + " oczekiwano: 4\n");

        printLeaderboard(fixedLeaderboard);

    }

    //Helper
    private static void printLeaderboard(TreeSet<Player> leaderboard) {
        System.out.println("|----------------------------------|");
        System.out.println("|           TABELA LIDERÓW         |");
        System.out.println("|----------------------------------|");
        int rank = 1;
        for (Player p : leaderboard) {
            System.out.printf("|  %-3d  |  %s%n", rank++, p);
        }
        System.out.println("|--------------------------------- |");
    }

    // Pytanie:
    // Dlaczego HashSet nie miałby problemu ze znikaniem graczy o tym samym wyniku, a TreeSet go miał? Czym różni się
    // weryfikacja unikalności w tych dwóch kolekcjach?

    // Odpowiedź:
    //Hashset sprawdza unikalność przez hashCode() + equals() domyślnie oparte na adresie obiektu w pamięci
    //Każdy newPlayer to nowy adres, więc każdy gracz jest unikalny niezależnie od wyniku
    //
    //TreeSet sprawdza unikalność wyłącznie przez komparator, jeśli compare(a, b) == 0, uznaje obiekty za duplikat
    // i odrzuca drugi bez żadnego wyjątku nie patrząc wcale na equals() ani hashcode().
}
