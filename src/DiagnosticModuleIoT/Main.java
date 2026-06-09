package DiagnosticModuleIoT;

public class Main {
    public static void main(String[] args) {

        DiagnosticService service = new DiagnosticService();

        System.out.println("Test 1: poprawna wersja v2.x");
        service.checkFirmwareStatus(
                new Building("HQ", new ControlPanel(new Gateway("v2.1.4")))
        );
        //[DIAGNOSTYKA] Wykryta wersja oprogramowania: v2.1.4

        System.out.println("\n Test 2: firmware v1.x - nie przejdzie filtra");
        service.checkFirmwareStatus(
                new Building("Branch", new ControlPanel(new Gateway("v1.9.0")))
        );
        //fallback -> [DIAGNOSTYKA] Wykryta wersja oprogramowania: v2.0.0-LTS

        System.out.println("\n Test 3: blank wersja - uszkodzony czujnik");
        service.checkFirmwareStatus(
                new Building("Factory", new ControlPanel(new Gateway(" ")))
        );
        //fallback -> [DIAGNOSTYKA] Wykryta wersja oprogramowania: v2.0.0-LTS

        System.out.println("\n Test 4: brak ramki sieciowej");
        service.checkFirmwareStatus(
                new Building("Warehouse", new ControlPanel(null))
        );
        //fallback -> [DIAGNOSTYKA] Wykryta wersja oprogramowania: v2.0.0-LTS

        System.out.println("\n Test 5: brak panelu sterowania");
        service.checkFirmwareStatus(
                new Building("Annex", null)
        );
        //fallback -> [DIAGNOSTYKA] Wykryta wersja oprogramowania: v2.0.0-LTS

        System.out.println("\n Test 6: null building");
        service.checkFirmwareStatus(null);
        //fallback -> [DIAGNOSTYKA] Wykryta wersja oprogramowania: v2.0.0-LTS

    }
}
