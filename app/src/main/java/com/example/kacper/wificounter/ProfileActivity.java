package com.example.kacper.wificounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileActivity extends ActionBarActivity {

    WiFi wifi;
    TextView profileName;
    TextView wifiName;
    TextView conInfo;
    TextView startTimerText;
    TextView timeElapsedText;
    Profile profile;
    String defSSID;
    Intent serviceIntent;
    Timer timer;

    SharedPreferences prefs;
    String prefName = "MyPref";
    String STARTTIME = "starttime";
    private static final String TAG = "MyActivity";

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "In onsave");
        savedInstanceState.putLong("starttime", timer.getMilisCountingStart());
        super.onSaveInstanceState(savedInstanceState);
    }

   /* @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        Log.d(TAG, "In RESTORE MODE MOTHERFUCKER");


    }*/


    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG,"ON RESUME!!!!!!!");
        prefs = getSharedPreferences(prefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("milisStart",timer.getMilisCountingStart());
        editor.commit();

    }


    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG,"ON RESUME!!!!!!!");

        prefs = getSharedPreferences(prefName,MODE_PRIVATE);
        long milis = prefs.getLong("milisStart",5);
        timer.setCountingStart(milis);
        startTimerText = (TextView) findViewById(R.id.timerStartText);
        timeElapsedText = (TextView) findViewById(R.id.TimeElapsed);
        startTimerText.setText(timer.countingStartToString());
        timeElapsedText.setText(timer.ElapsedTimeToString());
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(TAG,"ON STOP!!!!!!!");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"ON DESTROY!!!!!!!");
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "ON CREATE!!!!!!!!!!!!");
        wifi = new WiFi(this);
        timer = new Timer();

        /*if (savedInstanceState != null)
        {
            timer.setCountingStart(savedInstanceState.getLong("starttime"));
        }*/




        wifiName = (TextView)findViewById(R.id.wifiNameText);
        conInfo = (TextView)findViewById(R.id.connInfoText);
        startTimerText = (TextView) findViewById(R.id.timerStartText);
        timeElapsedText = (TextView) findViewById(R.id.TimeElapsed);

        wifiName.setText(wifi.getWiFiSSID());

        defSSID = wifi.getWiFiSSID();

        if(defSSID.equals(wifi.getWiFiSSID()) && wifi.isWiFiConnected()) {
            conInfo.setText("Connected!");
            if(timer.countingStart==null)
            {
                timer.setStartTime();
                startTimerText.setText(timer.countingStartToString());
            }
            else startTimerText.setText(timer.countingStartToString());


        }
        else conInfo.setText("Can't connect to this Wi-Fi");




    }

    public void refresh(View view)
    {
        wifiName = (TextView)findViewById(R.id.wifiNameText);
        conInfo = (TextView)findViewById(R.id.connInfoText);
        startTimerText = (TextView) findViewById(R.id.timerStartText);
        timeElapsedText = (TextView) findViewById(R.id.TimeElapsed);
        wifiName.setText(wifi.getWiFiSSID());
        defSSID = wifi.getWiFiSSID();
        if(defSSID.equals(wifi.getWiFiSSID())) {
            conInfo.setText("Connected!");
            if(timer.countingStart==null)
            {
                timer.setStartTime();
                startTimerText.setText(timer.countingStartToString());

            }
            else
            {

                timeElapsedText.setText(timer.ElapsedTimeToString());
            }



        }
        else conInfo.setText("Can't connect to this Wi-Fi");

    }


    @Override
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
    }


}
