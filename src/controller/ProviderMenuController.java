/*
  Author: Ryan Gammon and Lucas Cottrell
*/

package controller;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import data.MemberService;
import reports.MemberReport;
import data.ProviderDirectory;
import model.ServiceRecord;
import data.ServiceRecordService;


public class ProviderMenuController {
  private Scanner scanner = new Scanner(System.in);     
  private MemberService memberService = new MemberService();
  ProviderDirectory directory = new ProviderDirectory();
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
        //bill member
        
        //here
    }
  }
    private void providerMenu(int providerID) {
    while (true) {
        System.out.println("\n===== Provider Menu =====");
        System.out.println("1. Bill Member");
        System.out.println("2. Generate Report");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");

        int choice = getIntInput();

        switch (choice) {
            case 1 -> billMember(providerID);
            case 2 -> generateReport();
            case 0 -> {
                System.out.println("Exiting Operator Menu...");
                return;
            }
            default -> System.out.println("Invalid option.");
        }
    }
    }
    // OPTION 1: ADD MEMBER
    private void billMember(int providerID) {
        System.out.println("\n--- Bill Member ---");

        System.out.print("Enter member ID: ");
        String id2 = scanner.nextLine();
        int id = Integer.parseInt(id2);
        boolean exists = memberService.memberExists(id);
        var member = memberService.getMember(id);
        if(exists){
            if (member.getSuspended()) {
                System.out.println("Member suspended.");
                return;
            }
            else{
                System.out.println("Validated");
            }
            
        }
        else{
            System.out.println("Invalid number");
        }

        System.out.print("Enter the date the service was provided in the following format: MM–DD–YYYY: ");
        String provideDate = scanner.nextLine();
        int serviceCode;
        while(true)
        {
            System.out.print("Please enter the appropriate service code: ");
            serviceCode = scanner.nextInt();
            while(directory.enterServiceCode(serviceCode).equals("error"))
            {
                System.out.print("The ID you entered is not a valid service code. Please try again: ");
                serviceCode = scanner.nextInt();
            }
            String activity = directory.enterServiceCode(serviceCode);
            System.out.println("The service provided was: " + activity + " ,is this correct?(y/n)");
            String response = scanner.nextLine();
            if(response.equals("n")){
                continue;
            }
            else{
                break;
            }

        }
        System.out.println("Optional: You may enter a comment to attach to this record (100 character limit). Press Enter to continue: ");
        String comment = scanner.nextLine();
        comment = comment.substring(0, 100);
        String provider = String.valueOf(providerID);
        String newID = String.valueOf(id);
        ServiceRecord sr = new ServiceRecord(provideDate, provider, newID, serviceCode, comment);
        ServiceRecordService srs = new ServiceRecordService();
        srs.addServiceRecord(sr);
    }
    // OPTION 2: REMOVE MEMBER
    private void generateReport(){
        Path filePath = Paths.get("data", "emails");
        try {
            directory.sendDirectoryRequest(filePath);
        } catch (Exception e) {
            System.out.println("Error generating report: " + e.getMessage());
            return;
        }
        
    }
    // OPTION 3: UPDATE MEMBER


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

