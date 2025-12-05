/*
  Author: James Pepper
*/

package controller;

public class ManagerMenuController {
  public ManagerMenuController()  {
    LoginController loginMenu = new LoginController();
    boolean valid = loginMenu.login();
    if (!valid) return;
    System.out.println("");
    // here
  }
}
