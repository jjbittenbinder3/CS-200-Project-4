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
        inID = scan.nextInt();
        Member mem = new MemberService().getMember(inID);
        if (mem == null) return 0;
          System.out.print("Enter password: ");
          inPass = scan.next();
        if (mem.getPassword() != inPass) return 1;
        return 2;
        
      case OPERATOR:
        System.out.print("Enter Operator ID: ");
        inID = scan.nextInt();
        Operator op = new OperatorService().getOperator(inID);
        if (op == null) return 0;
          System.out.print("Enter password: ");
          inPass = scan.next();
        if (op.getPassword() != inPass) return 1;
        return 2;
        
      case PROVIDER:
        System.out.print("Enter Provider ID: ");
        inID = scan.nextInt();
        Provider prov = new ProviderService().getProvider(inID);
        if (prov == null) return 0;
          System.out.print("Enter password: ");
          inPass = scan.next();
        if (prov.getPassword() != inPass) return 1;
        return 2;
        
      case MANAGER:
        System.out.print("Enter Manager ID: ");
        inID = scan.nextInt();
        Manager man = new ManagerService().getManager(inID);
        if (man == null) return 0;
          System.out.print("Enter password: ");
          inPass = scan.next();
        if (man.getPassword() != inPass) return 1;
        return 2;
        
      default:
        return 0;
    }
  }
}
