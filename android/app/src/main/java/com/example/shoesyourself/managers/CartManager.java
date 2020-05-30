package com.example.shoesyourself.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoesyourself.entities.Cart;
import com.example.shoesyourself.helpers.DataBaseHelper;
import com.example.shoesyourself.services.ConnectionBD;

import java.util.ArrayList;


public class CartManager {

    /**
     * Cart TABLE FIELDS
     */
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String queryGetAll = "select * from " + DataBaseHelper.CART_TABLE_NAME;
    /**
     * getAll return all Cart from DataBase
     *
     * @param context
     * @return ArrayList<Cart>
     */
    public static ArrayList<Cart> getAll(Context context) {
        ArrayList<Cart> categories = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            categories.add(new Cart(
                    cursor.getString(0),
                    cursor.getString(1))
            );
        }
        ConnectionBD.close();
        return categories;
    }
    /**
     * Insert Cart in DataBase
     *
     * @param context
     * @param Cart
     */
    public long insert(Context context, Cart Cart) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, Cart.getUserId());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        return bd.insert(DataBaseHelper.CART_TABLE_NAME, null, contentValues);
    }
    /**
     * Update Cart in Database
     *
     * @param context
     * @param Cart
     */
    public void update(Context context, Cart Cart) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, Cart.getUserId());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.update(DataBaseHelper.CART_TABLE_NAME, contentValues, ID + " = " + Cart.getId(), null);
    }
    /**
     * Delete Cart from DataBase
     *
     * @param context
     * @param id
     */
    public static void delete(Context context, int id) {
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.delete(DataBaseHelper.CART_TABLE_NAME, "id = ?", new String[]{"" + id});
        ConnectionBD.close();
    }
}
