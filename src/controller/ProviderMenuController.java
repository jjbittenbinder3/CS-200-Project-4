/*
  Author: James Pepper
*/

package controller;

public class ProviderMenuController {
  public ProviderMenuController() {
    boolean valid = new LoginController().login(LoginController.Type.PROVIDER);
    if (!valid) return;
    System.out.println("");
    // here
  }
}
