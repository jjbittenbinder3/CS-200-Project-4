//Author: James Pepper, Jackson Lammons
package controller;

import java.util.Scanner;

import data.MemberService;
import data.ServicesService;
import data.ProviderService;
import data.MemberService;
import data.ServiceRecordService;
import model.Service;
import model.ServiceRecord;
import java.util.Vector;

public class ProviderMenuController {

    private Scanner scanner = new Scanner(System.in);

    public ProviderMenuController() {
        showMenu();
    }

    private void showMenu() {
        int status = new LoginController().login(LoginController.Type.PROVIDER);

        switch (status) {
            case 0:
                System.out.println("Invalid provider ID entered.");
                return;
            case 1:
                System.out.println("Invalid password entered.");
                return;
            default:
                providerMenu(status);
                return;
        }
    }

    // PROVIDER MAIN MENU
    private void providerMenu(int provider) {
        while (true) {
            System.out.println("\n===== Provider Menu =====");
            System.out.println("1. Bill Member");
            System.out.println("2. Generate Provider Directory");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            while (!(choice >= 0 && choice <= 2)) {
                System.out.print("Invalid input. Please enter a valid option: ");
                choice = getIntInput();
            }

            switch (choice) {
                case 1 -> billMember(provider);
                case 2 -> generateReport(provider);
                case 0 -> {
                    System.out.println("Exiting Provider Menu...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void billMember(int provider) {
        MemberService ms = new MemberService();
        int memberId;
        while (true) {
            System.out.println("Enter Member ID to bill: ");
            memberId = getIntInput();
            if (ms.memberExists(memberId)) break;
            System.out.println("Member ID not found. Please try again.");
        }
        if (ms.getMember(memberId).getSuspended() == true) {
            System.out.println("Member account is suspended. Cannot bill this member.");
            return;
        }

        System.out.println("Enter service date: ");
        String serviceDate = scanner.nextLine();

        System.out.println("Enter service code: ");
        String serviceCode = scanner.nextLine();
        ServicesService ss = new ServicesService();
        Service sv = ss.getService(Integer.parseInt(serviceCode));
        if (sv == null) {
            System.out.println("Service code not found. Cannot bill this member.");
            return;
        }

        System.out.println("Service :" + sv.getName() + ". Does this look correct? (Y/N)");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("N")) {
            System.out.println("Billing cancelled by provider.");
            return;
        }

        System.out.println("Enter comments (max 100 characters): ");
        String comments = scanner.nextLine();

        String providerName = findProvider(provider);
        String memberName = findMember(memberId);

        ServiceRecord sr = new ServiceRecord(serviceDate, providerName, memberName, Integer.parseInt(serviceCode), comments);
        ServiceRecordService srs = new ServiceRecordService();
        srs.addServiceRecord(sr);

        
        System.out.println("Fee: " + sv.getFee());
        System.out.println("Service billed successfully.");
    }

    private void generateReport(int provider) {
        ServiceRecordService srs = new ServiceRecordService();
        Vector<ServiceRecord> records = srs.getAllServiceRecords();
        for (ServiceRecord sr : records) {
            String providerName = findProvider(provider);
            if (sr.getProvider().equals(providerName)) {
                int sc = sr.getService();
                ServicesService ss = new ServicesService();
                Service sv = ss.getService(sc);
                System.out.println("Service: " + sv.getName());
                System.out.println("Service Code: " + sv.getCode());
                System.out.println("Fee: " + sv.getFee());
            }
        }
    }

    private int getIntInput() {
        while (true) { 
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }

    private String findProvider(int id) {
        ProviderService ps = new ProviderService();
        return ps.getProvider(id).getName();
    }

    private String findMember(int id) {
        MemberService ms = new MemberService();
        return ms.getMember(id).getName();
    }
}
