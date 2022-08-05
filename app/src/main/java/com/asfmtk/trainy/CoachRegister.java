package com.asfmtk.trainy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CoachRegister extends AppCompatActivity {

    Button coachSignMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_register_layout);
        coachSignMe = (Button) findViewById(R.id.coach_sign_up);
         SignUpCoach();
    }

    public void SignUpCoach() {
        coachSignMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachRegister.this, CoachLogin.class);
                startActivity(intent);
            }
        });
    }
}