package com.example.shoesyourself.services;

import android.util.Log;

import com.example.shoesyourself.helpers.DataBaseHelper;

import java.util.concurrent.ExecutionException;


public class GetJson {

    public static String get(String json, String routes) {
        String response = null;
        HttpGetRequest httpGetRequest = new HttpGetRequest();
        try {
            String host = DataBaseHelper.URL_SERVER;
            response = httpGetRequest.execute(host + routes).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String jsonToReturn = "";
        if (response != null) {
            jsonToReturn = response;
        }
        return jsonToReturn;
    }
}
