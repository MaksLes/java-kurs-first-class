package SpyFlow.interception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EnemyInterceptionApp {

    public static void main(String[] args) throws IOException {


        //Tworzymy plik testowy automatycznie żeby program dało się uruchomić od razu
        Path jsonFile = Path.of("enemy_bases.json");
        if (!Files.exists(jsonFile)) {
            String sampleJson = """
                    [
                      {
                        "baseId": "ALPHA-01",
                        "location": "Syberia",
                        "threatLevel": 8,
                        "encrypted_hash": "x8f92ma!11"
                      },
                      {
                        "baseId": "OMEGA-99",
                        "location": "Pacyfik",
                        "threatLevel": 10,
                        "encrypted_hash": "z00021!bb"
                      }
                    ]
                    """;
            Files.writeString(jsonFile, sampleJson);
            System.out.println("[SYSTEM] Utworzono przykładowy plik enemy_bases.json\n");
        }

        ObjectMapper mapper = new ObjectMapper();

        //wyłączamy domyślne rzucanie wyjątku przy napotkaniu pola JSON-a, którego nie ma w klasie docelowej (encrypted_hash).
        //Bez tej linii mapper.readValue() rzuciłby UnrecognizedPropertyException
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //TypeReference<List<EnemyBase>> jest niezbędne, ponieważ typ generyczny List<EnemyBase> jest usuwany w runtime
        // zwykłe EnemyBase.class nie przekazałoby informacji że mapujemy listę obiektów
        List<EnemyBase> bases = mapper.readValue(
                new File(jsonFile.toString()),
                new TypeReference<List<EnemyBase>>() {}
        );

        System.out.println("=== Przechwycone bazy wroga ===");
        bases.forEach(base -> System.out.println(" " + base));

        //Weryfikacja: lokalizacje baz o threatLevel > 8
        System.out.println("\n=== Bazy o poziomie zagrożenia > 8 ===");

        bases.stream()
                .filter(base -> base.threatLevel() > 8)
                .map(EnemyBase::location)
                .forEach(location -> System.out.println(" " + location));
    }
}
