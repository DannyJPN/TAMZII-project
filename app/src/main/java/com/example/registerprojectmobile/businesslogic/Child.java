package com.example.registerprojectmobile.businesslogic;
    public class Child:Person
    {
        public uint ID { get; set; }
        public Address HomeAddress { get; set; }
        public InsuranceCompany Insurance { get; set; }
        public string HealthState { get; set; }
        public string Comments { get; set; }
        public DateTime BirthDate { get; set; }
        public string SchoolName { get; set; }
        public Parent Mother { get; set; }
        public Parent Father { get; set; }
    }

