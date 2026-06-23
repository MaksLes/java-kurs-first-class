package aeroflow.service;

import aeroflow.model.Aircraft;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FlightControl {

    //Klucz: nazwa bramki wartość: lista nazwisk pasażerów
    private final Map<String, List<String>> gateBoardingList = new HashMap<>();

    //Kolejka priorytetowa - samolot o najwyższym emergencyLevel startuje pierwszy
    private final PriorityQueue<Aircraft> takeoffQueue;

    public FlightControl() {
        //Komparator malejący: reversed() odwraca domyślny porządek rosnący
        takeoffQueue = new PriorityQueue<>(
                Comparator.comparingInt(Aircraft::emergencyLevel).reversed()
        );
    }

    //Grupowanie pasażerów przy bramkach
    public void assignPassengerToGate(String gate, String passengerName){
        //computeIfAbsent tworzy nową ArrayList tylko gdy bramka nie istnieje w mapie, następnie od razu zwraca listę
        // i dodaje pasażera
        gateBoardingList.computeIfAbsent(gate, key -> new ArrayList<>()).add(passengerName);
        System.out.println("[BRAMKA] " + passengerName + " -> " + gate);
    }

    public void printGates(){
        System.out.println("\nStan Bramek");
        //entrySet() - klucz i lista dostępne jednocześnie bez podwójnego get()
        gateBoardingList.entrySet().forEach(entry ->
                System.out.println(" " + entry.getKey() + ": " + entry.getValue()));
    }

    //Kolejka startowa
    public void registerDeparture(Aircraft aircraft){
        takeoffQueue.offer(aircraft);
        System.out.println("[REJESTRACJA] " + aircraft.flightNumber() + " (priorytet: " + aircraft.emergencyLevel() + ")");
    }

    public void processTakeoffs(){
        System.out.println("\nKolejność startów");
        //poll() zwraca null gdy kolejka jest pusta
        Aircraft aircraft;
        while ((aircraft = takeoffQueue.poll()) != null){
            System.out.println("[WIEŻA] Zezwalam na start: " +  aircraft.flightNumber()
            +  " (Priorytet: " + aircraft.emergencyLevel() + ")");
        }
    }

}
