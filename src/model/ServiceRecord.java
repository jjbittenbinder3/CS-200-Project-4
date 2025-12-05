/*
  Author: James Pepper
*/

package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServiceRecord {

  //Variables

  private String dateBilled;
  private String dateProvided;
  private String provider;
  private String member;
  private int service;
  private String comments;

  //Constructors
  
  public ServiceRecord() {}

  public ServiceRecord(String dateProvided, String provider, String member, int service) {
    this.dateBilled = getTime();
    this.dateProvided = dateProvided;
    this.provider = provider;
    this.member = member;
    this.service = service;
    this.comments = "";
  }

  public ServiceRecord(String dateProvided, String provider, String member, int service, String comments) {
    this.dateBilled = getTime();
    this.dateProvided = dateProvided;
    this.provider = provider;
    this.member = member;
    this.service = service;
    this.comments = comments;
  }

  //Private Methods

  private String getTime() {
    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    return date.toString() + " " + time.toString();
  }

  //Getters

  public String getDateBilled() {
    return dateBilled;
  }

  public String getDateProvided() {
    return dateProvided;
  }

  public String getProvider() {
    return provider;
  }

  public String getMember() {
    return member;
  }
  
  public int getService() {
    return service;
  }
  
  public String getComments() {
    return comments;
  }
  
  //Setters

  public void setComments(String comments) {
    this.comments = comments;
  }
  
  //Public Methods
  
  public String getRecord() {
    String result = "";
    result += dateProvided + "\n";
    result += dateBilled + "\n";
    result += provider + "\n";
    result += member + "\n";
    result += service + "\n";
    result += comments + "\n";
    return result;
  }
  
}
