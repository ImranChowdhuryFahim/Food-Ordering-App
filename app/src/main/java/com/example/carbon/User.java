package com.example.carbon;

public class User {
    private String Name;
    private String Gender;
    private String Number;
    private String Pass;

    public  User ()
    {

    }
    public User(String name, String gender, String number,String pass) {
        this.Name = name;
        this.Gender = gender;
        this.Number = number;
        this.Pass=pass;
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
    public String getPass(){ return  Pass;}
}
