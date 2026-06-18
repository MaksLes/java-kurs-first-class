package GameServerStats;

import GameServerStats.model.PlayerKey;
import GameServerStats.service.GameServerManager;

public class Main {

    public static void main(String[] args) {

        GameServerManager manager = new GameServerManager();

        //Gracze
        PlayerKey kamil = new PlayerKey("UUID-1", "Kamil");
        PlayerKey mariola = new PlayerKey("UUID-2", "Mariola");
        PlayerKey andrzej = new PlayerKey("UUID-3", "Andrzej");

        //Logowanie na serwery
        System.out.println("Logowanie na serwery");
        manager.joinServer("Survival-1", kamil);
        manager.joinServer("Survival-1", mariola);
        manager.joinServer("SkyBlock-3", andrzej);
        manager.joinServer("SkyBlock-3", kamil); // kamil gra na dwóch serwerach

        manager.printLobbies();

        //Punkty
        System.out.println("\nDodawanie punktów");
        manager.addPoints(kamil, 500);
        manager.addPoints(kamil, 300); //merge: 500 + 300 = 800
        manager.addPoints(kamil, 200); //merge; 800 + 200 = 1000
        manager.addPoints(mariola, 750);
        manager.addPoints(andrzej, 600);
        manager.addPoints(andrzej, 400); //merge: 600 + 400 = 1000 (remis z kamilem)

        //Ranking
        manager.printLeaderboards();

    }
}
