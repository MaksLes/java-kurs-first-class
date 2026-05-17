public class RobotManager {

    //1. Przeciążanie - Metoda do ładowania o konkretną liczbę jednostek
    public static int charge(int currentEnergy, int energyToAdd){
        int updatedEnergy = currentEnergy + energyToAdd;

        //Zwracamy sumę, ale ograniczamy ją do 100 za pomocą Math.min
        return Math.min(updatedEnergy, 100);
    }

    //2. Przeciążanie - Metoda ładowania "do pełna"
    public static int charge(int currentEnergy, boolean fullCharge){
        if (fullCharge) {
            return 100;
        }
        return currentEnergy;
    }

    //3. Rekurencja - Obliczanie zużycia energii na odwrót
    public static int calculateReturnPath(int distance){
        if (distance <= 0){
            return 0;
        }
        return distance + calculateReturnPath(distance - 1);
    }

    //4. SRP (Single Responsibility Principle) Metoda procesowa zarządzająca logiką robota
    public static void performDailyRoutine(){
        int energy = 20;
        System.out.println("=== STATUS ROBOTA ===");
        System.out.println("Energia początkowa: " + energy + "%");

        //Wywołanie charge (przeciążona wersja z liczbą jednostek)
        energy = charge(energy, 35);
        System.out.println("Energia po doładowaniu jednostek (+35): " + energy + "%");

        //Symulacja ładowania do pełna przed długą trasą
        energy = charge(energy, true);
        System.out.println("Energia po naładowaniu do pełna: " + energy + "%");

        //Obliczanie kosztu powrotu dla dystansu 5
        int distanceToBase = 5;
        int energyNeeded = calculateReturnPath(distanceToBase);

        System.out.println("Planowany dystans powrotny: " + distanceToBase);
        System.out.println("Koszt energii dla tej trasy: " + energyNeeded);

        //Finalne sprawdzenie zasobów
        if (energy >= energyNeeded){
            System.out.println("Status: Powrót do bazy możliwy.");
        } else {
            System.out.println("Status: UWAGA! Niewystarczająca ilość energii.");
        }
    }

    public static void main(String[] args) {
        performDailyRoutine();
    }
}
