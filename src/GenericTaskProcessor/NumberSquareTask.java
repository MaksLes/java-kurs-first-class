package GenericTaskProcessor;

public class NumberSquareTask implements Task<Integer> {
    @Override
    public void execute(Integer data) {
        System.out.println("Wynik: " + (data * data));
    }
}
