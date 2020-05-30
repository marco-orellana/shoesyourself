package com.example.shoesyourself.helpers;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shoesyourself.entities.Brand;
import com.example.shoesyourself.services.GetJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class BrandHelper {

    public static void getFromAPI(SQLiteDatabase db) {
        String json = GetJson.get(null, "/brands");
        if (json != null) {
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Brand>>() {
            }.getType();
            ArrayList<Brand> brands = gson.fromJson(json, listType);
            for (Brand b : brands) {
                Log.d("JsonGetListBrand", "id: " + b.getId() + " title: " + b.getTitle());
                db.execSQL("insert into " + DataBaseHelper.BRAND_TABLE_NAME + " (id,title) values " + "( '" + b.getId() + "', '" + b.getTitle() + "')");
            }
        }
    }
}
