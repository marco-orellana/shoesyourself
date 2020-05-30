package com.example.shoesyourself.helpers;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shoesyourself.entities.Product;
import com.example.shoesyourself.services.GetJson;
import com.example.shoesyourself.services.HttpGetRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class ProductHelper {

    public static void getFromAPI(SQLiteDatabase db) {
        String json = GetJson.get(null, "/products");
        if (json != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Product>>() {
            }.getType();
            ArrayList<Product> products = gson.fromJson(json, listType);
            for (Product p : products) {
                Log.d("JsonGetlistProduct", "id: " + p.getId() + " title: " + p.getTitle());
                db.execSQL("insert into " + DataBaseHelper.PRODUCT_TABLE_NAME + " (id, title, description, img_url, price, brand_id) values " + "('" + p.getId() + "','" + p.getTitle() + "','" + p.getDescription() + "','" + p.getImgUrl() + "'," + p.getPrice() + ",'" + p.getIdBrand() + "')");
            }
        }
    }
}