package CyberSignalSanitizer;

import java.util.function.Function;

public class Main {

   public static void main(String[] args) {

       String testSignal = " [NEON-NET]; CONNECTED   ";
       String alertSignal = " [ALERT]; OMNI_CORPL_IS_WATCHING   ";

       //Krok 1: Tradycyjna klasa
       Function<String, String> step1 = new SignalStripper();
       System.out.println("[KROK 1] " + step1.apply(alertSignal));

       //Krok 2: Klasa anonimowa
       Function<String, String> step2 = new Function<String, String>() {
           @Override
           public String apply(String s){
               return s.strip();
           }
       };
       System.out.println("[KROK 2] " + step2.apply(testSignal));

       //Krok 3: Lambda pełna
       Function<String, String> step3 = (String s) -> {
           return s.strip();
       };
       System.out.println("[KROK 3] " + step3.apply(testSignal));

       //Krok 4: Lambda uproszczona
       Function<String, String> step4 = s -> s.strip();
       System.out.println("[KROK 4] " + step4.apply(testSignal));

       //Krok 5: Referencja do metody
       //String::strip to referencja do metody niestatycnej - kompilator wie że parametr lambdy jest obiektem
       //na który strip() zostanie wywołany
       Function<String, String> step5 = String::strip;
       System.out.println("[KROK 5] " + step5.apply(testSignal));

    }
}

/**
 * PYTANIE: Który krok jest najbardziej wydajny w Runtime?
 *
 * Kroki 3, 4, 5 są identycznie wydajne w czasie wykonania.
 *
 * Lambdy i referencje do metod są tłumaczone przez kompilator do instrukcji invokedynamic
 *
 * Krok 1 (klasa) i krok 2 (klasa anonimowa) tworzy osobny obiekt na stercie przy każdym new - minimalny narzut alokacji
 * ale mierzalny
 *
 * Wniosek: kroki 3-5 są nieznacznie wydajniejsze od 1-2 ale między sobą są identyczne
 */
