package com.example.shoesyourself.services;

import com.example.shoesyourself.helpers.DataBaseHelper;

import java.util.concurrent.ExecutionException;


public class PutJson {

    public static String put(String json, String routes) {
        String response = null;
        HttpPutRequest httpPutRequest = new HttpPutRequest();
        try {
            String host = DataBaseHelper.URL_SERVER;
            response = httpPutRequest.execute(host + routes, json).get();
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
