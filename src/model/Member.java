package model;

public class Member {
  private String name;
  private String address;
  private String city;
  private String state;
  private String zip;
  private boolean suspended;
  private int ID;
  private String password;
  
  public Member() {}
  
  public Member(String name, int ID) {
    this.name = name;
    this.number = number;
  }

  public Login(String inID, String inPassword) {
    if (inID == this.ID) && (inPassword == this.password) {
      return true;
    }
    return false;
  }
  // Getters and setters
}

newMember = new Member(name, ID);
