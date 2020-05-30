package com.example.shoesyourself.helpers;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shoesyourself.entities.Cart;
import com.example.shoesyourself.services.GetJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class CartHelper {

    public static void getFromAPI(SQLiteDatabase db) {
        String json = "[{\"_id\":\"5e4d940cdccea45424a23009\",\"user_id\":\"5e4d940cdccea45424a23008\",\"__v\":0}]";
        json = GetJson.get(json, "/users/5e4d940cdccea45424a23008/cart");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Cart>>() {
        }.getType();
        ArrayList<Cart> cart = gson.fromJson(json, listType);
        for (Cart c : cart) {
            Log.d("JsonGetlistRole", "id: " + c.getId() + " title: " + c.getUserId());
            db.execSQL("insert into " + DataBaseHelper.CART_TABLE_NAME + " (id,user_id) values " + "('" + c.getId() + "','" + c.getUserId() + "')");
        }
    }
}
