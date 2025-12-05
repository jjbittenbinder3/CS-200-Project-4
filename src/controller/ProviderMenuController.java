/*
  Author: James Pepper
*/

package controller;

public class ProviderMenuController {
  public ProviderMenuController() {
    showMenu();
  }
  
  private void showMenu() {
    int status = new LoginController().login(LoginController.Type.PROVIDER);
    switch (status) {
      case 0:
        System.out.println("Invalid provider ID entered.");
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
