package com.example.registerprojectmobile;

import java.util.ArrayList;

public class Group {
    private final ArrayList<Child> Members= new ArrayList<>();
    private String Name;
    private int ID;

    public Group(int id,String name)
    {
        ID=id;
        Name=name;

    }
    public int getID(){return ID;}
    public String getName(){return Name;}
    public ArrayList<Child> getMembers(){return Members;}

    public void setID(int id){ID=id;}
    public void setName(String name){Name =name;}

}
