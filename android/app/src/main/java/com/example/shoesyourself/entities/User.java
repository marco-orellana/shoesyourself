package com.example.shoesyourself.entities;

public class User {

    private String _id;
    private String email;
    private String first_name;
    private String last_name;
    private String img_url;
    private String password;
    private String role_id;
    public User() {
    }
    public User(String role_id, String email, String first_name, String last_name, String password) {
        this.role_id = role_id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
    }
    public User(String id, String role_id, String email, String firstName, String lastName, String urlImg, String password) {
        this._id = id;
        this.role_id = role_id;
        this.email = email;
        this.first_name = firstName;
        this.last_name = lastName;
        this.img_url = urlImg;
        this.password = password;
    }
    public User(String role_id, String email, String first_name, String last_name, String img_url, String password) {
        this.role_id = role_id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.img_url = img_url;
        this.password = password;
    }
    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }
    public String getRoleId() {
        return role_id;
    }
    public void setRoleId(String role_id) {
        this.role_id = role_id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String lastName) {
        this.last_name = lastName;
    }
    public String getUrlImg() {
        return img_url;
    }
    public void setUrlImg(String urlImg) {
        this.img_url = urlImg;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
