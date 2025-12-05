//Author: James Pepper, Jackson Lammons
package controller;

import data.MemberService;
import reports.MemberReport;

import java.util.Scanner;

public class OperatorMenuController {

    private MemberService memberService = new MemberService();
    private Scanner scanner = new Scanner(System.in);

    public OperatorMenuController() {
        showMenu();
    }

    private void showMenu() {
        int status = new LoginController().login(LoginController.Type.OPERATOR);

        switch (status) {
            case 0:
                System.out.println("Invalid operator ID entered.");
                return;
            case 1:
                System.out.println("Invalid password entered.");
                return;
            case 2:
                operatorMenu();
                return;
        }
    }
    // OPERATOR MAIN MENU
    private void operatorMenu() {
        while (true) {
            System.out.println("\n===== Operator Menu =====");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Update Member");
            System.out.println("4. Validate Member");
            System.out.println("5. Generate Member Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> removeMember();
                case 3 -> updateMember();
                case 4 -> validateMember();
                case 5 -> generateMemberReport();
                case 0 -> {
                    System.out.println("Exiting Operator Menu...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
    // OPTION 1: ADD MEMBER
    private void addMember() {
        System.out.println("\n--- Add Member ---");

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("ID (9 digits): ");
        int id = getIntInput();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Address: ");
        String address = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("State: ");
        String state = scanner.nextLine();

        System.out.print("ZIP: ");
        String zip = scanner.nextLine();

        boolean suspended = false;

        var m = new model.Member(name, id, password, suspended);
        m.setAddress(address, city, state, zip);

        memberService.addMember(m);

        System.out.println("Member added successfully.");
    }
    // OPTION 2: REMOVE MEMBER
    private void removeMember() {
        System.out.println("\n--- Remove Member ---");

        System.out.print("Enter Member ID: ");
        int id = getIntInput();

        if (!memberService.memberExists(id)) {
            System.out.println("Member does not exist.");
            return;
        }

        memberService.removeMember(id);
        System.out.println("Member removed.");
    }
    // OPTION 3: UPDATE MEMBER
    private void updateMember() {
        System.out.println("\n--- Update Member ---");

        System.out.print("Enter Member ID: ");
        int id = getIntInput();

        var member = memberService.getMember(id);
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.print("New Name: ");
        member.setInfo(scanner.nextLine(), id, member.getPassword());

        System.out.print("New Address: ");
        String address = scanner.nextLine();

        System.out.print("City: ");
        String city = scanner.nextLine();

        System.out.print("State: ");
        String state = scanner.nextLine();

        System.out.print("ZIP: ");
        String zip = scanner.nextLine();

        member.setAddress(address, city, state, zip);

        memberService.updateMember(member);

        System.out.println("Member updated.");
    }
    // OPTION 4: VALIDATE MEMBER
    private void validateMember() {
        System.out.println("\n--- Validate Member ---");

        System.out.print("Enter Member ID: ");
        int id = getIntInput();

        var member = memberService.getMember(id);

        if (member == null) {
            System.out.println("Invalid number.");
            return;
        }

        if (member.getSuspended()) {
            System.out.println("Member suspended.");
            return;
        }

        System.out.println("Validated.");
    }
    // OPTION 5: MEMBER REPORT
    private void generateMemberReport() {
        System.out.print("Enter Member ID: ");
        int id = getIntInput();

        new MemberReport().generateMemberReport(id);
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
