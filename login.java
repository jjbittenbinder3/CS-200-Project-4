import java.util.Scanner;

public class login {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Press o for operator login, p for provider login, and m for manager login");

        String choice = sc.nextLine().trim().toLowerCase();

        if (choice.equals("o")) {
            operatorLogin(sc);
            operatorMenu(sc);
        } else if (choice.equals("p")) {
            providerLogin(sc);
        } else if (choice.equals("m")) {
            managerLogin(sc);

        } else {
            System.out.println("Invalid input.");
        }

        sc.close();
    }

    // -----------------------------------
    // Operator login function
    // -----------------------------------
    public static void operatorLogin(Scanner sc) {
        System.out.print("Enter operator ID: ");
        String id = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if(id.equals("12345")){
            System.out.println("Login succesful");
        }
        else{
            System.out.println("Incorrect username or password, please try again");
            providerLogin(sc);
        }
    }

    // -----------------------------------
    // Provider login function
    // -----------------------------------
    public static void providerLogin(Scanner sc) {
        System.out.print("Enter provider ID: ");
        String id = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.println("Provider login attempt for ID: " + id);
        if(id.equals("12345")){
            System.out.println("Login succesful");
        }
        else{
            System.out.println("Incorrect username or password, please try again");
            providerLogin(sc);
        }
        // Add your validation logic here
    }

    // -----------------------------------
    // Manager login function
    // -----------------------------------
    public static void managerLogin(Scanner sc) {
        System.out.print("Enter manager ID: ");
        String id = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.println("Provider login attempt for ID: " + id);
        if(id.equals("12345")){
            System.out.println("Login succesful");
        }
        else{
            System.out.println("Incorrect username or password, please try again");
            providerLogin(sc);
        }
    }
    public static void operatorMenu(Scanner sc) {
        System.out.println("Please select one of the following options: Add");
        String option = sc.nextLine();
        if(option.equals("Add")){
            addMember(sc);
        }

    }
}

