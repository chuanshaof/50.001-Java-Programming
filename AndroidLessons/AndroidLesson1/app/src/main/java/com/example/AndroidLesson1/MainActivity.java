package com.example.AndroidLesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//TODO 1.1 Put in some images in the drawables folder
//TODO 1.2 Go to activity_main.xml and modify the layout

public class MainActivity extends AppCompatActivity {

    //TODO 1.2 Instance variables are declared for you, please import the libraries
    ArrayList<Integer> images;
    Button charaButton;
    Button charbButton;
    ImageView charaImage;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 1.3 Instantiate An ArrayList object
        //TODO 1.4 Add the image IDs to the ArrayList
        images = new ArrayList<Integer>(Arrays.asList(
                R.drawable.ashketchum,
                R.drawable.bartsimpson,
                R.drawable.bulbasaur,
                R.drawable.defaultimage,
                R.drawable.edogawaconan,
                R.drawable.eevee,
                R.drawable.gyrados,
                R.drawable.judyhopps,
                R.drawable.nemo,
                R.drawable.nickwilde,
                R.drawable.pikachu,
                R.drawable.psyduck,
                R.drawable.snorlax,
                R.drawable.spearow,
                R.drawable.tomandjerry,
                R.drawable.yoda));



        //TODO 1.5 Get references to the charaButton and charaImage widgets using findViewById
        TextView textView = findViewById(R.id.textViewRandomImages);
        ImageView charaImage = findViewById(R.id.charaImage);

        //TODO 1.6 For charaButton, invoke the setOnClickListener method
        //TODO 1.7 Create an anonymous class which implements View.OnClickListener interface
        //TODO 1.8 Within onClick, write code to randomly select an image ID from the ArrayList and display it in the ImageView
        charaButton = findViewById(R.id.charaButton);
        charaButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Random random = new Random();
                        int index = Math.abs(random.nextInt()) % images.size();
                        int id = images.get(index);
                        charaImage.setImageResource(id);
                    }
                }
        );

        //TODO 1.9 [On your own] Create another button, which when clicked, will cause one image to always be displayed
        charbButton = findViewById(R.id.charbButton);
        charbButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        charaImage.setImageResource(R.drawable.pikachu);
                    }
                }
        );

    }
}