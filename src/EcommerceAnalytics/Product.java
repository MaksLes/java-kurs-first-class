package EcommerceAnalytics;

//Record- niemutowalny nośnik danych produktu. Metoda revenue() jest polem wyliczonym, nie przechowywanym
// unikamy niespójności danych. Gdyby price lub sold się zmieniły, revenue zawsze będzie przeliczane na nowo.
public record Product(String id, String name, String category, double price, int stock, int sold, double rating) {

    public double revenue(){
        return price * stock;
    }
}
