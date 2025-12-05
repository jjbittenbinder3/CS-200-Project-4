/*
  Author: James Zittlow
*/

package model;

public class Manager {

  //Variables
  
  private String name;
  private int ID;
  private String password;
  
  private String phoneNumber;
  private String email;

  //Constructors
  
  public Manager() {}
  
  public Manager(String name, int ID) {
    this.name = name;
    this.ID = ID;
  }
  
  public Manager(String name, int ID, String password) {
    this.name = name;
    this.ID = ID;
    this.password = password;
  }

  //Public Methods
  
  public boolean login(int ID, String Password) {
    if ((ID == this.ID) && (Password.equals(this.password))) return true;
    return false;
  }

  //Setters

  public void setInfo(String name, int ID, String password) {
    this.name = name;
    this.ID = ID;
    this.password = password;
  }

  //Getters

  public String getName() {
    return this.name;
  }

  public int getID() {
    return this.ID;
  }

  public String getPassword() {
    return this.password;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public String getEmail() {
    return this.email;
  }
}
