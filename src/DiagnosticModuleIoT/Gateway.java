package DiagnosticModuleIoT;

import java.util.Optional;

public class Gateway {
    private String firmwareVersion;

    public Gateway(String firmwareVersion){
        this.firmwareVersion = firmwareVersion;
    }

    public Optional<String> getFirmwareVersion(){
        return Optional.ofNullable(firmwareVersion);
    }
}
