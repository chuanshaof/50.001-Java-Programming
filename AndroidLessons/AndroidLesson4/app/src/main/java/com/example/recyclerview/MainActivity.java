package com.example.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.recyclerview.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

// Remove implements if using alternative method
public class MainActivity extends AppCompatActivity implements CharaAdapter.OnPokemonListener {
    RecyclerView recyclerView;
    CharaAdapter charaAdapter;
    ImageView imageViewAdded;

    DataSource dataSource;

    final String KEY_DATA = "data";
    final String LOGCAT = "RV";
    final String PREF_FILE = "mainsharedpref";
    final int REQUEST_CODE_IMAGE = 1000;

    SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO 11.1 Get references to the widgets
        recyclerView = findViewById(R.id.charaRecyclerView);
        imageViewAdded = findViewById(R.id.imageViewAdded);

        //TODO 12.7 Load the Json string from shared Preferences
        //TODO 12.8 Initialize your dataSource object with the Json string
        mPreferences = getSharedPreferences(PREF_FILE, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPreferences.getString(KEY_DATA, "");
        dataSource = gson.fromJson(json, DataSource.class);

        if (dataSource == null) {
            //TODO 11.2 Create your dataSource object by calling Utils.firstLoadImages
            ArrayList<Integer> imagesId = new ArrayList<>(Arrays.asList(
                    R.drawable.bulbasaur,
                    R.drawable.eevee,
                    R.drawable.gyrados,
                    R.drawable.pikachu,
                    R.drawable.psyduck,
                    R.drawable.snorlax,
                    R.drawable.spearow,
                    R.drawable.squirtle));
            dataSource = Utils.firstLoadImages(MainActivity.this, imagesId);
        }


        //TODO 11.3 --> Go to CharaAdapter
        //TODO 11.8 Complete the necessary code to initialize your RecyclerView
        charaAdapter = new CharaAdapter(MainActivity.this, dataSource, this);

        // Alternative method to implement onClickListener for the adapter
//        charaAdapter = new CharaAdapter(MainActivity.this, dataSource, new CharaAdapter.OnPokemonListener() {
//            @Override
//            public void onPokemonClick(int position) {
//                imageViewAdded.setImageBitmap(dataSource.getImage(position));
//                Toast.makeText(getApplicationContext(), dataSource.getName(position), Toast.LENGTH_SHORT).show();
//            }
//        });

        recyclerView.setAdapter(charaAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        // recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        //TODO 12.9 [OPTIONAL] Add code to delete a RecyclerView item upon swiping. See notes for the code.
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                CharaAdapter.CharaViewHolder charaViewHolder = (CharaAdapter.CharaViewHolder) viewHolder;
                int position = charaViewHolder.getAdapterPosition();
                dataSource.removeData(position);
                charaAdapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        //TODO 12.1 Set up an Explicit Intent to DataEntry Activity with startActivityForResult (no coding)
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DataEntry.class);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });
    }

    //TODO 12.6 Complete onPause to store the DataSource object in SharedPreferences as a JSON string
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor prefsEditor = mPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dataSource);
        prefsEditor.putString(KEY_DATA, json);
        prefsEditor.apply();
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    //TODO 12.5 Write onActivityResult to get the data passed back from DataEntry and add to DataSource object
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if( requestCode == REQUEST_CODE_IMAGE && resultCode == Activity.RESULT_OK){
            String name = data.getStringExtra(DataEntry.KEY_NAME);
            String path = data.getStringExtra(DataEntry.KEY_PATH);

            dataSource.addData(name, path);

            Bitmap bitmap = Utils.loadImageFromStorage(path, name);

            imageViewAdded.setImageBitmap(bitmap);

            Toast.makeText(getApplicationContext(), "Pokemon Caught!", Toast.LENGTH_SHORT).show();

            charaAdapter.notifyDataSetChanged();
        }
    }

    // This is an interface to make the pokemon click transfer into CharaAdapter, used for option 1
    @Override
    public void onPokemonClick(int position) {
        imageViewAdded.setImageBitmap(dataSource.getImage(position));
        Toast.makeText(getApplicationContext(), dataSource.getName(position), Toast.LENGTH_SHORT).show();
    }
}
