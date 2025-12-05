// Author: James Zittlow
package controller;

import java.util.Scanner;
import reports.MemberReport;
import reports.ProviderReport;
import reports.SummaryReport;

public class ManagerMenuController {

    private Scanner scanner = new Scanner(System.in);

    public ManagerMenuController() {
        showMenu();
    }

    private void showMenu() {
        int status = new LoginController().login(LoginController.Type.MANAGER);

        switch (status) {
            case 0 -> {
                System.out.println("Invalid manager ID entered.");
                return;
            }
            case 1 -> {
                System.out.println("Invalid password entered.");
                return;
            }
            case 2 -> managerMenu();
        }
    }

    // MANAGER MAIN MENU
    private void managerMenu() {
        while (true) {
            System.out.println("\n===== Manager Menu =====");
            System.out.println("1. Generate Member Report");
            System.out.println("2. Generate Provider Report");
            System.out.println("3. Generate Summary Report");
            System.out.println("4. Generate EFT Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> generateMemberReport();
                case 2 -> generateProviderReport();
                case 3 -> generateSummaryReport();
                case 4 -> generateEFTReport();
                case 0 -> {
                    System.out.println("Exiting Manager Menu...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // OPTION 1: MEMBER REPORT
    private void generateMemberReport() {
        System.out.print("Enter Member ID: ");
        int id = getIntInput();
        new MemberReport().generateMemberReport(id);
    }

    // OPTION 2: PROVIDER REPORT
    private void generateProviderReport() {
        System.out.print("Enter Provider ID: ");
        int id = getIntInput();
        new ProviderReport().generateProviderReport(id);
    }

    // OPTION 3: SUMMARY REPORT
    private void generateSummaryReport() {
        new SummaryReport().generateSummaryReport();
    }

    // OPTION 4: EFT REPORT
    private void generateEFTReport() {
      EFTReport eftRep = new EFTReport();
      eftRep.generateEFTReport();
    }

    // Safe Int Input
    private int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}
