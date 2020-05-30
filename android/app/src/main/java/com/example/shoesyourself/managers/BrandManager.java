package com.example.shoesyourself.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoesyourself.entities.Brand;
import com.example.shoesyourself.helpers.DataBaseHelper;
import com.example.shoesyourself.services.ConnectionBD;

import java.util.ArrayList;


public class BrandManager {

    private static final String queryGetAll = "select * from " + DataBaseHelper.BRAND_TABLE_NAME;
    private static final String queryGetAllOnlyTitle = "select title from " + DataBaseHelper.BRAND_TABLE_NAME;
    /**
     * getAll return all Brand from DataBase
     *
     * @param context
     * @return ArrayList<Brand>
     */
    public static ArrayList<Brand> getAll(Context context) {
        ArrayList<Brand> brands = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            brands.add(new Brand(
                    cursor.getString(0),
                    cursor.getString(1))
            );
        }
        ConnectionBD.close();
        return brands;
    }
    /**
     * getAll return all Brand Title from DataBase
     *
     * @param context
     * @return ArrayList<Brand>
     */
    public static ArrayList<String> getAllTitle(Context context) {
        ArrayList<String> brandsTitle = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAllOnlyTitle, null);
        while (cursor.moveToNext()) {
            brandsTitle.add(cursor.getString(0));
        }
        ConnectionBD.close();
        return brandsTitle;
    }
}
