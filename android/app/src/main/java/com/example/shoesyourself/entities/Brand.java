package com.example.shoesyourself.entities;

public class Brand {

    private String _id;
    private String title;
    public Brand(String id, String title) {
        this._id = id;
        this.title = title;
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
