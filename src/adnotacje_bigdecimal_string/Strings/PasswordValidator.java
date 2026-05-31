package adnotacje_bigdecimal_string.Strings;

public class PasswordValidator {

    public static String validatePassword(String password){

        String cleanedPassword = password.strip();

        if (cleanedPassword.length() < 8){
            System.out.println("Hasło jest za krótkie.");
        } else {
            System.out.println("Długość hasła jest poprawna.");
        }

        if (!password.equals(cleanedPassword)){
            System.out.println("Usunięto spacje z początku lub końca");
        }

        boolean containsDigitOrSpecial = false;

        for (char c : cleanedPassword.toCharArray()){

            if (Character.isDigit(c) || !Character.isLetterOrDigit(c)){

                containsDigitOrSpecial = true;
                break;

            }
        }

        if (containsDigitOrSpecial){
            System.out.println("Hasło zawiera cyfrę lub znak specjalny.");
        } else {
            System.out.println("Hasło nie zawiera cyfry ani znaku specjalnego.");
        }

        return cleanedPassword;
    }

    public static void main(String[] args) {
        String password = " MojeSuperTajneHaslo123! ";
        String result = validatePassword(password);
        System.out.println("Wyczyszczone hasło: " + result);
    }
}
