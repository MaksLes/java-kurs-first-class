package CoreAlert;

public class ReactorDiagnostics {

    public static String triggerEmergencyCooling(String coolingSystemName){
        System.out.println("[KRYTYCZNE] !!! URUCHAMIAM SYSTEM: " + coolingSystemName + " !!!");
        return "SYSTEM_ACTIVE";
    }
}
