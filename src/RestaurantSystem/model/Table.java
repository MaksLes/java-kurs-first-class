package RestaurantSystem.model;

import RestaurantSystem.exception.TableAlreadyBookedException;

public class Table {

    private final int number;
    private boolean booked;

    public Table(int number) {
        this.number = number;
        this.booked = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return booked;
    }

    public void book(){
        if (booked) {
            throw new TableAlreadyBookedException(number);
        }
        this.booked = true;
        System.out.println("[TABLE] Stolik nr " + number + " został zablokowany w systemie.");
    }
}
