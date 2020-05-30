package com.example.shoesyourself.entities;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.shoesyourself.managers.Asset;

import java.io.File;


public class Product {

    private String _id;
    private String title;
    private String description;
    private String img_url;
    private double price;
    private String brand_id;
    private String brand_title;
    private Drawable img;
    public Product() {
    }
    public Product(Context context, String id, String title, String img_url, double price, String brand_title) {
        this._id = id;
        this.title = title;
        this.img_url = img_url;
        this.price = price;
        this.brand_title = brand_title;
        this.brand_title = brand_title;
        this.img_url = img_url;
        this.img = Asset.setImageInImageView(context, getFolderUrl() + this.img_url);
    }
    public Product(Context context, String id, String brand_id, String title, String description, String img_url, double price, String brand_title) {
        this._id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.brand_id = brand_id;
        this.brand_title = brand_title;
        this.img_url = img_url;
        this.img = Asset.setImageInImageView(context, getFolderUrl() + this.img_url);
    }
    public String getFolderUrl() {
        String str = this.getBrandTitle().toLowerCase().replaceAll(" ", "_").toLowerCase();
        return "products" + File.separator + str + File.separator;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Drawable getImg() {
        return img;
    }
    public void setImg(Drawable img) {
        this.img = img;
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
    public String getImgUrl() {
        return img_url;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getIdBrand() {
        return brand_id;
    }
    public void setIdBrand(String idBrand) {
        this.brand_id = idBrand;
    }
    public String getBrandTitle() {
        return brand_title;
    }
    public void setBrandTitle(String brandTitle) {
        this.brand_title = brandTitle;
    }
}
