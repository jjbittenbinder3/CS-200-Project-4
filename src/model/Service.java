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
  
  public Service(String name, int code, double fee) {
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
  
  public void setFee(double fee) {
    this.fee = fee;
  }
  
  //Getters

  public String getName() {
    return this.name;
  }

  public int getCode() {
    return this.code;
  }

  public double getFee() {
    return this.fee;
  }
  
}
