package Lambda;

@FunctionalInterface
public interface AlertTransformer {
    String transform(String message, String prefix);
}
