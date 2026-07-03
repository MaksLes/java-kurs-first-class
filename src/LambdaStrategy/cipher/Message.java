package LambdaStrategy.cipher;

//Rekord- niemutowalny nośnik danych z perspektywy bezpieczeństwa istotne, że treść wiadomości nie może zostać
// zmodyfikowana po utworzeniu obiektu

public record Message(String id, String content, int securityLevel) {
}
