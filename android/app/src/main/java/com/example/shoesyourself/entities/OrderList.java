package com.example.shoesyourself.entities;

public class OrderList {

    private String _id;
    private String order_id;
    private String product_id;
    private int quantity;
    public OrderList(String id, String order_id, String product_id, int qty) {
        this._id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = qty;
    }
    public OrderList(String order_id, String product_id, int qty) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = qty;
    }
    public OrderList() {
    }
    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }
    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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
}
