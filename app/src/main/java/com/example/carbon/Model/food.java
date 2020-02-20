package com.example.carbon.Model;

public class food {
    private String Name;
    private  String Price;
    private  String Image;
    public food() {
    }

    public food(String name, String price,String image) {
        Name = name;
        Price = price;
        Image= image;
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
}
