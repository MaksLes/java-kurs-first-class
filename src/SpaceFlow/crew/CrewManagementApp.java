package SpaceFlow.crew;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CrewManagementApp {

    public static void main(String[] args)  throws IOException {

        //krok 1-2: Ścieżka + weryfikacja/utworzenie katalogu
        Path missionControlDir = Path.of("mission_control");

        //Files.exists() nie rzuca wyjątku - zwraca boolean, bezpieczne do sprawdzenia warunku
        if (!Files.exists(missionControlDir)) {
            Files.createDirectory(missionControlDir);
            System.out.println("[SYSTEM] Utworzono katalog: " + missionControlDir.toAbsolutePath());
        } else {
            System.out.println("[SYSTEM] Katalog już istnieje: " +  missionControlDir.toAbsolutePath());
        }

        //Krok 3: Lista astronautów
        List<String> crew = List.of(
                "Adam Małysz",
                "Robert Lewandowski",
                "Maryla Rodowicz",
                "Mateusz Borek",
                "Przemysław Babiarz",
                "Bartłomiej Pawłowski"
        );

        //Krok 4: Zapis do pliku
        Path crewFile = missionControlDir.resolve("crew_roster.txt");

        //Files.write() przyjmuje Path + List<String>, automatycznie tworząc plik lub nadpisując istniejący i zapisując
        //każdy element w osobnej linii
        Files.write(crewFile, crew);
        System.out.println("[SYSTEM] Zapisano listę załogi do: " + crewFile + "\n");

        //Krok 5: Weryfikacja - odczyt + filtrowanie
        List<String> loadedCrew = Files.readAllLines(crewFile);

        System.out.println("Zatwierdzeni astronauci (nazwisko > 5 liter)");

        loadedCrew.stream()
                .filter(fullName -> {
                    //Wyciągamy samo nazwisko - zakładamy format Imię Nazwisko
                    String[] parts =  fullName.split(" ");
                    String surname = parts[parts.length - 1];
                    return surname.length() > 5;
                })
                .forEach(name -> System.out.println("[ZATWIERDZONY] " + name));
    }
}
