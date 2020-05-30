package com.example.shoesyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoesyourself.settings.Preferences;


public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView bgApp, shoesLeft, shoesRight;
    Button btnStart;
    TextView tvLogo;
    Handler handler;
    int delayStart, delayEnd;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.NoBar);
        setContentView(R.layout.activity_start);
        ctx = this;
        bgApp = findViewById(R.id.bgapp);
        shoesLeft = findViewById(R.id.shoes_left);
        shoesRight = findViewById(R.id.shoes_right);
        tvLogo = findViewById(R.id.tv_logo);
        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        handler = new Handler();
        delayStart = 3500;
        delayEnd = 1000;
        animationStart();
        Preferences preferences = new Preferences(this);
        preferences.init();
    }
    public void animationStart() {
        shoesLeft.animate().rotationYBy(720).setDuration(delayStart).start();
        shoesRight.animate().rotationYBy(-720).setDuration(delayStart).start();
        tvLogo.animate().rotationXBy(1080).setDuration(delayStart).start();
        handler.postDelayed(showBtnStart, delayStart);
    }
    public void animationEnd() {
        bgApp.animate().translationY(-3000).setDuration(delayEnd).start();
        shoesLeft.animate().translationX(600).setDuration(delayEnd).start();
        shoesRight.animate().translationX(-600).setDuration(delayEnd).start();
        tvLogo.animate().translationY(1000).setDuration(delayEnd).start();
        handler.postDelayed(launchMain, delayEnd);
    }
    @Override
    public void onClick(View v) {
        animationEnd();
        v.setVisibility(View.INVISIBLE);
    }
    private Runnable showBtnStart = new Runnable() {
        public void run() {
            btnStart.setVisibility(View.VISIBLE);
        }
    };
    private Runnable launchMain = new Runnable() {
        public void run() {
            Intent i = new Intent(StartActivity.this, MainActivity.class);
            //Intent i = new Intent(StartActivity.this, DrawerActivity.class);
            startActivity(i);
            finish();
        }
    };
}
