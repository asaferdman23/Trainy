package com.asfmtk.trainy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    //buttons variables declarations
    Button coachPage;
    Button trainerPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        //buttons initialization
        coachPage = (Button) findViewById(R.id.coach_button);
        trainerPage = (Button) findViewById(R.id.trainer_button);
        registryPage();
    }

    //method that contains both buttons intentions
    public void registryPage() {
        coachPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sends to coach register page
                Intent intent = new Intent(WelcomeActivity.this, RegisterClass.class);
                startActivity(intent);
                Log.i(Utils.TAG, "coach_registry_button_log");
            }
        });
    }
}