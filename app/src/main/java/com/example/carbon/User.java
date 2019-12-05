package com.example.carbon;

public class User {
    private String Name;
    private String Gender;
    private String Number;

    public  User ()
    {

    }
    public User(String name, String gender, String number) {
        this.Name = name;
        this.Gender = gender;
        this.Number = number;
    }
    public String getGender()
    {
        return Gender;
    }
    public String getName()
    {
        return Name;
    }
    public String getNumber()
    {
        return Number;
    }
}
