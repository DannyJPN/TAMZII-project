package com.example.registerprojectmobile;

import java.util.Date;

public class Child 
{

private String Name;
private String Surname;
private Date BirthDate;
private int RegNum;
private String Address;
private String HealthState;
private String Comments;
private String Email;
private int Phone;
private Boolean LeavingWithoutParents;
private int InsuranceNumber;
private int GroupID;
    public String getName(){ return Name;}
    public String getSurname(){ return Surname;}
    public Date getBirthDate(){ return BirthDate;}
    public int getRegNum(){ return RegNum;}
    public String getAddress(){ return Address;}
    public String getHealthState(){ return HealthState;}
    public String getComments(){ return Comments;}
    public String getEmail(){ return Email;}
    public int getPhone(){ return Phone;}
    public Boolean getLeavingWithoutParents(){ return LeavingWithoutParents;}
    public int getInsuranceNumber(){ return InsuranceNumber;}
    public int getGroupID(){return GroupID;}

    public  void setName(String  name){Name =name ;}
    public  void setSurname(String surname){ Surname=surname ;}
    public  void setBirthDate(Date birthDate){ BirthDate=birthDate ;}
    public  void setRegNum(int regNum){ RegNum= regNum;}
    public  void setAddress(String address){ Address=address ;}
    public  void setHealthState(String healthState){ HealthState=healthState ;}
    public  void setComments(String comments){ Comments=comments ;}
    public  void setEmail(String email){ Email=email ;}
    public  void setPhone(int phone){ Phone=phone ;}
    public  void setLeavingWithoutParents(Boolean leavingWithoutParents){LeavingWithoutParents =leavingWithoutParents ;}
    public  void setInsuranceNumber(int insuranceNumber){ InsuranceNumber= insuranceNumber;}
    public void setGroupID(int groupID){GroupID=groupID;}


}
