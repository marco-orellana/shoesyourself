package com.example.shoesyourself.entities;

public class Order {
private String _id;
private String user_id;
private String date;
    public Order() {
    }
    public Order(String id, String user_id, String date) {
        this._id = id;
        this.user_id = user_id;
        this.date = date;
    }
    public Order(String id, String user_id) {
        this._id = id;
        this.user_id = user_id;
    }
    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
