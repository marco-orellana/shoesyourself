package com.example.shoesyourself.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.shoesyourself.managers.UserManager;


public class DataBaseHelper extends SQLiteOpenHelper {

    // TABLES NAMES
    public static final String BRAND_TABLE_NAME = "brand";
    public static final String PRODUCT_TABLE_NAME = "product";
    public static final String ROLE_TABLE_NAME = "role";
    public static final String USER_TABLE_NAME = "user";
    public static final String ORDER_TABLE_NAME = "orders";
    public static final String ORDERLIST_TABLE_NAME = "orderlist";
    public static final String CART_TABLE_NAME = "cart";
    public static final String CARTLIST_TABLE_NAME = "cartlist";
    //public static final String URL_SERVER = "http://192.168.0.106:8080";
    public static final String URL_SERVER = "http://192.168.0.213:8080";
    // TABLES CREATES STATEMENTS
    private static final String CREATE_TABLE_BRAND = ("create table " + BRAND_TABLE_NAME + "(" + "id text primary key , title text)");
    private static final String CREATE_TABLE_PRODUCT = ("create table " + PRODUCT_TABLE_NAME + "(" + "id text primary key , title text, description text, img_url text, price real, brand_id text)");
    private static final String CREATE_TABLE_ROLE = ("create table " + ROLE_TABLE_NAME + "(" + "id text primary key , title text)");
    private static final String CREATE_TABLE_USER = ("create table " + USER_TABLE_NAME + "(" + "id text , role_id text, email text primary key , first_name text, last_name text, img_url text, password text)");
    private static final String CREATE_TABLE_ORDER = ("create table " + ORDER_TABLE_NAME + "(" + "id text primary key , user_id text, date text)");
    private static final String CREATE_TABLE_ORDERLIST = ("create table " + ORDERLIST_TABLE_NAME + "(" + "id text primary key , order_id text, product_id text, quantity integer)");
    private static final String CREATE_TABLE_CART = ("create table " + CART_TABLE_NAME + "(" + "id text primary key , user_id text)");
    private static final String CREATE_TABLE_CARTLIST = ("create table " + CARTLIST_TABLE_NAME + "(" + "id text primary key , cart_id text, product_id text, quantity integer)");
    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creation
        db.execSQL(CREATE_TABLE_BRAND);
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_ROLE);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_CARTLIST);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_ORDERLIST);
        // Insertions
        BrandHelper.getFromAPI(db);
        //CategoryHelper.getFromAPI(db);
        ProductHelper.getFromAPI(db);
        //RoleHelper.getFromAPI(db);
        //OrderHelper.getFromAPI(db);
        //OrderListHelper.getFromAPI(db);
        //CartHelper.getFromAPI(db);
        //CartListHelper.getFromAPI(db);
        UserHelper.getFromAPI(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Suppressions
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BRAND_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ROLE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ORDERLIST_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CART_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CARTLIST_TABLE_NAME);
        // Creation
        onCreate(db);
    }
}
