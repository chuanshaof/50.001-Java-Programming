package com.example.recyclerview;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.recyclerview.R;

import java.io.IOException;

public class DataEntry extends AppCompatActivity {

    EditText editTextNameEntry;
    Button buttonSelectImage;
    Button buttonOK;
    ImageView imageViewSelected;
    Bitmap bitmap;
    final static int REQUEST_IMAGE_GET = 2000;
    final static String KEY_PATH = "Image";
    final static String KEY_NAME = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        editTextNameEntry = findViewById(R.id.editTextNameEntry);
        buttonSelectImage = findViewById(R.id.buttonSelectImage);
        imageViewSelected = findViewById(R.id.imageViewSelected);
        buttonOK = findViewById(R.id.buttonOK);

        //TODO 12.2 Set up an implicit intent to the image gallery (standard code)
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");

                // This line is fine if queries are added,
                // https://medium.com/androiddevelopers/package-visibility-in-android-11-cc857f221cd9
                // if (intent.resolveActivity(getPackageManager()) != null) { }

                // But more ideal to use the follow case
                // https://cketti.de/2020/09/03/avoid-intent-resolveactivity/
                try {
                    startActivityForResult(intent, REQUEST_IMAGE_GET);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Access to storage is needed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //TODO 12.4 When the OK button is clicked, set up an intent to go back to MainActivity
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultCode = Activity.RESULT_OK;
                Intent resultIntent = new Intent();

                // Extract the name from the EditText widget:
                String name = editTextNameEntry.getText().toString();

                // Save the bitmap image to Internal storage:
                String path = Utils.saveToInternalStorage(bitmap, name, DataEntry.this);

                // Call putExtra on the intent object to save the name and path
                resultIntent.putExtra(KEY_NAME, name);
                resultIntent.putExtra(KEY_PATH, path);

                setResult(resultCode, resultIntent);
                finish();
            }
        });

        //TODO 12.5 --> Go back to MainActivity

    }

    //TODO 12.3 Write onActivityResult to get the image selected
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            try {
                Uri fullPhotoUri = data.getData();
                bitmap = MediaStore.Images.
                        Media.getBitmap(this.getContentResolver(), fullPhotoUri);
                imageViewSelected.setImageURI(fullPhotoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
