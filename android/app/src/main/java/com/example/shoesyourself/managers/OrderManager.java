package com.example.shoesyourself.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoesyourself.entities.Order;
import com.example.shoesyourself.helpers.DataBaseHelper;
import com.example.shoesyourself.services.ConnectionBD;

import java.util.ArrayList;


public class OrderManager {

    /**
     * Order TABLE FIELDS
     */
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String DATE = "date";
    private static final String queryGetAll = "select * from " + DataBaseHelper.ORDER_TABLE_NAME;
    private static final String queryGetById = "select * from " + DataBaseHelper.ORDER_TABLE_NAME + " where id like ?";
    private static final String queryGetByUserId = "select * from " + DataBaseHelper.ORDER_TABLE_NAME + " where user_id like ?";
    private static final String queryGetAllByDate= "select * from " + DataBaseHelper.ORDER_TABLE_NAME + " where date like ?";
    /**
     * getAll return all Order from DataBase
     *
     * @param context
     * @return ArrayList<Order>
     */
    public static ArrayList<Order> getAll(Context context) {
        ArrayList<Order> orders = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            orders.add(new Order(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2))
            );
        }
        ConnectionBD.close();
        return orders;
    }
    /**
     * getById return an Order by id from DataBase
     *
     * @param context
     * @param id
     * @return Order
     */
    public static Order getById(Context context, int id) {
        Order order= null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetById, new String[]{"" + id});
        while (cursor.moveToNext()) {
            order = new Order(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)

            );
        }
        ConnectionBD.close();
        return order;
    }    /**
     * getById return an Order by user_id from DataBase
     *
     * @param context
     * @param user_id
     * @return Order
     */
    public static Order getByUserId(Context context, int user_id) {
        Order order= null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetByUserId, new String[]{"" + user_id});
        while (cursor.moveToNext()) {
            order = new Order(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        }
        ConnectionBD.close();
        return order;
    }
    /**
     * getAll return all Order by date from DataBase
     *
     * @param context
     * @param date
     * @return ArrayList<Order>
     */
    public static ArrayList<Order> getAllByDate(Context context,String date) {
        ArrayList<Order> orders = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAllByDate, new String[]{date});
        while (cursor.moveToNext()) {
            orders.add(new Order(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2))
            );
        }
        ConnectionBD.close();
        return orders;
    }
    /**
     * Insert Order in DataBase
     *
     * @param context
     * @param order
     */
    public void insert(Context context, Order order) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, order.getUser_id());
        contentValues.put(DATE, order.getDate());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.insert(DataBaseHelper.ROLE_TABLE_NAME, null, contentValues);
    }
    /**
     * Update Order in Database
     *
     * @param context
     * @param order
     */
    public void update(Context context, Order order) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, order.getUser_id());
        contentValues.put(DATE, order.getDate());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.update(DataBaseHelper.ORDER_TABLE_NAME, contentValues, ID + " = " + order.getId(), null);
    }
    /**
     * Delete Order from DataBase
     *
     * @param context
     * @param id
     */
    public static void delete(Context context, int id) {
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.delete(DataBaseHelper.ORDER_TABLE_NAME, "id = ?", new String[]{"" + id});
        ConnectionBD.close();
    }
}
