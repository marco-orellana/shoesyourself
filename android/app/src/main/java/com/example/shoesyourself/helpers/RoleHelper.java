package com.example.shoesyourself.helpers;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shoesyourself.entities.Role;
import com.example.shoesyourself.services.GetJson;
import com.example.shoesyourself.services.HttpGetRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class RoleHelper {

    public static void getFromAPI(SQLiteDatabase db) {
        String json = "[{\"_id\":\"5e4d88a93a31da507c490530\",\"title\":\"Admin\",\"__v\":0},{\"_id\":\"5e4d88ae3a31da507c490531\",\"title\":\"User\",\"__v\":0},{\"_id\":\"5e4d88b13a31da507c490532\",\"title\":\"Guest\",\"__v\":0}]";
        json = GetJson.get(json, "/roles");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Role>>() {
        }.getType();
        ArrayList<Role> roles = gson.fromJson(json, listType);
        for (Role r : roles) {
            Log.d("JsonGetlistRole", "id: " + r.getId() + " title: " + r.getTitle());
            db.execSQL("insert into " + DataBaseHelper.ROLE_TABLE_NAME + " (id,title) values " + "('" + r.getId() + "','" + r.getTitle() + "')");
        }
    }
}
