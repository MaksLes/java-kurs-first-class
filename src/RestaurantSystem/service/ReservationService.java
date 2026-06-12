package RestaurantSystem.service;
import RestaurantSystem.exception.ReservationValidationException;
import RestaurantSystem.exception.RestaruantException;
import RestaurantSystem.exception.TableAlreadyBookedException;
import RestaurantSystem.model.Table;

import java.util.Objects;

public class ReservationService {

    public void createReservation(String clientName, int guestsCount, Table table){
        //Każdy warunek kończy metodę natychmiast - unkamy głębokiego zagnieżdżania

        Objects.requireNonNull(clientName, "Nazwa klienta nie może być null!");

        if (guestsCount < 1 || guestsCount > 10){
            throw new ReservationValidationException(
                    "Liczba gości musi mieścić się w przedziale 1-10. Podano: " + guestsCount,
                    "INVALID_GUEST_COUNT"
            );
        }

        if (table == null){
            throw new ReservationValidationException(
                    "Obiekt stolika nie może być null!",
                    "NULL_TABLE_REFERENCE"
            );
        }

        try{
            table.book();
            System.out.println("[REZERWACJA] Potwierdzono rezerwację dla: " + clientName
            + " | Goście: " + guestsCount + " | Stolik nr: " + table.getNumber());

        } catch (TableAlreadyBookedException e){
            //Exception Chaining - oryginalny wyjątek trafia jako cause, dzięki czemu
            // wyższe warstwy mają pełen ślad diagnostyczny
            throw new RestaruantException(
                    "Nie udało się utworzyć rezerwacji",
                    "RESERVATION_FAILED", e // <-- zachowanie pierwotnej przyczyny
            );
        }
    }
}
