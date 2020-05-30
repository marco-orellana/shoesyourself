package com.example.shoesyourself.settings;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoesyourself.R;


public class ToolBarSetting {

    public static void set(AppCompatActivity acticity) {
        acticity.setTheme(R.style.NoBar);
        acticity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        acticity.getSupportActionBar().setIcon(R.drawable.ic_toolbar);
        acticity.getSupportActionBar().setDisplayShowTitleEnabled(true);
    }
}
