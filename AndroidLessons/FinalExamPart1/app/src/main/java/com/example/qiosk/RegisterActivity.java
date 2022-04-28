package com.example.qiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText bookingID;
    EditText mobileNumber;
    Button regButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bookingID = findViewById(R.id.bookingidEditText);
        mobileNumber = findViewById(R.id.mobilenumberEditText);
        regButton = findViewById(R.id.registerButton);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sMobile = mobileNumber.getText().toString();
                try {
                    int mobile = Integer.parseInt(sMobile);
                    if (mobile > 99999999 || mobile < 10000000) { throw new NumberFormatException(); }
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra(MainActivity.INTENT_KEYS.MOBILE, mobile);
                    returnIntent.putExtra(MainActivity.INTENT_KEYS.BOOKINGID, bookingID.getText().toString());
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } catch (NumberFormatException e) {
                    Toast.makeText(RegisterActivity.this, "Invalid mobile number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}