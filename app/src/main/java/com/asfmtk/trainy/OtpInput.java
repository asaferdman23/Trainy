package com.asfmtk.trainy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpInput extends AppCompatActivity {
    Button mSendMeButton;
    EditText mOtpNumberInput;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_input);

        mSendMeButton =findViewById(R.id.otp_send_button);
        mOtpNumberInput =findViewById(R.id.otp_input_number);
        mAuth = FirebaseAuth.getInstance();

        sendingOtp();
    }
    private void sendingOtp() {
        mSendMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otpNumberInputText = mOtpNumberInput.getText().toString();
                if(otpNumberInputText.length() <= 10) {
                    sendVerificationCodeToUser(otpNumberInputText);
                } else {
                    Toast.makeText(getApplicationContext(),"Phone number too short",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+972" + phoneNo)       // Phone number to verify
                        .setTimeout(10L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(OtpVerify.class)                 // Activity (for callback binding)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}
