package com.example.registerprojectmobile.businesslogic;

import java.time.LocalDate;

public class Child extends Person
    {
        public int ID ;public int  getID(){return ID;} public void setID(int value){ID = value;}
        public Address HomeAddress ;public Address getHomeAddress(){return HomeAddress;} public void setHomeAddress(Address value){HomeAddress = value;}
        public InsuranceCompany Insurance ;public InsuranceCompany getInsurance(){return Insurance;} public void setInsurance(InsuranceCompany value){Insurance = value;}
        public String HealthState ;public String getHealthState(){return HealthState;} public void setHealthState(String value){HealthState = value;}
        public String Comments ;public String getComments(){return Comments;} public void setComments(String value){Comments = value;}
        public LocalDate BirthDate ;public LocalDate getBirthDate(){return BirthDate;} public void setBirthDate(LocalDate value){BirthDate = value;}
        public String SchoolName ;public String getSchoolName(){return SchoolName;} public void setSchoolName(String value){SchoolName = value;}
        public Parent Mother ;public Parent getMother(){return Mother;} public void setMother(Parent value){Mother = value;}
        public Parent Father ;public Parent getFather (){return Father ;} public void setFather (Parent value){Father  = value;}
    ;}

