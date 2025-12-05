/*
  Author: James Pepper
*/

package controller;

public class ManagerMenuController {
  
  public ManagerMenuController()  {
    showMenu();
  }
  
  private showMenu() {
    int status = new LoginController().login(LoginController.Type.MANAGER);
    switch (status) {
      case 0:
        System.out.println("Invalid member ID entered.");
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
