package adnotacje_bigdecimal_string.Annotations;

public class MockDashboard {

    @RequiredRole("USER")
    public void viewReports(){
        System.out.println("Wyświetlanie raportów...");
    }

    @RequiredRole("ADMIN")
    public void deleteUsers(){
        System.out.println("Usuwanie użytkowników...");
    }
}
