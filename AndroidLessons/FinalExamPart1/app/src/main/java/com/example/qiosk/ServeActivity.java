package com.example.qiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServeActivity extends AppCompatActivity {

    TextView servingTextView;
    Button servedButton;
    Button returnButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serve);

        servingTextView = findViewById(R.id.servingTextView);
        servedButton = findViewById(R.id.servedButton);
        returnButton = findViewById(R.id.returnButton);

        Intent intentFromMain = getIntent();
        final int mobile = intentFromMain.getIntExtra(MainActivity.INTENT_KEYS.MOBILE, 0);
        String bookingId = intentFromMain.getStringExtra(MainActivity.INTENT_KEYS.BOOKINGID);
        servingTextView.setText(String.valueOf(mobile));

        servedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(MainActivity.INTENT_KEYS.MOBILE, mobile);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }
}