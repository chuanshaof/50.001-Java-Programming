package com.example.qiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    ListView queueListView;
    Model.PQueue datasource;

    static class REQUEST_CODES {
        public final static int REG = 100;
        public final static int SERVE = 200;
    }

    static class INTENT_KEYS {
        public static String MOBILE = "mobile";
        public static String TIME = "time";
        public static String BOOKINGID = "bookingid";
    }

    static class SHARED_PREF {
        public final static String sharedPreference = "com.example.qiosk.mainSharedPrefs";
        public final static String KEY = "datasource";
    }

    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queueListView = findViewById(R.id.queueListView);

        Log.i("MainActivity", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (datasource == null) { datasource = restoreDataSource(new Model.PQueue()); }
        renderQueue();
        Log.i("MainActivity", "onStart");
    }

    // restore the datasource object from the shared preferences if it exists.
    private Model.PQueue restoreDataSource(Model.PQueue deft) {
        Model.PQueue datasource;
        if (this.mPreferences == null) {
            this.mPreferences = getSharedPreferences(SHARED_PREF.sharedPreference, MODE_PRIVATE);
        }
        String dsJsonStr = mPreferences.getString(SHARED_PREF.KEY, null);
        if (dsJsonStr != null && dsJsonStr != "") {
            try {
                JSONObject json  = new JSONObject(dsJsonStr);
                datasource = new Model.PQueue(json);
            } catch (JSONException e) {
                datasource = deft;
            }
        } else {
            datasource = deft;
        }
        Log.i("MainActivity", "restoreDataSource");
        return datasource;
    }

    // save the datasource in the shared preference file.
    private void persistDataSource(Model.PQueue ds) {
        if (this.mPreferences == null) {
            this.mPreferences = getSharedPreferences(SHARED_PREF.sharedPreference, MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = this.mPreferences.edit();
        try {
            String dsJsonStr = ds.toJSON().toString();
            editor.putString(SHARED_PREF.KEY, dsJsonStr);
            editor.apply();
        } catch (JSONException e) {
            Log.i("MainActivity", "persistDataSource failed.");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
        this.persistDataSource(this.datasource);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.register) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivityForResult(intent, REQUEST_CODES.REG);
        } else {
            Intent intent = new Intent(this, ServeActivity.class);
            Model.Appointment a = datasource.peek();
            if (a == null) {
                Toast.makeText(this, "The queue is empty.", Toast.LENGTH_LONG).show();
            } else {
                intent.putExtra(INTENT_KEYS.MOBILE, a.getMobile());
                intent.putExtra(INTENT_KEYS.TIME, a.getTime());
                String bid = a.getBookingId();
                if (bid != null) {
                    intent.putExtra(INTENT_KEYS.BOOKINGID, bid);
                }
                startActivityForResult(intent, REQUEST_CODES.SERVE);
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            Log.i("MainActivity", "onActivityResult:" + datasource.toJSON().toString());
        } catch (JSONException e) {

        }
        datasource = restoreDataSource(new Model.PQueue());
        if (requestCode == REQUEST_CODES.REG) {
            String bookingID = data.getStringExtra(INTENT_KEYS.BOOKINGID);
            int mobile = data.getIntExtra(INTENT_KEYS.MOBILE, 0);
            if (mobile > 0) {
                Model.Appointment a;
                if (bookingID == null || bookingID.trim().isEmpty()) {
                    a = new Model.WalkIn(mobile);
                } else {
                    a = new Model.Scheduled(mobile, bookingID);
                }
                datasource.add(a);
            } else {
                Toast.makeText(this, "Registration failed: invalid mobile number", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == REQUEST_CODES.SERVE) {
            int mobile = data.getIntExtra(INTENT_KEYS.MOBILE, 0);
            // TODO: Question 1B.c
            if (resultCode == Activity.RESULT_OK) {
                datasource.dequeue();
            } else {
                Model.Appointment appointment = datasource.dequeue();
                Model.Appointment a = new Model.Scheduled(appointment.getMobile(), appointment.getBookingId());
                datasource.add(a);
            }
        }
        Log.i("MainActivity", "onActivityResult");
        renderQueue();
    }

    public void renderQueue() {
        queueListView = findViewById(R.id.queueListView);
        DataSourceAdapter dsAdapter = new DataSourceAdapter(MainActivity.this, datasource);
        queueListView.setAdapter(dsAdapter);
    }

    static class DataSourceAdapter extends BaseAdapter {
        Context context;
        Model.PQueue dataSource;
        LayoutInflater inflter;
        public DataSourceAdapter(Context applicationContext, Model.PQueue dataSource) {
            this.context = applicationContext;
            this.dataSource = dataSource;
            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        // TODO: Question 1B.a
        public int getCount() {
            return dataSource.size();
        }

        @Override
        public Object getItem(int i) {
            return dataSource.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        // TODO: Question 1B.a
        // in the getView method, you are supposed to
        // 1) inflate the (item) view which displays every item in the list (already done)
        // 2) retrieve the references of the TextViews contained in the item view
        // 3) set the text of the textviews by retrieving the relevant fields from the
        //    datasource
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.activity_queuelistview, null);

            TextView mobileNumberTextView = view.findViewById(R.id.mobilenumberTextView);
            TextView timeTextView = view.findViewById(R.id.timeTextView);

            String mobile = String.valueOf(dataSource.get(i).getMobile());
            String time = String.valueOf(dataSource.get(i).getTime());
            mobileNumberTextView.setText(mobile);
            timeTextView.setText(time);
            return view;
        }
    }
}