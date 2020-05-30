package com.example.shoesyourself.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoesyourself.entities.CartList;
import com.example.shoesyourself.helpers.DataBaseHelper;
import com.example.shoesyourself.services.ConnectionBD;

import java.util.ArrayList;


public class CartListManager {

    /**
     * CartList TABLE FIELDS
     */
    private static final String ID = "id";
    private static final String CART_ID = "cart_id";
    private static final String PRODUCT_ID = "product_id";
    private static final String QUANTITY = "quantity";
    private static final String queryGetAll = "select * from " + DataBaseHelper.CARTLIST_TABLE_NAME;
    private static final String queryGetById = "select * from " + DataBaseHelper.CARTLIST_TABLE_NAME + " where id like ?";
    private static final String queryGetByCartId = "select * from " + DataBaseHelper.CARTLIST_TABLE_NAME + " where cart_id like ?";
    private static final String queryGetAllByProductId = "select * from " + DataBaseHelper.CARTLIST_TABLE_NAME + " where product_id like ?";
    /**
     * getAll return all Cartlists from DataBase
     *
     * @param context
     * @return ArrayList<Cartlist>
     */
    public static ArrayList<CartList> getAll(Context context) {
        ArrayList<CartList> orderItems = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            orderItems.add(new CartList(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3))
            );
        }
        ConnectionBD.close();
        return orderItems;
    }
    /**
     * getById return an Cartlist by id from DataBase
     *
     * @param context
     * @param id
     * @return Cartlist
     */
    public static CartList getById(Context context, int id) {
        CartList cartList = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetById, new String[]{"" + id});
        while (cursor.moveToNext()) {
            cartList = new CartList(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
        }
        ConnectionBD.close();
        return cartList;
    }
    /**
     * getByOrderId return an OrderItems by cart_id from DataBase
     *
     * @param context
     * @param cart_id
     * @return Cartlist
     */
    public static CartList getByCartId(Context context, int cart_id) {
        CartList cartList = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetByCartId, new String[]{"" + cart_id});
        while (cursor.moveToNext()) {
            cartList = new CartList(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
        }
        ConnectionBD.close();
        return cartList;
    }
    /**
     * getAllByProductId return all CartLists by product_id from DataBase
     *
     * @param context
     * @param product_id
     * @return ArrayList<Cartlist>
     */
    public static ArrayList<CartList> getAllByProductId(Context context, int product_id) {
        ArrayList<CartList> cartLists = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAllByProductId, new String[]{"" + product_id});
        while (cursor.moveToNext()) {
            cartLists.add(new CartList(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3))
            );
        }
        ConnectionBD.close();
        return cartLists;
    }
    /**
     * Insert CartList in DataBase
     *
     * @param context
     * @param CartList
     */
    public void insert(Context context, CartList CartList) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CART_ID, CartList.getCart_id());
        contentValues.put(PRODUCT_ID, CartList.getProduct_id());
        contentValues.put(QUANTITY, CartList.getQuantity());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.insert(DataBaseHelper.CARTLIST_TABLE_NAME, null, contentValues);
    }
    /**
     * Update CartList in Database
     *
     * @param context
     * @param CartList
     */
    public void update(Context context, CartList CartList) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CART_ID, CartList.getCart_id());
        contentValues.put(PRODUCT_ID, CartList.getProduct_id());
        contentValues.put(QUANTITY, CartList.getQuantity());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.update(DataBaseHelper.CARTLIST_TABLE_NAME, contentValues, ID + " = " + CartList.getId(), null);
    }
    /**
     * Delete CartList from DataBase
     *
     * @param context
     * @param id
     */
    public static void delete(Context context, int id) {
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.delete(DataBaseHelper.CARTLIST_TABLE_NAME, "id = ?", new String[]{"" + id});
        ConnectionBD.close();
    }
}
