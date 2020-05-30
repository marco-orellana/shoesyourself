package com.example.shoesyourself.helpers;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shoesyourself.entities.User;
import com.example.shoesyourself.managers.UserManager;
import com.example.shoesyourself.services.GetJson;
import com.example.shoesyourself.services.HttpPostRequest;
import com.example.shoesyourself.services.PostJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class UserHelper {

    public static void getFromAPI(SQLiteDatabase db) {
        String json = "[{\"_id\":\"5e4d940cdccea45424a23008\",\"role_id\":\"5e4d88a93a31da507c490530\",\"email\":\"f.gaudreau05@gmail.com\",\"first_name\":\"Fred\",\"last_name\":\"Gaudreau\",\"img_url\":\"fred.jpg\",\"password\":\"$2a$12$kFH.mFRYO.xdQUawZDOZ.ujebYv/ZgUR7GIeg.1BVS31bOZMBDGDq\",\"__v\":0}]";
        json = GetJson.get(json, "/users");
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<User>>() {
        }.getType();
        ArrayList<User> users = gson.fromJson(json, listType);
        for (User u : users) {
            Log.d("JsonGetlistUser", "id: " + u.getId() + " title: " + u.getFirstName());
            db.execSQL("insert into " + DataBaseHelper.USER_TABLE_NAME + " (id,email, first_name, last_name, img_url, password, role_id) values " + "('" + u.getId() + "','" + u.getEmail() + "','" + u.getFirstName() + "','" + u.getLastName() + "','" + u.getUrlImg() + "','" + u.getPassword() + "','" + u.getRoleId() + "')");
        }
//        User userToSend = new User("5e4d88a93a31da507c490530","marco@polo.com", "marco", "polo", "test.png", "abc123...");
//        String jsonToSemd = gson.toJson(userToSend);
//        Log.d("JsonTosendUser", jsonToSemd);
//        String jsonFromApi = PostJson.post(jsonToSemd, "/users");
//        User userFromApi = gson.fromJson(jsonFromApi, User.class);
//        Log.d("JsonGetlistUser", "id: " + userFromApi.getId() + " title: " + userFromApi.getFirstName());
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("id", userFromApi.getId());
//        contentValues.put("email", userFromApi.getEmail());
//        contentValues.put("first_name", userFromApi.getFirstName());
//        contentValues.put("last_name", userFromApi.getLastName());
//        contentValues.put("img_url", userFromApi.getUrlImg());
//        contentValues.put("password", userFromApi.getPassword());
//        contentValues.put("role_id", userFromApi.getRoleId());
//        db.insert(DataBaseHelper.USER_TABLE_NAME, null, contentValues);
    }
}

