package GenericTaskProcessor;

public interface Task<T> {
    void execute(T data);
}
