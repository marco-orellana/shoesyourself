package com.example.shoesyourself.helpers;

import android.database.sqlite.SQLiteDatabase;


public class OrderHelper {

    public static void getFromAPI(SQLiteDatabase db) {
        // REQUETE POUR RECUPERER LES DONNEES DE LA TABLE ORDER
        db.execSQL("insert into " + DataBaseHelper.ORDER_TABLE_NAME + " (id,user_id,date) values " +
                "('2nj3e8uh328hr4jnfidoswijq','wemfjewquwj2ewifnjdwq','2020/17/2') ");
    }
}
