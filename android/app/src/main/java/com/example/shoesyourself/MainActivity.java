package com.example.shoesyourself;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.res.Configuration;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoesyourself.settings.Preferences;
import com.example.shoesyourself.settings.Switcher;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    TextView textCartItemCount;
    Handler handler;
    Boolean isAdmin, isConnected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        isConnected = Preferences.getIsConnected();
        isAdmin = Preferences.getIsAdmin();
        setDrawerLayout();
        Switcher.show(this, Switcher.CATALOGUE_TAG);
    }
    @Override
    protected void onStart() {
        allowConnectionOption(navigationView);
        super.onStart();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        final MenuItem menuItem = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        textCartItemCount = actionView.findViewById(R.id.cart_badge);
        Preferences.setTextViewCount(textCartItemCount);
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return !Preferences.getIsAdmin();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.action_cart) {
            if (Preferences.getCartListLength() == 0)
                Toast.makeText(this, getString(R.string.cart_empty_text), Toast.LENGTH_SHORT).show();
            else
                Switcher.show(this, Switcher.CART_TAG);
        }
        return super.onOptionsItemSelected(item);
    }
    protected void setDrawerLayout() {
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        navigationView = findViewById(R.id.nvView);
        setupDrawerContent(navigationView);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }
    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.item_profil:
                Toast.makeText(this, "Your Profil Information", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_log_out:
                Preferences.switchOffUser();
                Toast.makeText(this, "You are logging out", Toast.LENGTH_SHORT).show();
                recreate();
                break;
        }
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle().toString().toUpperCase());
        drawerLayout.closeDrawers();
    }
    private void allowConnectionOption(NavigationView navigationView) {
        View headerLayout = navigationView.getHeaderView(0);
        RelativeLayout rlHeaderProfil = headerLayout.findViewById(R.id.drawer_header_profil);
        TextView firstName = rlHeaderProfil.findViewById(R.id.tv_first_name);
        TextView lastName = rlHeaderProfil.findViewById(R.id.tv_last_name);
        TextView email = rlHeaderProfil.findViewById(R.id.tv_email);
        LinearLayout llHeaderSign = headerLayout.findViewById(R.id.drawer_header_sign);
        final Button btnSignIn = headerLayout.findViewById(R.id.btn_sign_in);
        Button btnSignUp = headerLayout.findViewById(R.id.btn_sign_up);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLoginClick(v);
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLoginClick(v);
            }
        });
        if (Preferences.getIsConnected()) {
            rlHeaderProfil.setVisibility(View.VISIBLE);
            llHeaderSign.setVisibility(View.GONE);
            firstName.setText(Preferences.currentUserInfo.getFirstName());
            lastName.setText(Preferences.currentUserInfo.getLastName());
            email.setText(Preferences.currentUserInfo.getEmail());
        } else {
            rlHeaderProfil.setVisibility(View.GONE);
            llHeaderSign.setVisibility(View.VISIBLE);
        }
    }
    public void btnLoginClick(View v) {
        Intent signin = new Intent(MainActivity.this, LoginActivity.class);
        Intent signup = new Intent(MainActivity.this, RegisterActivity.class);
        switch (v.getId()) {
            case R.id.btn_sign_in:
                startActivity(signin);
                break;
            case R.id.btn_sign_up:
                startActivity(signup);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        Switcher.backOrExit(this);
    }
}