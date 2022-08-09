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

    private FirebaseAuth mAuth;
    private EditText coachName;
    private EditText coachEmail;
    private EditText coachPassword;
    Button coachSignMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_register_layout);
        coachSignMe = (Button) findViewById(R.id.coach_sign_up);
        coachName = (EditText) findViewById(R.id.coach_name_box);
        coachEmail = (EditText) findViewById(R.id.coach_email_box);
        coachPassword = (EditText) findViewById(R.id.coach_password_box);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onClick(View v) {

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

        coachSignMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachRegister.this, OtpInput.class);
                startActivity(intent);
            }
        });
    }
}