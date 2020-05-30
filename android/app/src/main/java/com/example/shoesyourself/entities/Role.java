package com.example.shoesyourself.entities;

public class Role {

    private String _id;
    private String title;
    public Role(String title) {
        this.title = title;
    }
    public Role(String id, String title) {
        this._id = id;
        this.title = title;
    }
    public Role() {
    }
    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
