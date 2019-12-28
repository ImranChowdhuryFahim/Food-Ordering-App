package com.example.carbon.Model;

public class food {
    private String Name;
    private  String Price;

    public food() {
    }

    public food(String name, String price) {
        Name = name;
        Price = price;
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
}
