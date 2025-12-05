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
    int inPass = 0;
    
    switch (loginType) {
      case MEMBER:
        System.out.print("Enter Member ID: ");
        inID = scan.nextInt()
        Member mem = new MemberService().findMember(inID);
        if (!mem) return 0;
          System.out.print("Enter password: ");
          inPass = scan.nextInt();
        if (mem.password != inPass) return 1;
        return 2;
        
      case OPERATOR:
        System.out.print("Enter Operator ID: ");
        inID = scan.nextInt()
        Operator op = new OperatorService().findOperator(inID);
        if (!op) return 0;
          System.out.print("Enter password: ");
          inPass = scan.nextInt();
        if (op.password != inPass) return 1;
        return 2;
        
      case PROVIDER:
        System.out.print("Enter Provider ID: ");
        inID = scan.nextInt()
        Provider prov = new ProviderService().findProvider(inID);
        if (!prov) return 0;
          System.out.print("Enter password: ");
          inPass = scan.nextInt();
        if (prov.password != inPass) return 1;
        return 2;
        
      case MANAGER:
        System.out.print("Enter Manager ID: ");
        inID = scan.nextInt()
        Manager man = new ManagerService().findManager(inID);
        if (!man) return 0;
          System.out.print("Enter password: ");
          inPass = scan.nextInt();
        if (man.password != inPass) return 1;
        return 2;
        
      default:
        return 0;
    }
    return 0;
  }
}
