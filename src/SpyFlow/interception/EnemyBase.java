package SpyFlow.interception;

//Rekord tylko z trzema polami - Jackson domyślnie próbowałby zmapować każde pole JSON-a na pole klasy
public record EnemyBase(String baseId, String location, int threatLevel) {
}
