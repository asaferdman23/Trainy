package com.asfmtk.trainy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CoachLogin extends AppCompatActivity {
    Button mLoginButton;
    EditText mPassword;
    EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_login_layout);
    }
}