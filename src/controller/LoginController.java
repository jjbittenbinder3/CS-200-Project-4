/*
  Author: James Pepper
*/

public class LoginController {
  
  public enum Type {
    MEMBER,
    OPERATOR,
    PROVIDER,
    MANAGER
  }
  
  public LoginController(Type loginType) {}
  
  public boolean login(Type loginType) {
    switch (loginType) {
      case MEMBER:
        break;
      case OPERATOR:
        break;
      case PROVIDER:
        break;
      case MANAGER:
        break;
      default:
        break;
    } 
  }
}
