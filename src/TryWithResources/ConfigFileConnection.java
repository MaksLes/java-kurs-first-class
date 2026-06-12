package TryWithResources;

public class ConfigFileConnection implements AutoCloseable{

    public ConfigFileConnection(){
        System.out.println("[SYSTEM] Otwarto strumień pliku konfiguracyjnego");
    }

    @Override
    public void close(){
        System.out.println("[SYSTEM] Strumień pliku został automatycznie zamknięty");
    }
}
