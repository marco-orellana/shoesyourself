package com.example.shoesyourself.helpers;

import android.database.sqlite.SQLiteDatabase;


public class CartListHelper {

    public static void getFromAPI(SQLiteDatabase db) {
        // REQUETE POUR RECUPERER LES DONNEES DE LA TABLE CATEGORY
        db.execSQL("insert into " + DataBaseHelper.CARTLIST_TABLE_NAME + " (id,cart_id,product_id,quantity) values " +
                "('23847yrthgjifd8u','wemrfgti8u3j','123rtgfdsqw23','123ertgfdswer')");
    }
}
