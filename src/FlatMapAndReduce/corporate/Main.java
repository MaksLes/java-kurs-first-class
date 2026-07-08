package FlatMapAndReduce.corporate;

import java.util.List;
import java.util.OptionalDouble;

public class Main {

    public static void main(String[] args) {

        Department deptIT = new Department("IT", List.of(
                new Employee("Tomasz", 34, 9500.0),
                new Employee("Ewa", 29, 8700.0),
                new Employee("Aleksandra", 41, 12300.0)
        ));

        Department deptHR = new Department("HR", List.of(
                new Employee("Jan", 38, 7200.0),
                new Employee("Marta", 26, 6800.0)
        ));

        List<Department> company = List.of(deptIT, deptHR);

        System.out.println("A) Wszyscy pracownicy (alfabetycznie)");
        company.stream()
                .flatMap(dept -> dept.employees().stream())
                .map(Employee::name)
                .sorted()
                .forEach(name -> System.out.println(" " + name));

        System.out.println("\nB) Najwyższa pensja w firmie");
        OptionalDouble topSalary = company.stream()
                .flatMap(dept -> dept.employees().stream())
                .mapToDouble(Employee::salary)
                .max();

        topSalary.ifPresentOrElse(
                salary -> System.out.println(" Najwyższa pensja " + salary + " zł"),
                () -> System.out.println(" Brak danych o pracownikach.")
        );

        System.out.println("\nC) Agregator redukujący (reduce)");

        String identity = "[KADRY] -> ";

        String report = company.stream()
                .flatMap(dept -> dept.employees().stream())
                .map(Employee::name)
                .reduce(identity, (partial, name) -> partial.equals(identity) ? partial + name
                        : partial + " | " + name);

        System.out.println(" " + report);
    }
}
