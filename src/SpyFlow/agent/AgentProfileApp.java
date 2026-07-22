package SpyFlow.agent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class AgentProfileApp {

    public static void main(String[] args) throws Exception {

        SecretAgent agent = new SecretAgent("Sokół", 27, true, "Marek Kowalski");

        ObjectMapper mapper = new ObjectMapper();
        //Pretty Print - formatowanie JSON-a z wcięciami dla czytelności w konsoli
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper.writeValueAsString(agent);
        System.out.println("=== Profil Agenta (JSON) ===");
        System.out.println(json);
        //realName nie powinno się pojawić w wyniku bo @JsonIgnore blokuje serializację
    }
}
