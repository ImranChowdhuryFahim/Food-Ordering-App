package com.example.carbon;

public class orderFormat {

    private String order_key;
    private String order_status;
    private String order_msg;

    public orderFormat()
    {

    }

    public orderFormat(String order_key,String order_status,String order_msg)
    {
        this.order_key=order_key;
        this.order_status=order_status;
        this.order_msg=order_msg;
    }

    public String getOrder_key() {
        return order_key;
    }

    public void setOrder_key(String order_key) {
        this.order_key = order_key;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_msg() {
        return order_msg;
    }

    public void setOrder_msg(String order_msg) {
        this.order_msg = order_msg;
    }
}
