package com.example.shoesyourself.entities;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.shoesyourself.managers.Asset;

import java.io.File;


public class CartList {

    private String _id;
    private String cart_id;
    private String product_id;
    private int quantity;
    //To show product information in cartList
    private Product productItem;
    public CartList() {
    }
    public CartList(int quantity, Product productItem) {
        this.quantity = quantity;
        this.productItem = productItem;
    }
    public CartList(String id, String cart_id, String product_id, int quantity) {
        this._id = id;
        this.cart_id = cart_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }
    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }
    public String getCart_id() {
        return cart_id;
    }
    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }
    public String getProduct_id() {
        return product_id;
    }
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product getProductItem() {
        return productItem;
    }
    public void setProductItem(Product productItem) {
        this.productItem = productItem;
    }
}
