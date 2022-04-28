package com.example.comicapp;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    EditText editTextComicNo;
    Button buttonGetComic;
    TextView textViewTitle;
    ImageView imageViewComic;

    String comicNo;
    public static final String TAG = "Logcat";
    final String ERROR_NO_NETWORK = "No Network";
    final String ERROR_NOT_VALID = "Comic No Not Valid";
    final String ERROR_MALFORMED_URL = "Malformed URL";
    final String ERROR_BAD_JSON = "Bad JSON Response";
    final String ERROR_HTTPS_ERROR = "HTTPS Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO 6.0 Study the Utils class and see what methods are available for you
        //TODO 6.1 Ensure that Android Manifest has permissions for internet and has orientation fixed
        //TODO 6.2 Get references to widgets
        editTextComicNo = findViewById(R.id.editTextComicNo);
        buttonGetComic = findViewById(R.id.buttonGetComic);
        textViewTitle = findViewById(R.id.textViewTitle);
        imageViewComic = findViewById(R.id.imageViewComic);

        //TODO 6.3 Set up setOnClickListener for the button
        //TODO 6.4 Retrieve the user input from the EditText
        //TODO 6.5 - 6.9 Modify getComic below
        //TODO 6.10 If network is active, call the getComic method with the userInput
        buttonGetComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comicNo = editTextComicNo.getText().toString();
                if (Utils.isNetworkAvailable(MainActivity.this)) {
                    getComic(comicNo);

                    // Code below is to run the BackgroundTask abstract class 6.11 (Challenge)
                    // SetBitmap setBitmap = new SetBitmap();
                    // setBitmap.start(comicNo);
                } else {
                    Log.d(TAG, ERROR_NO_NETWORK);
                }
            }
        });
    }


    //TODO 6.5 - 6.9 ****************
    //TODO you are reminded that new Runnable{} is an anonymous inner class
    //TODO 6.5 Make sure getComic has the signature getComic(final String userInput); make sure an executor and a handler are instantiated
    //TODO 6.6 (background work) create a final Container<Bitmap> cBitmap object which will be used for commmunication between the main thread and the child thread
    //TODO 6.7 (background work) Call Utils.getImageURLFromXkcdApi to get the image URL from comicNo
    //TODO 6.8 (background work) Call Utils.getBitmap using the URL to get the bitmap
    //TODO 6.9 (UI thread work) Assign the Bitmap downloaded to imageView. The bitmap may be null.

    //TODO 7.1 (background work) create a final Container<String> cString object which will be used for commmunication between the main thread and the child thread
    //TODO 7.2 (background work) Call Utils.getComicTitleFromXkcdApi to get the comic title from comicNo
    //TODO 7.3 (UI thread work) Assign the comic title to textViewTitle. The title may be null.

    void getComic(final String userInput) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                final Container<Bitmap> cBitmap = new Container<>();
                final Container<String> cString = new Container<>();
                String jsonURL;
                String jsonTitle;
                URL url;
                Bitmap bitmap;
                try {
                    jsonURL = Utils.getImageURLFromXkcdApi(userInput);
                    jsonTitle = Utils.getComicTitleFromXkcdApi(userInput);
                    url = new URL(jsonURL);
                    bitmap = Utils.getBitmap(url);
                } catch (MalformedURLException e) {
                    // Thrown in URL constructor
                    Log.d(TAG, ERROR_MALFORMED_URL);
                    return;
                } catch (IOException e) {
                    // Error thrown in getInputStream, where urlConnection is not made
                    Log.d(TAG, ERROR_HTTPS_ERROR);
                    return;
                } catch (JSONException e) {
                    // This is for calling new JSONObject
                    Log.d(TAG, ERROR_BAD_JSON);
                    return;
                }
                cBitmap.set(bitmap);
                cString.set(jsonTitle);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (cBitmap.get() != null) {
                            imageViewComic.setImageBitmap(cBitmap.get());
                            textViewTitle.setText(cString.get());
                        } else {
                            // If comicNo is invalid, bitmap = null
                            Log.d(TAG, ERROR_NOT_VALID);
                        }
                    }
                });
            }
        });
    }

    class Container<T> {
        T value;
        Container() {
            this.value = null;
        }
        void set(T x) { this.value = x; }
        T get() { return this.value; }
    }



    //TODO 6.11 (Challenge) Re-do steps 6.5 - 6.10 by making use of the BackgroundTask abstract class
    // (Hints: you may consider applying the template method design pattern)
    //TODO 6.12 (Self-added) Add in title also
    abstract class BackgroundTask<I,O> {
        abstract public O task(I input);
        // Add another class to handle the Title
        abstract public void done(O result);

        public void start(final I userInput) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            final Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //Background work here
                    // Possible to add another Container here and run the other Task for title
                    final Container<O> c = new Container<O>();
                    O o = task(userInput);
                    c.set(o);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //UI Thread work here
                            if (c.get() != null) {
                                // Might need 2 test, or can do it simply
                                done(c.get());
                            }
                        }
                    });
                }
            });
        }
    }

    class SetBitmap extends BackgroundTask {
        @Override
        public Object task(Object input) {
            String jsonURL;
            URL url;
            Bitmap bitmap;
            try {
                // Change this to getComicTitleFromXkcdApi for comic title
                jsonURL = Utils.getImageURLFromXkcdApi((String) input);
                url = new URL(jsonURL);
                bitmap = Utils.getBitmap(url);
                return bitmap;
            } catch (MalformedURLException e) {
                // Thrown in URL constructor
                Log.d(TAG, ERROR_MALFORMED_URL);
                return null;
            } catch (IOException e) {
                // Error thrown in getInputStream, where urlConnection is not made
                Log.d(TAG, ERROR_HTTPS_ERROR);
                return null;
            } catch (JSONException e) {
                // This is for calling new JSONObject
                Log.d(TAG, ERROR_BAD_JSON);
                return null;
            }
        }

        @Override
        public void done(Object result) {
            imageViewComic.setImageBitmap((Bitmap) result);
        }
    }
}
