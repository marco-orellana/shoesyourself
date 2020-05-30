package com.example.shoesyourself.settings;

import android.app.Activity;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoesyourself.R;


public class Switcher {

    public static FragmentManager manager;
    private static FragmentTransaction transaction;
    public static String CATALOGUE_TAG = "Catalogue";
    public static String CART_TAG = "Cart";
    public static String ADDEDITPRODUCT_TAG = "AddEditProduct";
    public static void show(AppCompatActivity activity, String fragmentTag) {
        FragmentManager manager = activity.getSupportFragmentManager();
        Fragment fragment = getFragmentByTag(fragmentTag);
        try {
            transaction = manager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            transaction.replace(R.id.fragment_container, fragment, fragmentTag);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showWithParams(AppCompatActivity activity, Fragment fragment, String fragmentTag) {
        FragmentManager manager = activity.getSupportFragmentManager();
        try {
            transaction = manager.beginTransaction();
            transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            transaction.replace(R.id.fragment_container, fragment, fragmentTag);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Fragment getFragmentByTag(String tag) {
        Fragment fragment = null;
        try {
            Class aClass = Class.forName("com.example.shoesyourself.fragments." + tag);
            fragment = (Fragment) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return fragment;
    }
    public static void backOrExit(AppCompatActivity activity) {
        FragmentManager manager = activity.getSupportFragmentManager();
        Fragment fragment = manager.findFragmentByTag(CATALOGUE_TAG);
        if (fragment == null) {
            show(activity, CATALOGUE_TAG);
        } else {
            activity.finish();
        }
    }
}
