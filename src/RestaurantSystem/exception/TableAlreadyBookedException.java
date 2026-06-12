package RestaurantSystem.exception;

public class TableAlreadyBookedException extends RestaruantException {

    private final int tableNumber;

    public TableAlreadyBookedException(int tableNumber) {
        super("Stolik nr " + tableNumber + " jest już zajęty.", "TABLE_ALREADY_BOOKED");
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }
}
