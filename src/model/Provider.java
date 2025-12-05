/*
  Author: James Pepper
*/

package model;

public class Provider {

  //Variables
  
  private String name;
  private int ID;
  private String password;
  
  private String address;
  private String city;
  private String state;
  private String zip;

  private boolean suspended;

  //Constructors
  
  public Provider() {}
  
  public Provider(String name, int ID) {
    this.name = name;
    this.ID = ID;
  }
  
  public Provider(String name, int ID, String password, boolean suspended) {
    this.name = name;
    this.ID = ID;
    this.password = password;
    this.suspended = suspended;
  }

  //Public Methods
  
  public boolean login(int inID, String inPassword) {
    if ((inID == this.ID) && (inPassword.equals(this.password))) return true;
    return false;
  }

  //Setters

  public void setInfo(String name, int ID, String password) {
    this.name = name;
    this.ID = ID;
    this.password = password;
  }

  public void setAddress(String address, String city, String state, String zip) {
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

  public void setStatus(boolean suspended) {
    this.suspended = suspended;
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

  public String getAddress() {
    return this.address;
  }

  public String getCity() {
    return this.city;
  }

  public String getState() {
    return this.state;
  }

  public String getZip() {
    return this.zip;
  }

  public boolean getSuspended() {
    return this.suspended;
  }
}
