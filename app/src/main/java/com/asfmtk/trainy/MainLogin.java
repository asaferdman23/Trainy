package com.asfmtk.trainy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainLogin extends AppCompatActivity {

    //clickable text variables declarations
    TextView mRegister;
    TextView mForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);

        //clickable text initialization
        mRegister = (TextView) findViewById(R.id.register);
        mForgotPassword = (TextView) findViewById(R.id.forgot_password);

        //initiate what happens when clicking on Register text
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogin.this, WelcomeActivity.class);
                startActivity(intent);
                Log.i(Utils.TAG, "register_text_click");
            }
        });

//        mForgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainLogin.this, some password recovery .class)
//                startActivity(intent);
//                Log.i(Utils.TAG, "forgot_password");
//            }
//        });

    }
}