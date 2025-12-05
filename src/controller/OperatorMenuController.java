/*
  Author: James Pepper
*/

package controller;

public class OperatorMenuController {
  public OperatorMenuController() {
    boolean valid = new LoginController().login(LoginController.Type.OPERATOR);
    if (!valid) return;
    System.out.println("");
    // here
  }
}
