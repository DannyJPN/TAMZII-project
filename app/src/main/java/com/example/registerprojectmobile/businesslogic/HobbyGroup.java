package com.example.registerprojectmobile.businesslogic;
    public class HobbyGroup
    {
        public uint ID { get; set; }
        public GroupLeader Leader { get; set; }
        public string Day { get; set; }
        public TimeSpan From { get; set; }
        public TimeSpan To { get; set; }
        public uint Min { get; set; }
        public uint Max { get; set; }

        public string Name { get; set; }

    }

