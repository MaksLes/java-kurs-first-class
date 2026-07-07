package ParkFlow;

import java.util.function.Predicate;

public class TicketValidator {

    public static Predicate<String> isNotNullOrBlank =
            ticket -> ticket != null && !ticket.isBlank();

    public static Predicate<String> hasValidPrefix =
            ticket -> ticket != null && ticket.startsWith("PARK-");

    public static Predicate<String> isInternalTest =
            ticket -> "TEST-000".equals(ticket);

    public static Predicate<String> isValidTicket =
            isNotNullOrBlank
                    .and(hasValidPrefix)
                    .or(isInternalTest);
}
