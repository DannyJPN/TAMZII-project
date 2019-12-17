package com.example.registerprojectmobile;

import android.graphics.Bitmap;

import java.util.Date;

public class Child 
{

private String Name="";
private String Surname="";
private Date BirthDate=new Date();
private int RegNum=0;

private int InsuranceNumber=0;
private int GroupID=0;
private int ID=0;
private Bitmap Photo=null;

    public String getName(){ return Name;}
    public String getSurname(){ return Surname;}
    public Date getBirthDate(){ return BirthDate;}
    public int getRegNum(){ return RegNum;}
    public int getInsuranceNumber(){ return InsuranceNumber;}
    public int getGroupID(){return GroupID;}
public int getID(){return ID;}
public Bitmap getPhoto(){return Photo;}

    public  void setName(String  name){Name =name ;}
    public  void setSurname(String surname){ Surname=surname ;}
    public  void setBirthDate(Date birthDate){ BirthDate=birthDate ;}
    public  void setRegNum(int regNum){ RegNum= regNum;}
  public  void setInsuranceNumber(int insuranceNumber){ InsuranceNumber= insuranceNumber;}
    public void setGroupID(int groupID){GroupID=groupID;}
public void setID(int id){ID=id;}
public void setPhoto(Bitmap b){Photo=b;}


}
