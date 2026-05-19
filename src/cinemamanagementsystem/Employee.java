package cinemamanagementsystem;

public class Employee {

    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double calculateSalary(){
        return baseSalary;
    }

    //Gettery
    public String getName(){ return name; }
    public double getBaseSalary(){ return baseSalary; }

    @Override
    public String toString(){
        return String.format("Employee{name='%s', baseSalary=%.2f PLN}", name, baseSalary);
    }
}
