/*
  Author: James Pepper
*/

package controller;

public class OperatorMenuController {
  public OperatorMenuController() {
    showMenu();
  }
  
  private showMenu() {
    int status = new LoginController().login(LoginController.Type.OPERATOR);
    switch (status) {
      case 0:
        System.out.println("Invalid operator ID entered.");
        return;
      case 1:
        System.out.println("Invalid password entered.");
        return;
      case 2:
        //here
        return;
    }
    return;
  }
}
