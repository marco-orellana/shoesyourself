package com.example.shoesyourself.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoesyourself.entities.Role;
import com.example.shoesyourself.helpers.DataBaseHelper;
import com.example.shoesyourself.services.ConnectionBD;

import java.util.ArrayList;


public class RoleManager {

    /**
     * Role TABLE FIELDS
     */
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String queryGetAll = "select * from " + DataBaseHelper.ROLE_TABLE_NAME;
    private static final String queryGetById = "select * from " + DataBaseHelper.ROLE_TABLE_NAME + " where id like ?";
    private static final String queryGetByTitle = "select * from " + DataBaseHelper.ROLE_TABLE_NAME + " where title like ?";
    /**
     * getAll return all Role from DataBase
     *
     * @param context
     * @return ArrayList<Role>
     */
    public static ArrayList<Role> getAll(Context context) {
        ArrayList<Role> roles = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            roles.add(new Role(
                    cursor.getString(0),
                    cursor.getString(1))
            );
        }
        ConnectionBD.close();
        return roles;
    }
    /**
     * getById return a Role by id from DataBase
     *
     * @param context
     * @return ArrayList<Role>
     */
    public static Role getById(Context context, int id) {
        Role role = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetById, new String[]{"" + id});
        while (cursor.moveToNext()) {
            role = new Role(
                    cursor.getString(0),
                    cursor.getString(1)
            );
        }
        ConnectionBD.close();
        return role;
    }    /**
     * getById return a Role by title from DataBase
     *
     * @param context
     * @param title
     * @return ArrayList<Role>
     */
    public static Role getById(Context context, String title) {
        Role role = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetByTitle, new String[]{title});
        while (cursor.moveToNext()) {
            role = new Role(
                    cursor.getString(0),
                    cursor.getString(1)
            );
        }
        ConnectionBD.close();
        return role;
    }
    /**
     * Insert Role in DataBase
     *
     * @param context
     * @param role
     */
    public void insert(Context context, Role role) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, role.getTitle());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.insert(DataBaseHelper.ROLE_TABLE_NAME, null, contentValues);
    }
    /**
     * Update Role in Database
     *
     * @param context
     * @param role
     */
    public void update(Context context, Role role) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, role.getTitle());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.update(DataBaseHelper.ROLE_TABLE_NAME, contentValues, ID + " = " + role.getId(), null);
    }
    /**
     * Delete Role from DataBase
     *
     * @param context
     * @param id
     */
    public static void delete(Context context, int id) {
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.delete(DataBaseHelper.ROLE_TABLE_NAME, "id = ?", new String[]{"" + id});
        ConnectionBD.close();
    }
}
