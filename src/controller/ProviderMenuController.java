/*
  Author: James Pepper
*/

package controller;

public class ProviderMenuController {
  public ProviderMenuController() {
    LoginController loginMenu = new LoginController();
    boolean valid = loginMenu.login(LoginController.type.PROVIDER);
    if (!valid) return;
    System.out.println("");
    // here
  }
}
