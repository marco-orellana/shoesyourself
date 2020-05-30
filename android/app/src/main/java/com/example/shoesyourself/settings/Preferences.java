package com.example.shoesyourself.settings;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.shoesyourself.LoginActivity;
import com.example.shoesyourself.entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class Preferences {

    public static String PREFERENCE_NAME = "SHOESPREFERENCES";
    public static String ISADMIN_KEY = "IKHBFVGB";
    public static String ISCONNECTED_KEY = "DFFGBGDFBGHSJ";
    public static String CARTLISTID_KEY = "FWGFBFHGHJ";
    public static String USER_ID = "JMSADJKSDAJH";
    public static String USER_ROLE_ID = "KLASJKDKASKJDSA";
    public static String USER_EMAIL = "ASDSADJASKASD";
    public static String USER_FIRST_NAME = "ASDASDASDWWQE";
    public static String USER_LAST_NAME = "ASDGHTRTGREE";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public static User currentUserInfo;
    public static TextView cartListCount;
    private static Activity activity;
    public Preferences(Activity activity) {
        Preferences.activity = activity;
    }
    public void init() {
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean(ISADMIN_KEY, false);
        editor.putBoolean(ISCONNECTED_KEY, false);
        editor.apply();
    }
    public static void setUserInPrefences(User user) {
        currentUserInfo = user;
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(USER_ID, user.getId());
        editor.putString(USER_ROLE_ID, user.getRoleId());
        editor.putString(USER_EMAIL, user.getEmail());
        editor.putString(USER_FIRST_NAME, user.getFirstName());
        editor.putString(USER_LAST_NAME, user.getLastName());
        editor.putBoolean(ISCONNECTED_KEY, true);
        editor.apply();
        if (user.getRoleId().equals("5e4d88a93a31da507c490530")) {
            Preferences.setIsAdmin(true);
        }
    }
    public static Boolean getIsAdmin() {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        boolean lo = sharedPref.getBoolean(ISADMIN_KEY, false);
        return lo;
    }
    public static boolean getIsConnected() {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        boolean lo = sharedPref.getBoolean(ISCONNECTED_KEY, false);
        return lo;
    }
    public static void setIsAdmin(Boolean isAdmin) {
        editor.putBoolean(ISADMIN_KEY, isAdmin);
        editor.commit();
    }
    public static void setIsConnected(Boolean isConnected) {
        editor.putBoolean(ISCONNECTED_KEY, isConnected);
        editor.commit();
    }
    public static void setTextViewCount(TextView textViewCount) {
        cartListCount = textViewCount;
        updateBadge();
    }
    public static ArrayList<String> getCartList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CARTLISTID_KEY, null);
        ArrayList<String> cartListId = gson.fromJson(json, new TypeToken<ArrayList<String>>() {
        }.getType());
        return (cartListId == null) ? new ArrayList<String>() : cartListId;
    }
    private static void updateCartList(ArrayList<String> list) {
        Gson gson = new Gson();
        String cartListIdToJson = gson.toJson(list);
        editor.putString(CARTLISTID_KEY, cartListIdToJson);
        editor.commit();
        updateBadge();
    }
    public static int getCartListLength() {
        ArrayList<String> str = getCartList();
        return (str == null) ? 0 : str.size();
    }
    public static boolean existInCart(String id) {
        ArrayList<String> cartListId = getCartList();
        return cartListId.contains(id);
    }
    public static void addToCart(String id) {
        ArrayList<String> cartListId = getCartList();
        cartListId.add(id);
        updateCartList(cartListId);
    }
    public static void removeFromCart(String id) {
        ArrayList<String> cartListId = getCartList();
        cartListId.remove(id);
        updateCartList(cartListId);
    }
    public static void removeAllCartItem() {
        editor.remove(CARTLISTID_KEY);
        editor.commit();
        updateBadge();
    }
    public static void updateBadge() {
        int count = getCartListLength();
        if (count == 0) {
            if (cartListCount.getVisibility() == View.VISIBLE)
                cartListCount.setVisibility(View.GONE);
        } else {
            // Maximum de nombre (99) a afficher dans le badge pour cause de debordement de chiffre
            // mais continue l'enregistrement des produits dans le panier
            cartListCount.setText(String.valueOf(Math.min(count, 99)));
            if (cartListCount.getVisibility() == View.GONE)
                cartListCount.setVisibility(View.VISIBLE);
        }
    }
    public static void switchOffUser() {
        editor.putBoolean(ISCONNECTED_KEY, false);
        editor.putBoolean(ISADMIN_KEY, false);
        editor.commit();
    }
}