/*
  Author: James Pepper
*/

package controller;

public class ManagerMenuController {
  public ManagerMenuController()  {
    boolean valid = new LoginController().login(LoginController.Type.MANAGER);
    if (!valid) return;
    System.out.println("");
    // here
  }
}
