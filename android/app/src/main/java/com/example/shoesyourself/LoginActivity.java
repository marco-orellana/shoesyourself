package com.example.shoesyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoesyourself.entities.User;
import com.example.shoesyourself.managers.UserManager;
import com.example.shoesyourself.settings.Preferences;

import java.util.ArrayList;

import static com.example.shoesyourself.settings.Preferences.ISADMIN_KEY;
import static com.example.shoesyourself.settings.Preferences.ISCONNECTED_KEY;
import static com.example.shoesyourself.settings.Preferences.USER_EMAIL;
import static com.example.shoesyourself.settings.Preferences.USER_FIRST_NAME;
import static com.example.shoesyourself.settings.Preferences.USER_ID;
import static com.example.shoesyourself.settings.Preferences.USER_LAST_NAME;
import static com.example.shoesyourself.settings.Preferences.USER_ROLE_ID;


public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btnSignIn;
    Button btnNewMember;
    TextView tvErrorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.et_email_signin);
        etPassword = findViewById(R.id.et_password_signin);
        btnSignIn = findViewById(R.id.btn_signin);
        btnNewMember = findViewById(R.id.btn_not_member);
        tvErrorMessage = findViewById(R.id.tv_error_signin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean fieldIsEmpty;
                String strEmail = etEmail.getText().toString();
                String strPassword = etPassword.getText().toString();
                fieldIsEmpty = allFieldsRequired(strEmail, strPassword);
                if (!fieldIsEmpty) {
                    User user = UserManager.getByEmailAndPassword(LoginActivity.this, strEmail, strPassword);
                    if (user != null) {
                        Preferences.setUserInPrefences(user);
                        finish();
                    } else {
                        tvErrorMessage.setVisibility(View.VISIBLE);
                        tvErrorMessage.setText("* Wrong Email address or Password.");
                    }
                }
            }
        });
        btnNewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUp = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(signUp);
                finish();
            }
        });
    }
    public boolean allFieldsRequired(String strEditText1, String strEditText2) {
        if (TextUtils.isEmpty(strEditText1)) {
            errorMessageFieldsEmpty();
            return true;
        } else if (TextUtils.isEmpty(strEditText2)) {
            errorMessageFieldsEmpty();
            return true;
        }
        return false;
    }
    public void errorMessageFieldsEmpty() {
        tvErrorMessage.setVisibility(View.VISIBLE);
        tvErrorMessage.setText("* Enter your Email address and Password.");
    }
}
