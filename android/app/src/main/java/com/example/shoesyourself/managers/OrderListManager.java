package com.example.shoesyourself.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shoesyourself.entities.OrderList;
import com.example.shoesyourself.helpers.DataBaseHelper;
import com.example.shoesyourself.services.ConnectionBD;

import java.util.ArrayList;


public class OrderListManager {

    /**
     * OrderItems TABLE FIELDS
     */
    private static final String ID = "id";
    private static final String ORDER_ID = "order_id";
    private static final String PRODUCT_ID = "product_id";
    private static final String QUANTITY = "quantity";
    private static final String queryGetAll = "select * from " + DataBaseHelper.ORDERLIST_TABLE_NAME;
    private static final String queryGetById = "select * from " + DataBaseHelper.ORDERLIST_TABLE_NAME + " where id like ?";
    private static final String queryGetByOrderId = "select * from " + DataBaseHelper.ORDERLIST_TABLE_NAME + " where order_id like ?";
    private static final String queryGetAllByProductId = "select * from " + DataBaseHelper.ORDERLIST_TABLE_NAME + " where product_id like ?";
    /**
     * getAll return all OrderList from DataBase
     *
     * @param context
     * @return ArrayList<OrderList>
     */
    public static ArrayList<OrderList> getAll(Context context) {
        ArrayList<OrderList> orderItems = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAll, null);
        while (cursor.moveToNext()) {
            orderItems.add(new OrderList(
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
     * getById return an OrderList by id from DataBase
     *
     * @param context
     * @param id
     * @return OrderItems
     */
    public static OrderList getById(Context context, int id) {
        OrderList orderList = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetById, new String[]{"" + id});
        while (cursor.moveToNext()) {
            orderList = new OrderList(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
        }
        ConnectionBD.close();
        return orderList;
    }
    /**
     * getByOrderId return an OrderList by order_id from DataBase
     *
     * @param context
     * @param order_id
     * @return ArrayList<OrderList>
     */
    public static OrderList getByOrderId(Context context, int order_id) {
        OrderList orderList = null;
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetByOrderId, new String[]{"" + order_id});
        while (cursor.moveToNext()) {
            orderList = new OrderList(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
        }
        ConnectionBD.close();
        return orderList;
    }
    /**
     * getAllByProductId return all OrderItems by product_id from DataBase
     *
     * @param context
     * @param product_id
     * @return ArrayList<OrderList>
     */
    public static ArrayList<OrderList> getAllByProductId(Context context, int product_id) {
        ArrayList<OrderList> orderItems = new ArrayList<>();
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        Cursor cursor = bd.rawQuery(queryGetAllByProductId, new String[]{"" + product_id});
        while (cursor.moveToNext()) {
            orderItems.add(new OrderList(
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
     * Insert OrderList in DataBase
     *
     * @param context
     * @param orderList
     */
    public void insert(Context context, OrderList orderList) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_ID, orderList.getOrder_id());
        contentValues.put(PRODUCT_ID, orderList.getProduct_id());
        contentValues.put(QUANTITY, orderList.getQuantity());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.insert(DataBaseHelper.ORDERLIST_TABLE_NAME, null, contentValues);
    }
    /**
     * Update OrderList in Database
     *
     * @param context
     * @param orderList
     */
    public void update(Context context, OrderList orderList) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDER_ID, orderList.getOrder_id());
        contentValues.put(PRODUCT_ID, orderList.getProduct_id());
        contentValues.put(QUANTITY, orderList.getQuantity());
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.update(DataBaseHelper.ORDERLIST_TABLE_NAME, contentValues, ID + " = " + orderList.getId(), null);
    }
    /**
     * Delete OrderList from DataBase
     *
     * @param context
     * @param id
     */
    public static void delete(Context context, int id) {
        SQLiteDatabase bd = ConnectionBD.getBd(context);
        bd.delete(DataBaseHelper.ORDERLIST_TABLE_NAME, "id = ?", new String[]{"" + id});
        ConnectionBD.close();
    }
}
