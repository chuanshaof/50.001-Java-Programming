package com.example.AndroidLesson2;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SubActivity extends AppCompatActivity {

    Button buttonBackToCalculator;
    EditText editTextSubValueOfA;
    EditText editTextSubValueOfB;
    public final static String INTENT_EXCH_RATE = "Exchange Rate";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.subsharedprefs";
    public final static String A_KEY = "A_KEY";
    public final static String B_KEY = "B_KEY";
    String subValueOfA;
    String subValueOfB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //TODO 4.9 Implement saving to shared preferences for the contents of the EditText widget
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        subValueOfA = mPreferences.getString(A_KEY, null);
        subValueOfB = mPreferences.getString(B_KEY, null);

        //TODO 3.5 Get references to the editText widgets
        //TODO 3.6 Get a reference to the Back To Calculator Button
        buttonBackToCalculator = findViewById(R.id.buttonBackToCalculator);
        editTextSubValueOfA = findViewById(R.id.editTextSubValueA);
        editTextSubValueOfB = findViewById(R.id.editTextSubValueB);

        if (subValueOfA != null) {
            editTextSubValueOfA.setText(subValueOfA);
        }

        if (subValueOfB != null) {
            editTextSubValueOfB.setText(subValueOfB);
        }

        //TODO 3.7 Set up setOnClickListener
        //TODO 3.8 Obtain the values stored in the editTextWidgets
        //TODO 3.9 Check that these values are valid --> See the Utils class
        //TODO 3.10 Set up an explicit intent and pass the exchange rate back to MainActivity
        //TODO 3.11 Decide how you are going to handle a divide-by-zero situation or when negative numbers are entered
        //TODO 3.12 Decide how you are going to handle a situation when the editText widgets are empty
        buttonBackToCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subValueOfA = editTextSubValueOfA.getText().toString();
                subValueOfB = editTextSubValueOfB.getText().toString();

                try {
                    ExchangeRate exchangeRate = new ExchangeRate(subValueOfA, subValueOfB);
                    Intent intent = new Intent(SubActivity.this, MainActivity.class);
                    intent.putExtra(MainActivity.RATE_KEY, exchangeRate.getExchangeRate().toString());
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(SubActivity.this, R.string.warning_blank_edit_text, Toast.LENGTH_SHORT).show();
                    Log.e("Logcat", getString(R.string.warning_blank_edit_text));
                } catch (IllegalArgumentException e) {
                    Toast.makeText(SubActivity.this, R.string.warning_invalid_edit_text, Toast.LENGTH_SHORT).show();
                    Log.e("Logcat", getString(R.string.warning_invalid_edit_text));
                }
            }
        });

    }

    //TODO 4.10 Don't forget to override onPause()
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString(A_KEY, editTextSubValueOfA.getText().toString());
        preferencesEditor.putString(B_KEY, editTextSubValueOfB.getText().toString());
        preferencesEditor.apply();
    }

}
