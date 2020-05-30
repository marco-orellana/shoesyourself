package com.example.shoesyourself.entities;

public class Cart {

    private String _id;
    private String user_id;
    public Cart(String id, String user_id) {
        this._id = id;
        this.user_id = user_id;
    }
    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }
    public String getUserId() {
        return user_id;
    }
    public void setUserId(String user_id) {
        this.user_id = user_id;
    }
}
