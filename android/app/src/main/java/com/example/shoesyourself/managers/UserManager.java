package com.example.shoesyourself.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoesyourself.entities.User;
import com.example.shoesyourself.helpers.DataBaseHelper;
import com.example.shoesyourself.services.ConnectionBD;
import com.example.shoesyourself.services.HttpPostRequest;
import com.example.shoesyourself.services.PostJson;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class UserManager {

    /**
     * PRODUCT TABLE FIELDS
     */
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String IMG_URL = "img_url";
    private static final String PASSWORD = "password";
    private static final String ROLE_ID = "role_id";
    /**
     * PRODUCT TABLE'S QUERIES
     */
    private static final String queryGetAll = "select * from " + DataBaseHelper.USER_TABLE_NAME;
    private static final String queryGetById = "select * from " + DataBaseHelper.USER_TABLE_NAME + " where id like ?";
    private static final String queryGetByRole = "select * from " + DataBaseHelper.USER_TABLE_NAME + " where role_id like ?";
    private static final String queryGetByEmail = "select * from " + DataBaseHelper.USER_TABLE_NAME + " where email like ?";
    private static final String queryGetByEmailAndPassword = "select * from " + DataBaseHelper.USER_TABLE_NAME + " where email like ? and password like ?";
    /**
     * getAll return all users from DataBase
     *
     * @param context
     * @return ArrayList<User>
     */
    public static ArrayList<User> getAll(Context context) {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            users.add(new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6))
            );
        }
        ConnectionBD.close();
        return users;
    }
    /**
     * getById return  User by id from DataBase
     *
     * @param context
     * @param id
     * @return User
     */
    public static User getById(Context context, int id) {
        User user = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetById, new String[]{"" + id});
        if (cursor != null) {
            cursor.moveToFirst();
            user = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6));
        }
        ConnectionBD.close();
        return user;
    }
    /**
     * getByCategory return all users by roles from DataBase
     *
     * @param context
     * @param idRole
     * @return ArrayList<User>
     */
    public static ArrayList<User> getByRole(Context context, int idRole) {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetByRole, new String[]{"" + idRole});
        while (cursor.moveToNext()) {
            users.add(new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6))
            );
        }
        ConnectionBD.close();
        return users;
    }
    /**
     * getByBrand return all user by email from DataBase
     *
     * @param context
     * @param email
     * @return User
     */
    public static User getByEmail(Context context, String email) {
        User user = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetByEmail, new String[]{"" + email});
        while (cursor.moveToNext()) {
            user = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6))
            ;
        }
        ConnectionBD.close();
        return user;
    }
    public static User getByEmailAndPassword(Context context, String email, String password) {
        User user = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetByEmailAndPassword, new String[]{"" + email, "" + password});
        while (cursor.moveToNext()) {
            user = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6))
            ;
        }
        ConnectionBD.close();
        return user;
    }
    /**
     * Insert user in DataBase
     *
     * @param context
     * @param user
     */
    public static void insert(Context context, User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, user.getId());
        contentValues.put(EMAIL, user.getEmail());
        contentValues.put(FIRST_NAME, user.getFirstName());
        contentValues.put(LAST_NAME, user.getLastName());
        contentValues.put(IMG_URL, user.getUrlImg());
        contentValues.put(PASSWORD, user.getPassword());
        contentValues.put(ROLE_ID, user.getRoleId());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.insert(DataBaseHelper.USER_TABLE_NAME, null, contentValues);
    }
    /**
     * Update user in Database
     *
     * @param context
     * @param user
     */
    public static void update(Context context, User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL, user.getEmail());
        contentValues.put(FIRST_NAME, user.getFirstName());
        contentValues.put(LAST_NAME, user.getLastName());
        contentValues.put(IMG_URL, user.getUrlImg());
        contentValues.put(PASSWORD, user.getPassword());
        contentValues.put(ROLE_ID, user.getRoleId());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.update(DataBaseHelper.USER_TABLE_NAME, contentValues, ID + " = " + user.getId(), null);
    }
    /**
     * Delete user from DataBase
     *
     * @param context
     * @param id
     */
    public static void delete(Context context, int id) {
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.delete(DataBaseHelper.USER_TABLE_NAME, "id = ?", new String[]{"" + id});
        ConnectionBD.close();
    }
    public static void postToAPI(Context context, User user) {
        Gson gson = new Gson();
        String jsonToSemd = gson.toJson(user);
        String jsonFromApi = PostJson.post(jsonToSemd, "/users");
        User userFromApi = gson.fromJson(jsonFromApi, User.class);
        UserManager.insert(context, userFromApi);
    }

}
