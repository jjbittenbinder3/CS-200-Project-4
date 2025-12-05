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
      case 2:
      case 3:
      case 4:
      default:
        break;
    }

    System.out.println("exiting terminal...");
  }
}
