package adnotacje_bigdecimal_string.Annotations;

import java.lang.reflect.Method;

public class PermissionChecker {
    public static void main(String[] args) {

        String userRole = "USER";

        MockDashboard dashboard = new MockDashboard();

        Method[] methods = MockDashboard.class.getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(RequiredRole.class)) {
                RequiredRole annotation = method.getAnnotation(RequiredRole.class);

                String requiredRole = annotation.value();

                System.out.println("\nMetoda: " + method.getName());
                System.out.println("Wymagana rola: " + requiredRole);

                if (!userRole.equals(requiredRole)) {
                    System.out.println("Dostęp przyznany");

                    try{
                        method.invoke(dashboard);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Brak uprawnień");
                }
            }
        }
    }
}
