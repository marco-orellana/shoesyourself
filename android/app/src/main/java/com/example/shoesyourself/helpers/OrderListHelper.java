package com.example.shoesyourself.helpers;

import android.database.sqlite.SQLiteDatabase;


public class OrderListHelper {

    public static void getFromAPI(SQLiteDatabase db) {
        // REQUETE POUR RECUPERER LES DONNEES DE LA TABLE CATEGORY
        db.execSQL("insert into " + DataBaseHelper.ORDERLIST_TABLE_NAME + " (id,order_id,product_id,quantity) values " +
                "('230er8fuvjkde934urj','efrgijrn43ekmeoi9rjg','ewdfvgjr3e2mwkode',1)");
    }
}
