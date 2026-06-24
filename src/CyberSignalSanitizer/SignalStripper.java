package CyberSignalSanitizer;

import java.util.function.Function;

public class SignalStripper implements Function <String, String> {

    @Override
    public String apply(String s) {
        return s.strip();
    }
}
