package com.example.shoesyourself.services;

import android.util.Log;

import com.example.shoesyourself.helpers.DataBaseHelper;

import java.util.concurrent.ExecutionException;


public class PostJson {

    public static String post(String json, String routes) {
        String response = null;
        HttpPostRequest httpPostRequest = new HttpPostRequest();
        try {
            String host = DataBaseHelper.URL_SERVER;
            response = httpPostRequest.execute(host + routes, json).get();
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
