package com.asfmtk.trainy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OtpInput extends AppCompatActivity {
    Button mSendMeButton;
    EditText mOtpNumberInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_input);
        mSendMeButton =findViewById(R.id.otp_send_button);
        mOtpNumberInput =findViewById(R.id.otp_input_number);
        sendingOtp();
    }

    private void sendingOtp() {
        mSendMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otpNumberInputText = mOtpNumberInput.getText().toString();
                if (!TextUtils.isEmpty(otpNumberInputText)){
                    Intent intent =new Intent(OtpInput.this,OtpVerify.class);
                    startActivity(intent);
                } else {
                    mOtpNumberInput.setError("");
                    Toast.makeText(getApplicationContext(),R.string.empty_phone_input,Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}