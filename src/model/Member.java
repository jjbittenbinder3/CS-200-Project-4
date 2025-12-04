package model;

public class Member {
  private String name;
  private int ID;
  private String password;
  
  private String address;
  private String city;
  private String state;
  private String zip;

  private boolean suspended;

  //Constructors
  public Member() {}
  
  public Member(String name, int ID, String password = "", boolean suspended = false) {
    this.name = name;
    this.ID = ID;
    this.password = password;
    this.suspended = suspended;
  }

  //Public Methods
  public Login(String inID, String inPassword) {
    if (inID == this.ID) && (inPassword == this.password) return true;
    return false;
  }

  public SetInfo(String name, int ID, string password, boolean suspended = this.suspended) {
    this.name = name;
    this.ID = ID;
    this.password = password;
    this.suspended = suspended;
  }

  public SetAddress(String address, String city, String state, string zip) {
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

  public SetStatus(boolean suspended) {
    this.suspended = suspended;
  }
}
