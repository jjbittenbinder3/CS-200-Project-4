/*
  Author: James Pepper
*/

public class LoginController() {
  
  enum type {
    MEMBER,
    OPERATOR,
    PROVIDER,
    MANAGER
  }
  
  public LoginController(type loginType) {
    switch (loginType) {
      case MEMBER:
        break;
      case OPERATOR:
        break;
      case PROVIDER;
        break;
      case MANAGER:
        break;
      default:
        break;
    } 
  }
}
