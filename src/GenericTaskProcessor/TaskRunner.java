package GenericTaskProcessor;

public class TaskRunner<T> {
    private Task<T> task;
    private T data;

    public TaskRunner(Task<T> task, T data) {
        this.task = task;
        this.data = data;
    }

    public void run(){
        task.execute(data);
    }
}
