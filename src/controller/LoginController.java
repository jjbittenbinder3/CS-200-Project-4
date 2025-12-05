/*
  Author: James Pepper
*/
package controller;

import data.*;
import model.*;
import java.util.Scanner;

public class LoginController {
  
  public enum Type {
    MEMBER,
    OPERATOR,
    PROVIDER,
    MANAGER
  }
  
  public LoginController() {}

  /*
    return 0 = not found
    return 1 = password invalid
    return 2 = valid
  */
  public int login(Type loginType) {
    Scanner scan = new Scanner(System.in);
    int inID = 0;
    String inPass = "";
    
    switch (loginType) {
      case MEMBER:
        System.out.print("Enter Member ID: ");
        inID = Integer.parseInt(scan.nextLine());
        Member mem = new MemberService().getMember(inID);
        if (mem == null) return 0;
          System.out.print("Enter password: ");
          inPass = scan.nextLine();
        if (!mem.getPassword().equals(inPass)) return 1;
        return 2;
        
      case OPERATOR:
        System.out.print("Enter Operator ID: ");
        inID = Integer.parseInt(scan.nextLine());
        Operator op = new OperatorService().getOperator(inID);
        if (op == null) return 0;
          System.out.print("Enter password: ");
          inPass = scan.nextLine();
        if (!op.getPassword().equals(inPass)) return 1;
        return 2;
        
      case PROVIDER:
        System.out.print("Enter Provider ID: ");
        inID = Integer.parseInt(scan.nextLine());
        Provider prov = new ProviderService().getProvider(inID);
        if (prov == null) return 0;
          System.out.print("Enter password: ");
          inPass = scan.nextLine();
        if (!prov.getPassword().equals(inPass)) return 1;
        return 2;
        
      case MANAGER:
        System.out.print("Enter Manager ID: ");
        inID = Integer.parseInt(scan.nextLine());
        Manager man = new ManagerService().getManager(inID);
        if (man == null) return 0;
          System.out.print("Enter password: ");
          inPass = scan.nextLine();
        if (!man.getPassword().equals(inPass)) return 1;
        return 2;
        
      default:
        return 0;
    }
  }
}
