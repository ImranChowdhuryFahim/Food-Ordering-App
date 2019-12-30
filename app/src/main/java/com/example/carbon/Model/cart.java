package com.example.carbon.Model;

public class cart {
    private String Catagory;
    private String Name;
    private String Price;
    private  String Quantity;

    public cart() {
    }

    public cart(String catagory, String name, String price, String quantity) {
        Catagory = catagory;
        Name = name;
        Price = price;
        Quantity = quantity;
    }

    public String getCatagory() {
        return Catagory;
    }

    public void setCatagory(String catagory) {
        Catagory = catagory;
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

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
