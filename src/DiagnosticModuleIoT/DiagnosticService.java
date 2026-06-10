package DiagnosticModuleIoT;

import java.util.Optional;

public class DiagnosticService {

    public void checkFirmwareStatus(Building building){

        //orElseGet() zwraca T a nie Optional<T> więc opakowujemy wynik z powrotem
        Optional.of(

                Optional.ofNullable(building)// Optional<ControlPanel>
                        .flatMap(Building::getControlPanel)// Optional<Gateway>
                        .flatMap(ControlPanel::getGateway)// Optional<String>
                        .flatMap(Gateway::getFirmwareVersion)
                        .filter(v -> !v.isBlank() && v.startsWith("v2."))
                        .orElseGet(() -> {
                            //Symulacja pobierania szablonu domyślnego z repozytorium konfiguracji
                            System.out.println("[CONFIG] Pobranie wersji domyślnej z repozytorium konfiguracji...");
                            return "v.2.0.0-LTS";
                        })
        ).ifPresentOrElse(
                version -> System.out.println("[DIAGNOSTYKA] Wykryta wersja oprogramowania: " + version),
                () -> { throw new IllegalStateException("Błąd krytyczny diagnostyki systemu"); }
        );
    }
}
