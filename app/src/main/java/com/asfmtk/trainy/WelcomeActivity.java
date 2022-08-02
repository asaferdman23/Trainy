package com.asfmtk.trainy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button coachPage;
    Button trainerPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        coachPage = (Button) findViewById(R.id.coach_button);
        trainerPage = (Button) findViewById(R.id.trainer_button);
        RegistryPage();
//        trainerRegistryPage();
    }

    public void RegistryPage() {
        coachPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, CoachRegister.class);
                startActivity(intent);
                Log.i("TAG", "registryButtonLog");
            }
        });

        trainerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, TrainerRegister.class);
                startActivity(intent);
                Log.i("TAG", "trainerButtonLog");
            }
        });
    }
}