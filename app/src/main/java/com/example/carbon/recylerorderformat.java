package com.example.carbon;

import com.example.carbon.Model.cart;

import java.util.List;

public class recylerorderformat {
    private String key;
    private String phoneNumber;
    private String Adress;
    private String Total;
    private List<cart> Cart;


    public recylerorderformat() {
    }

    public recylerorderformat(String key, String phoneNumber, String adress, String total, List<cart> cart) {
        this.key = key;
        this.phoneNumber = phoneNumber;
        Adress = adress;
        Total = total;
        Cart = cart;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public List<cart> getCart() {
        return Cart;
    }

    public void setCart(List<cart> cart) {
        Cart = cart;
    }

}
