package LambdaStrategy.cipher;

//Własny interfejs funkcyjny - kontrakt strategii szyforwania.
//Adnotacja @FunctionalInterface wymusza dokłanie jedną metodę abstrakcyjną - kompilator zablokuje przypadkowe dodanie
//drugiej metody, chroniąc kontrakt

@FunctionalInterface
public interface EncryptionStrategy {
    String encrypt(String text);
}
