/*
  Author: James Pepper & James Zittlow
*/

package controller;

public class ManagerMenuController {
  
  public ManagerMenuController()  {
    showMenu();
  }
  
  private void showMenu() {
    int status = new LoginController().login(LoginController.Type.MANAGER);
    switch (status) {
      case 0:
        System.out.println("Invalid manager ID entered.");
        return;
      case 1:
        System.out.println("Invalid password entered.");
        return;
      case 2:
        int input = 0;
        while (input != 4) {
          System.out.println("1. Member Report");
          System.out.println("2. Provider Report");
          System.out.println("3. Summary Report");
          System.out.println("4. Exit");
          Scanner scanner = new Scanner(System.in);
          input = scanner.nextInt();
          switch (input) {
            case 1:
              MemberReport memRep = new MemberReport();
              System.out.println("Enter Member ID: ");
              int memberID = scanner.nextInt();
              memRep.generateMemberReport(memberID);
              break;
            case 2:
              ProviderReport provRep = new ProviderReport();
              System.out.println("Enter Provider ID: ");
              int providerID = scanner.nextInt();
              provRep.generateProviderReport(providerID);
              break;
            case 3:
              SummaryReport sumRep = new SummaryReport();
              sumRep.generateSummaryReport();
              break;
            case 4:
              return;
          }
        }
        scanner.close();
        return;
    }
  }
}
