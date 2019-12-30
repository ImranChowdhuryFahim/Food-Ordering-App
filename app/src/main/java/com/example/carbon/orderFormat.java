package com.example.carbon;

import com.example.carbon.Model.cart;

import java.util.List;

public class orderFormat {

    private String key;
    private String phoneNumber;
    private String Adress;
    private String Total;
    private List<cart> Cart;
    private String status;

    public orderFormat() {
    }

    public orderFormat(String key, String phoneNumber, String adress, String total, List<cart> cart, String status) {
        this.key = key;
        this.phoneNumber = phoneNumber;
        Adress = adress;
        Total = total;
        Cart = cart;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
