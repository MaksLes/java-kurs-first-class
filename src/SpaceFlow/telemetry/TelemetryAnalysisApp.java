package SpaceFlow.telemetry;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;


public class TelemetryAnalysisApp {

    public static void main(String[] args) throws IOException {

        Path logFile = Path.of("telemetry.log");

        //Zabezpieczenie - jeśli plik testowy nie istnieje, tworzymy go automatycznie, żeby program dało się uruchomić
        // od razu bez ręcznego przygotowania danych
        if (!Files.exists(logFile)) {
            List<String> sampleData = List.of(
                    "2026-07-14T18:00;ENGINE_TEMP;OK;85.5",
                    "2026-07-14T18:05;HULL_PRESSURE;WARNING;102.1",
                    "2026-07-14T18:10;ENGINE_TEMP;CRITICAL;120.5",
                    "2026-07-14T18:15;SOLAR_PANEL;OK;99.0",
                    "2026-07-14T18:20;OXYGEN_LEVEL;CRITICAL;15.2"
            );
            Files.write(logFile, sampleData);
            System.out.println("[SYSTEM] Utworzono przykładowy plik telemetry.log\n");
        }

        processTelemetryStandard(logFile);
        processTelemetryStreaming(logFile); //BONUS
    }

    //Zadanie główne: leniwy odczyt + zbiórka + zapis
    private static void processTelemetryStandard(Path logFile) throws IOException {

        Path outputFile = Path.of("critical_alerts.txt");

        //try-with-resources na dwóch zasobach jednocześnie - Files.lines() (odczyt)
        //oraz BufferedWriter (zapis). Java zamyka je automatycznie w odwrotnej kolejności otwarcia, nawet jeśli
        // wystąpi wyjątek w trakcie przetwarzania
        try (Stream<String> lines = Files.lines(logFile, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {

            //Krok 1: filtrowanie linii ze statusem CRITICAL
            //Krok 2: wyciąganie samej nazwy systemu przez split(";")
            List<String> criticalSystems = lines
                    .filter(line -> line.contains("CRITICAL"))
                    .map(line -> line.split(";")[1]) // indeks 1= nazwa systemu
                    .toList();

            //Zapis zebranej listy do pliku - jedna nazwa systemu na linię
            for (String system : criticalSystems) {
                writer.write(system);
                writer.newLine();
            }

            System.out.println("Zapisano " + criticalSystems.size()
                    + " krytycznych alertów do: " + outputFile);
            criticalSystems.forEach(s -> System.out.println(" ->" + s));
        }
        //Files.lines() otwiera realny uchwyt o pliku w systemie operacyjnym. Bez try-with-resources uchwyt pozostałby
        //otwarty do czasu Garbage Collectora, co przy wielu wywołaniach prowadzi do wyczerpania limitu deskryptorów
        //systemowych
    }

    //Bonus: zapis w locie bez zbierania do List<String>
    private static void processTelemetryStreaming(Path logFile) throws IOException {

        Path outputFile = Path.of("critical_alerts_streaming.txt");

        //owijamy wywołanie w try-catch wewnątrz lambdy, konwertując checked IOException na unchecked RuntimeException
        //jest to standardowy wzorzec przy łączeniu Stream API z IO
        try (Stream<String> lines = Files.lines(logFile, StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            lines.filter(line -> line.contains("CRITICAL"))
                    .map(line -> line.split(";")[1])
                    .forEach(system -> {
                        try {
                            writer.write(system);
                            writer.newLine();
                        } catch (IOException e) {
                            //Przepakowanie na unchecked pozwala przerwać cały strumień, w razie błedu zapisu zamiast
                            //go zignorować
                            throw new RuntimeException("Błąd zapisu podczas przetwarzania strumieniowego", e);
                        }
                    });

            System.out.println("\n Zapisano strumieniowo (bez pośredniej listy) do: " + outputFile);
        }
    }
}
