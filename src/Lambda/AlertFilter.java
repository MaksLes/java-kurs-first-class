package Lambda;

import java.util.function.Predicate;

public class AlertFilter {

    //Predicate 1: alert jest krytyczny
    public static final Predicate<SystemAlert> isCritical = alert -> alert.severityLevel() >= 5;

    //Predicate 2: alert pochodzi z bazy danych
    public static final Predicate<SystemAlert> isDatabaseAlert =
            alert -> "DATABASE".equals(alert.serviceName());

    //Predicate 3: log zawiera słowo ERROR
    public static final Predicate<SystemAlert> containsError = alert -> alert.logMessage().contains("ERROR");

    public static final Predicate<SystemAlert> requiresImmediateAction =
            isCritical.and(containsError).or(isDatabaseAlert);
}
