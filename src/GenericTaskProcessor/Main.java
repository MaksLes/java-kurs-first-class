package GenericTaskProcessor;

public class Main {
    public static void main(String[] args) {
        Task<String> printTask = new StringPrinterTask();
        TaskRunner<String> stringRunner = new TaskRunner<>(printTask, "kurs javy");
        stringRunner.run();

        Task<Integer> squareTask = new NumberSquareTask();
        TaskRunner<Integer> numberRunner = new TaskRunner<>(squareTask, 5);
        numberRunner.run();
    }
}
