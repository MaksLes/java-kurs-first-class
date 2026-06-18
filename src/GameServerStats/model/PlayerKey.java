package GameServerStats.model;

//Dlaczeo record idelanie nadaje się na klucz mapy?

//Record jest niemutowalny wszystkie pola są final i ustawiane tylko w konstruktorze
//kompilator automatycznie generuje poprawne hashCode() i equals() oparte na wartościach pól, a nie na adresie obiektu
// w pamięci

public record PlayerKey(String uuid, String username) {}
