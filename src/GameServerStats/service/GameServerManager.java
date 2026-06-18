package GameServerStats.service;

import GameServerStats.model.PlayerKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameServerManager {

    //Lobby: nazwa serwera -> lista zalogowanych graczy
    private final Map<String, List<PlayerKey>> serverLobbies = new HashMap<>();

    //Ranking globalny: gracz -> suma punktów
    private final Map<PlayerKey, Integer> leaderboards = new HashMap<>();

    //Dołączanie do serwera

    public void joinServer(String serverName, PlayerKey player){
        //computeIfAbsent: jeśli klucz istnieje - tworzy nową ArrayList i ją zwraca, jeśli istnieje - zwraca istniejącą
        // listę. Wszystko w jednej linii bez if.
        serverLobbies.computeIfAbsent(serverName, key -> new ArrayList<>()).add(player);
        System.out.println("[LOBBY] " + player.username() + " dołączył do serwera: " + serverName);
    }

    //Dodawanie punktów

    public void addPoints(PlayerKey player, int points){
        leaderboards.merge(player, points, Integer::sum);
        System.out.println("[PUNKTY] " + player.username() + " +'" + points + " pkt (łącznie: "
                + leaderboards.get(player) + ")");
    }

    //Wyświetlanie rankingu

    public void printLeaderboards(){
        System.out.println("\n|-------------------------------------------------------|");
        System.out.println("|                GLOBALNY RANKING GRACZY                |");
        System.out.println("|-------------------------------------------------------|");

        //entrySet() - jedna interacja, klucz i wartość dostępne jednocześnie
        leaderboards.entrySet().stream()
                .sorted(Map.Entry.<PlayerKey, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("|  Gracz: %-10s (UUID: %-6s) -> Punkty: %d%n",
                        entry.getKey().username(),
                        entry.getKey().uuid(),
                        entry.getValue()));
        System.out.println("|--------------------------------------------------------|");
    }

    //Wyświetlanie Lobby

    public void printLobbies(){
        System.out.println("\n|-----------------------------------------------------------|");
        System.out.println("|                         STAN SERWERÓW                     |");
        System.out.println("|-----------------------------------------------------------|");

        //entrySet() - ten sam powód co wyżej
        for (Map.Entry<String, List<PlayerKey>> entry : serverLobbies.entrySet()) {
            System.out.println("|   Serwer: " + entry.getKey());
            entry.getValue().forEach(p ->
                    System.out.println("|    ->" + p.username() + " (" + p.uuid() + ")"));
        }
        System.out.println("|------------------------------------------------------------|");
    }
}
