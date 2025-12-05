/*
  Author: James Pepper
*/

package model;

public class Service {

  //Variables
  
  private String name;
  private int code;
  private double fee;

  //Constructors
  
  public Service() {}
  
  public Service(String name, String code, double fee) {
    this.name = name;
    this.code = code;
    this.fee = fee;
  }
  
  //Setters

  public void setName(String name) {
    this.name = name;
  }
  
  public void setCode(int code) {
    this.code = code;
  }
  
  public void setFee(int Fee) {
    this.fee = fee;
  }
  
  //Getters

  public String getName() {
    return this.name;
  }

  public int getCode() {
    return this.code;
  }

  public int getFee() {
    return this.fee;
  }
  
}
