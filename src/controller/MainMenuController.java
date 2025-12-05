package controller

import java.util.Scanner;

public class MainMenuController {
  public MainMenuontroller() {}
  
  public void showMainMenu() {
    System.out.println("1. Operator Menu");
    System.out.println("2. Provider Menu");
    System.out.println("3. Manager Menu");
    System.out.println("4. Run Accounting");
    System.out.println("5. Exit");
    
    Scanner scanner = new Scanner(System.in);
    int input = scanner.nextInt();

    swtich (input) {
      case 1:
        OperatorMenuController menu = new OperatorMenuController();
        menu.showOperatorMenu();
        break;
      case 2:
        ProviderMenuController menu = new ProviderMenuController();
        menu.showProviderMenu();
        break;
      case 3:
        ManagerMenuController menu = new ManagerMenuController();
        menu.showManagerMenu();
        break;
      case 4:
        // Accounting
      default:
        break;
    }

    System.out.println("exiting terminal...");
  }
}
