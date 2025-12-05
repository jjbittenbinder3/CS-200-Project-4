/*
  Author: James Pepper
*/

package controller;

import data.MemberService;
import java.util.Scanner;

public class LoginController {
  
  public enum Type {
    MEMBER,
    OPERATOR,
    PROVIDER,
    MANAGER
  }
  
  public LoginController() {}
  
  public boolean login(Type loginType) {
    Scanner scan = new Scanner(System.in);
    
    switch (loginType) {
      case MEMBER:
        System.out.print("Enter Member ID: ");
        int inID = scan.nextInt()
        Member mem = new MemberService().findMember(inID);
        if (!mem) {
          notFound(loginType);
          return false;
        }

        if (mem.password == inPass) {
          
        } else {
          
        }
        break;
      case OPERATOR:
        break;
      case PROVIDER:
        break;
      case MANAGER:
        break;
      default:
        break;
    }
    return true;
  }
}
