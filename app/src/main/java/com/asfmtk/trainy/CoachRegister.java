package com.asfmtk.trainy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class CoachRegister extends AppCompatActivity {

    //declarations for firebase variable, inputs from user and sign in button
    private FirebaseAuth mAuth;
    private FirebaseAuth mUser;
    private EditText coachName, coachEmail, coachPassword;
    Button coachSignMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_register_layout);

        //page variables initialization
        coachName = (EditText) findViewById(R.id.coach_name_box);
        coachEmail = (EditText) findViewById(R.id.coach_email_box);
        coachPassword = (EditText) findViewById(R.id.coach_password_box);
        coachSignMe = (Button) findViewById(R.id.coach_sign_up);

        //firebase variables initialization
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getInstance();

        coachSignMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if conditions are needed here
                //calling the method that check all the terms
                performAuth();

                //what happens after clicking sign up button
                Intent intent = new Intent(CoachRegister.this, OtpInput.class);
                startActivity(intent);
            }
        });
    }

    //the actual method that checks the terms
    private void performAuth() {
        String coachNameInputString = coachName.getText().toString();
        String coachEmailInputString = coachEmail.getText().toString().trim();
        String coachPasswordInputString = coachPassword.getText().toString();

        if (coachNameInputString.isEmpty()) {
            coachName.setError("Full name is required!");
            coachName.requestFocus();
            return;
        }

        if (coachEmailInputString.isEmpty()) {
            coachEmail.setError("Email is required!");
            coachEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(coachEmailInputString).matches()) {
            coachEmail.setError("Please provide valid email!");
            coachEmail.requestFocus();
            return;
        }

        if (coachPasswordInputString.isEmpty()) {
            coachPassword.setError("Password is required1");
            coachPassword.requestFocus();
            return;
        }

        if (coachPasswordInputString.length() < 6) {
            coachPassword.setError("Minimum password length should be 6 characters!");
            coachPassword.requestFocus();
            return;
        }
    }
}