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
  private Provider provider;
  private Member member;
  private Service service;
  private String comments;

  //Constructors
  
  public ServiceRecord() {}

  public ServiceRecord(String dateProvided, Provider provider, Member member, Service service) {
    this.dateBilled = getTime();
    this.dateProvided = dateProvided;
    this.provider = provider;
    this.member = member;
    this.service = service;
    this.comments = "";
  }

  public ServiceRecord(String dateProvided, Provider provider, Member member, Service service, String comments) {
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

  public Provider getProvider() {
    return provider;
  }

  public Member getMember() {
    return member;
  }
  
  public Service getService() {
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
    result += provider.getID() + "\n";
    result += member.getID() + "\n";
    result += service.getCode() + "\n";
    result += comments + "\n";
    return result;
  }
  
}
