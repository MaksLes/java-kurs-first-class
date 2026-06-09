package DiagnosticModuleIoT;

import java.util.Optional;

public class ControlPanel {
    private Gateway gateway;

    public ControlPanel(Gateway gateway){
        this.gateway = gateway;
    }

    public Optional<Gateway> getGateway(){
        return Optional.ofNullable(gateway);
    }
}
