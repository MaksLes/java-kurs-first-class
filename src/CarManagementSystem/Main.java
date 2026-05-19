package CarManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Tworzymy listę pojazdów - deklaracja przez interfejs List
        List<Vehicle> fleet = new ArrayList<>();

        //Dodajemy pojazdy do floty
        fleet.add(new Car("Toyota", "Corolla"));
        fleet.add(new Car("BMW", "M4", "Premium-Service"));
        fleet.add(new Tesla("Model S"));

        System.out.println("===== System Zarządzania Flotą Pojazdów =====\n");

        //iteracja po flocie - polimorficzne wywołania
        for (Vehicle vehicle : fleet) {


            //Wypisanie opisu pojazdu (przesłonięcie toString())
            System.out.println(vehicle);

            //Polimorficzne wywołanie drive()
            vehicle.drive();

            //Sprawdzenie czy pojazd jest elektryczny
            if (vehicle instanceof Electric electricVehicle){
                electricVehicle.charge();
            }

            System.out.println(); //Separator wizualny
        }

        System.out.println("===== Koniec przeglądu floty =====");
    }
}
