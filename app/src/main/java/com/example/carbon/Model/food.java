package com.example.carbon.Model;

public class food {
    private String Name;
    private  String Price;
    private  String Image;
    private String Status;
    private String Fooddtails;
    public food() {
    }

    public food(String name, String price,String image,String status,String fooddtails) {
        Name = name;
        Price = price;
        Image= image;
        Status=status;
        Fooddtails=fooddtails;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getFooddtails() {
        return Fooddtails;
    }

    public void setFooddtails(String fooddtails) {
        Fooddtails = fooddtails;
    }
}
