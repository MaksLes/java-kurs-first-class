package SpyFlow.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SecretAgent {

    @JsonProperty("agent_alias") //zmienia nazwę pola w wygenerowanym JSON-ie
    private String codename;

    private int missionsCompleted;
    private boolean isActive;

    @JsonIgnore //to pole nigdy nie trafi do serializowanego JSON-a
    private String realName;

    public SecretAgent(String codeName, int missionsCompleted, boolean isActive, String realName) {
        this.codename = codeName;
        this.missionsCompleted = missionsCompleted;
        this.isActive = isActive;
        this.realName = realName;
    }

    //Gettery - Jackson wymaga ich do serializacji
    public String getCodename() { return codename; }
    public int getMissionsCompleted() { return missionsCompleted; }
    public boolean isActive() { return isActive; }
    public String getRealName() { return realName; }
}
