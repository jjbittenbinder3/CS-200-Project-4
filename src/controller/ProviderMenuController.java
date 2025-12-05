/*
  Author: James Pepper
*/

package controller;

public class ProviderMenuController {
  public ProviderMenuController() {
    boolean valid = new LoginController()..login(LoginController.type.PROVIDER);
    if (!valid) return;
    System.out.println("");
    // here
  }
}
