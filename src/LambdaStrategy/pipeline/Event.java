package LambdaStrategy.pipeline;

public record Event(String id, String type, String description, boolean isCrititcal) {
}
