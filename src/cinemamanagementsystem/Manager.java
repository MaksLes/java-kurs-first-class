package cinemamanagementsystem;

public class Manager extends Employee {

    private double bonus;

    public Manager(String name, double baseSalary, double bonus){
        super(name, baseSalary); //
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + bonus;
    }

    public double getBonus() { return bonus; }

    @Override
    public String toString(){
        return String.format("Manager{name='%s', baseSalary=%.2f PLN, bonus=%.2f PLN}", name, baseSalary, bonus);
    }
}
