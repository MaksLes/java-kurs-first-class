package DiagnosticModuleIoT;

import java.util.Optional;

public class Building {
    private String name;
    private ControlPanel controlPanel;

    public Building(String name, ControlPanel controlPanel){
        this.name = name;
        this.controlPanel = controlPanel;
    }

    public Optional<ControlPanel> getControlPanel(){
        return Optional.ofNullable(controlPanel);
    }
}
