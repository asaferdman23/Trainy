package com.asfmtk.trainy;

import static com.asfmtk.trainy.Utils.USERS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.Objects;

public class CoachRegister extends AppCompatActivity {

    //declarations for firebase variable, inputs from user and sign in button
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseAuth mUser;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ourUsers = database.getReference("User");
    private EditText coachName, coachEmail, coachPasswordFirst, coachPasswordSecond;
    Button coachSignMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coach_register_layout);

        //page variables initialization
        coachName = (EditText) findViewById(R.id.coach_name_box);
        coachEmail = (EditText) findViewById(R.id.coach_email_box);
        coachPasswordFirst = (EditText) findViewById(R.id.coach_password_box1);
        coachPasswordSecond = (EditText) findViewById(R.id.coach_password_box2);
        coachSignMe = (Button) findViewById(R.id.coach_sign_up);



        mAuth = FirebaseAuth.getInstance();
        ourUsers = FirebaseDatabase.getInstance().getReference(USERS);
        //firebase variables initialization


        coachSignMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //calling the method that check all the terms
                String coachNameInputString = coachName.getText().toString();
                String coachEmailInputString = coachEmail.getText().toString();
                String coachPasswordInputStringFirst = coachPasswordFirst.getText().toString();
                String coachPasswordInputStringSecond = coachPasswordSecond.getText().toString();

                if (coachNameInputString.isEmpty()) {
                    Log.i(Utils.TAG, "name is empty");
                    coachName.setError("Full name is required!");
//                    coachName.requestFocus();
                    return;
                }

                if (coachEmailInputString.isEmpty()) {
                    Log.i(Utils.TAG, "email is empty");
                    coachEmail.setError("Email is required!");
//                    coachEmail.requestFocus();
                    return;
                }

//                if (!Patterns.EMAIL_ADDRESS.matcher(coachEmailInputString).matches()) {
//                    Log.i(Utils.TAG, "email doesnt match pattern");
//                    coachEmail.setError("Please provide valid email!");
//                    coachEmail.requestFocus();
//                    return;
//                }

                if (coachPasswordInputStringFirst.isEmpty()) {
                    Log.i(Utils.TAG, "first password is empty");
                    coachPasswordFirst.setError("Password is required1");
//                    coachPassword.requestFocus();
                    return;
                }

                if (coachPasswordInputStringFirst.length() < 6) {
                    Log.i(Utils.TAG, "first password is too short");
                    coachPasswordFirst.setError("Password is required1");
//                    coachPassword.requestFocus();
                    return;
                }

                if (coachPasswordInputStringSecond.isEmpty()) {
                    Log.i(Utils.TAG, "second password is empty");
                    coachPasswordSecond.setError("Minimum password length should be 6 characters!");
//                    coachPassword.requestFocus();
                    return;
                }

                if (coachPasswordInputStringSecond.length() < 6) {
                    Log.i(Utils.TAG, "second password is too short");
                    coachPasswordSecond.setError("Minimum password length should be 6 characters!");
//                    coachPassword.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(coachEmailInputString, coachPasswordInputStringFirst)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(Utils.TAG, "createUserWithEmailAndPassword:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //what happens after clicking sign up button
                                    Intent intent = new Intent(CoachRegister.this, OtpInput.class);
                                    startActivity(intent);
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(Utils.TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                            }
                        });
                mAuth.createUserWithEmailAndPassword(coachEmailInputString, coachPasswordInputStringFirst).addOnCompleteListener((Task<AuthResult> task) -> {

                    if (task.isSuccessful()) {
                        Users user = new Users(coachEmail.getText().toString(), coachPasswordFirst.getText().toString(), );
                        registerUser(user);
                    } else {
                        Toast.makeText(CoachRegister.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            private void registerUser(Users user) {
                ourUsers.child(Objects.requireNonNull(mAuth.getCurrentUser()).getUid()).setValue(user);
                Toast.makeText(CoachRegister.this, "User Created", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), OtpInput.class));
            }
        });
    }
}