/*
  Author: James Pepper
*/

package controller;

public class OperatorMenuController {
  public OperatorMenuController() {
    LoginController loginMenu = new LoginController();
    boolean valid = loginMenu.login(LoginController.type.OPERATOR);
    if (!valid) return;
    System.out.println("");
    // here
  }
}
