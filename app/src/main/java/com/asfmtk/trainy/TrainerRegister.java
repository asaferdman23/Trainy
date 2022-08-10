package com.asfmtk.trainy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TrainerRegister extends AppCompatActivity {
    //variables declarations
    Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_register_layout);
        Log.i(Utils.TAG, "entering to trainer register class");

        //variables initialization
        mSignUpButton = findViewById(R.id.trainer_sign_up);
        signUpButton();
    }

    private void signUpButton() {
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainerRegister.this, OtpInput.class);
                startActivity(intent);
            }
        });
    }
}