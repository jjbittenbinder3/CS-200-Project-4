/*
  Author: James Pepper
*/

package controller;

public class ManagerMenuController {
  public ManagerMenuController()  {
    LoginController loginMenu = new LoginController();
    boolean valid = loginMenu.login(LoginController.type.MANAGER);
    if (!valid) return;
    System.out.println("");
    // here
  }
}
