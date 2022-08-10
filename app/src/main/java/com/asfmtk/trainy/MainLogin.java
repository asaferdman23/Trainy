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



        loginBtn.setOnClickListener(v -> {
        loginBtn.startAnimation();
        final String email = mEmail.getText().toString().trim();
        final String password = mPassword.getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please enter your email or password", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(LoginActivity.this, "Please re-enter your email", Toast.LENGTH_SHORT).show();
            mEmail.setError("Valid email is required");
        } else {
            loginUser(email, password);
        }
        // Do your networking task or background work here.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Choose a stop animation if your call was successful or not
                if (isSuccessful) {
                    loginBtn.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND, new TransitionButton.OnAnimationStopEndListener() {
                        @Override
                        public void onAnimationStopEnd() {
                            SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                            name = sp.getString(PREF_USER_NAME, "Motoroler");
                            TastyToast.makeText(LoginActivity.this, "Welcome back " + name, TastyToast.LENGTH_LONG, TastyToast.SUCCESS).show();
                            Intent intent = new Intent(LoginActivity.this, TabActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(intent);
                        }
                    });
                } else {
                    loginBtn.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null);
                    TastyToast.makeText(LoginActivity.this, "Wrong Username or Password", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();
                }
            }
        }, 2000);
    });
}

    private void loginUser(String email, String password) {
        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                boolean boolIsChecked = rememberMe.isChecked();
                if (boolIsChecked) {
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putBoolean(PREF_IS_CHECKED_EXTRA, boolIsChecked);
                    editor.apply();
                }
                mDatabaseReference.child(fAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String name = "";
                            SharedPreferences.Editor editor = mPrefs.edit();
                            for (DataSnapshot dataSnapshotValue : dataSnapshot.getChildren()) {
                                switch (dataSnapshotValue.getKey()) {
                                    case "mEmail":
                                        String email1 = dataSnapshotValue.getValue() != null ? dataSnapshotValue.getValue().toString() : "";
                                        editor.putString(PREF_USER_EMAIL, email1);
                                        editor.apply();
                                    case "mName":
                                        name = dataSnapshotValue.getValue() != null ? dataSnapshotValue.getValue().toString() : "";
                                        editor.putString(PREF_USER_NAME, name);
                                        editor.apply();
                                    case "mTeam":
                                        String team = dataSnapshotValue.getValue() != null ? dataSnapshotValue.getValue().toString() : "";
                                        editor.putString(PREF_USER_TEAM, team);
                                        editor.apply();
                                    case "mTelephone":
                                        String telephone = dataSnapshotValue.getValue() != null ? dataSnapshotValue.getValue().toString() : "";
                                        editor.putString(PREF_USER_TELEPHONE, telephone);
                                        editor.apply();
                                }
                            }
                            isSuccessful = true;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            } else {
                isSuccessful = false;
            }
            mPassword.getText().clear();
            mEmail.getText().clear();
        });
        Log.i(TAG, "Login Activity");
    }
}

asafFilter.txt
        Displaying asafFilter.txt.
}