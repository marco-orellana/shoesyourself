package com.example.shoesyourself.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shoesyourself.entities.Product;
import com.example.shoesyourself.helpers.DataBaseHelper;
import com.example.shoesyourself.services.ConnectionBD;
import com.example.shoesyourself.services.DeleteJson;
import com.example.shoesyourself.services.PostJson;
import com.example.shoesyourself.services.PutJson;
import com.google.gson.Gson;

import java.util.ArrayList;


public class ProductManager {

    /**
     * PRODUCT TABLE FIELDS
     */
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String IMG_URL = "img_url";
    private static final String PRICE = "price";
    private static final String BRAND_ID = "brand_id";
    /**
     * PRODUCT TABLE'S QUERIES
     */
    //String id, String title, String description, String img_url, double price, String category_id, String brand_id, String brand_title
    private static final String queryGetAll = "select product.id, brand_id, product.title, description, img_url, price, brand.title from product inner join brand on product.brand_id=brand.id";
    private static final String queryGetByIdForShowCartItem = "select product.id, product.title, img_url, price, brand.title from product inner join brand on product.brand_id=brand.id where product.id like ?";
    private static final String queryGetById = "select product.id, brand_id, product.title, description, img_url, price, brand.title from product inner join brand on product.brand_id=brand.id  where product.id like ?";
    private static final String queryGetByBrand = "select product.id, brand_id, product.title, description, img_url, price, brand.title from product inner join brand on product.brand_id=brand.id where brand_id like ?";
    /**
     * getAll return all product from DataBase
     *
     * @param context
     * @return ArrayList<Product>
     */
    public static ArrayList<Product> getAll(Context context) {
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            products.add(new Product(context,
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getDouble(5),
                    cursor.getString(6))
            );
        }
        ConnectionBD.close();
        return products;
    }
    /**
     * getById return all product by idProduct from DataBase
     *
     * @param context
     * @param idProduct
     * @return Product
     */
    public static Product getById(Context context, String idProduct) {
        Product product = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetById, new String[]{"" + idProduct});
        if (cursor != null) {
            cursor.moveToFirst();
            product = new Product(context,
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getDouble(5),
                    cursor.getString(6));
        }
        ConnectionBD.close();
        return product;
    }
    public static Product getByIdForShowCartItem(Context context, String idProduct) {
        Product product = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetByIdForShowCartItem, new String[]{"" + idProduct});
        if (cursor != null) {
            cursor.moveToFirst();
            product = new Product(context,
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getString(4));
        }
        ConnectionBD.close();
        return product;
    }
    /**
     * getByBrand return all product by Brand from DataBase
     *
     * @param context
     * @param idBrand
     * @return ArrayList<Product>
     */
    public static ArrayList<Product> getByBrand(Context context, String idBrand) {
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetByBrand, new String[]{"" + idBrand});
        while (cursor.moveToNext()) {
            products.add(new Product(context,
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getDouble(5),
                    cursor.getString(6))
            );
        }
        ConnectionBD.close();
        return products;
    }
    /**
     * Insert product in DataBase
     *
     * @param context
     * @param product
     */
    public static void insert(Context context, Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, product.getId());
        contentValues.put(TITLE, product.getTitle());
        contentValues.put(DESCRIPTION, product.getDescription());
        contentValues.put(IMG_URL, product.getImgUrl());
        contentValues.put(PRICE, product.getPrice());
        contentValues.put(BRAND_ID, product.getIdBrand());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.insert(DataBaseHelper.PRODUCT_TABLE_NAME, null, contentValues);
    }
    /**
     * Update product in Database
     *
     * @param context
     * @param product
     */
    public static void update(Context context, Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, product.getTitle());
        contentValues.put(DESCRIPTION, product.getDescription());
        contentValues.put(IMG_URL, product.getImgUrl());
        contentValues.put(PRICE, product.getPrice());
        contentValues.put(BRAND_ID, product.getIdBrand());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.update(DataBaseHelper.PRODUCT_TABLE_NAME, contentValues, ID + " like ? ", new String[]{"" + product.getId()});
    }
    /**
     * Delete product from DataBase
     *
     * @param context
     * @param id
     */
    public static void delete(Context context, String id) {
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.delete(DataBaseHelper.PRODUCT_TABLE_NAME, " id like ? ", new String[]{"" + id});
        ConnectionBD.close();
    }
    public static void postToAPI(Context context, Product product) {
        Gson gson = new Gson();
        String jsonToSemd = gson.toJson(product);
        String jsonFromApi = PostJson.post(jsonToSemd, "/products");
        Product productFromApi = gson.fromJson(jsonFromApi, Product.class);
        ProductManager.insert(context, productFromApi);
    }
    public static void updatetToAPI(Context context, Product product) {
        Gson gson = new Gson();
        String jsonToSemd = gson.toJson(product);
        String jsonFromApi = PutJson.put(jsonToSemd, "/products/" + product.getId());
        Log.d("JSonPutApi", jsonFromApi);
        ProductManager.update(context, product);
    }
    public static void deleteToAPI(Context context, int productId) {
        String jsonFromApi = DeleteJson.delete("/products/" + productId);
        Log.d("JSonDeleteApi", jsonFromApi);
    }
}
