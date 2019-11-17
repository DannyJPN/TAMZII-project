package com.example.registerprojectmobile.businesslogic;
    public class Child extends Person
    {
        public uint ID ;public uint  getID(){return ID} public void setID(uint value){ID = value}
        public Address HomeAddress ;public Address getHomeAddress(){return HomeAddress} public void setHomeAddress(Address value){HomeAddress = value}
        public InsuranceCompany Insurance ;public InsuranceCompany getInsurance(){return Insurance} public void setInsurance(InsuranceCompany value){Insurance = value}
        public string HealthState ;public string getHealthState(){return HealthState} public void setHealthState(string value){HealthState = value}
        public string Comments ;public string getComments(){return Comments} public void setComments(string value){Comments = value}
        public DateTime BirthDate ;public  getBirthDate(){return BirthDate} public void setBirthDate(DateTime value){BirthDate = value}
        public string SchoolName ;public string getSchoolName(){return SchoolName} public void setSchoolName(string value){SchoolName = value}
        public Parent Mother ;public  getMother(){return Mother} public void setMother(Parent value){Mother = value}
        public Parent Father ;public  getFather (){return Father } public void setFather (Parent value){Father  = value}
    }

