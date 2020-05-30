package com.example.shoesyourself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoesyourself.entities.User;
import com.example.shoesyourself.managers.UserManager;


public class RegisterActivity extends AppCompatActivity {

    EditText edFirstName;
    EditText edLastName;
    EditText edEmail;
    EditText edPassword;
    EditText edRePassword;
    CheckBox cbAgree;
    Button btnSignUp;
    Button btnAlreadyMember;
    TextView tvErrorMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edFirstName = findViewById(R.id.et_firstname_signup);
        edLastName = findViewById(R.id.et_lastname_signup);
        edEmail = findViewById(R.id.et_email_signup);
        edPassword = findViewById(R.id.et_password_signup);
        edRePassword = findViewById(R.id.et_repassword_signup);
        cbAgree = findViewById(R.id.cb_signup);
        btnSignUp = findViewById(R.id.btn_signup);
        btnAlreadyMember = findViewById(R.id.btn_already_member);
        tvErrorMessage = findViewById(R.id.tv_error_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean fieldIsEmpty;
                String userRole = "5e4d88ae3a31da507c490531";
                String strFirstName = edFirstName.getText().toString();
                String strLastName = edLastName.getText().toString();
                String strEmail = edEmail.getText().toString();
                String strPassword = edPassword.getText().toString();
                String strRePassword = edRePassword.getText().toString();
                fieldIsEmpty = allFieldsRequired(strFirstName, strLastName, strEmail, strPassword, strRePassword);
                if (!fieldIsEmpty && cbAgree.isChecked() && strPassword.equals(strRePassword)) {
                    User user = new User(userRole, strEmail, strFirstName, strLastName, "test.png", strPassword);
                    UserManager.postToAPI(RegisterActivity.this, user);
                    Intent signin = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(signin);
                    finish();
                } else if (!fieldIsEmpty && cbAgree.isChecked() && !strPassword.equals(strRePassword)) {
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    tvErrorMessage.setText("* Please match the requested password !");
                } else if (!fieldIsEmpty) {
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    tvErrorMessage.setText("* Check to indicate that you have read and agree to the Terms of Service !");
                }
            }
        });
        btnAlreadyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(signin);
                finish();
            }
        });
    }
    public boolean allFieldsRequired(String strEditText1, String strEditText2, String strEditText3, String strEditText4, String strEditText5) {
        if (TextUtils.isEmpty(strEditText1)) {
            errorMessageFieldsEmpty();
            return true;
        } else if (TextUtils.isEmpty(strEditText2)) {
            errorMessageFieldsEmpty();
            return true;
        } else if (TextUtils.isEmpty(strEditText3)) {
            errorMessageFieldsEmpty();
            return true;
        } else if (TextUtils.isEmpty(strEditText4)) {
            errorMessageFieldsEmpty();
            return true;
        } else if (TextUtils.isEmpty(strEditText5)) {
            errorMessageFieldsEmpty();
            return true;
        }
        return false;
    }
    public void errorMessageFieldsEmpty() {
        tvErrorMessage.setVisibility(View.VISIBLE);
        tvErrorMessage.setText("* All fields are required !");
    }
}
