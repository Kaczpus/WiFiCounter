package com.example.kacper.wificounter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


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
    HistoryHandler historyHandler;
    ProfileHandler profileHandler;
    History history;
    SharedPreferences prefs;
    String prefName = "MyPref";
    String STARTTIME = "starttime";
    String profileNameIntent;
    boolean hasServiceStarted = false;
    boolean canSaveHistory;

    private static final String TAG = "MyActivity";

   /* @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, "In onsave");
        savedInstanceState.putLong("starttime", timer.getMilisCountingStart());
        super.onSaveInstanceState(savedInstanceState);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String intentProfileText = intent.getStringExtra("profile");
        defSSID = intent.getStringExtra("ssid");
        //Toast.makeText(getApplicationContext(), intent.getStringExtra("ssid"), Toast.LENGTH_SHORT).show();
        profileNameIntent = intent.getStringExtra("profile");

        setContentView(R.layout.activity_profile);
        Log.d(TAG, "ON CREATE!!!!!!!!!!!!");
        wifi = new WiFi(this);
        timer = new Timer();
        ///historyHandler = new HistoryHandler(this);


        profileName = (TextView)findViewById(R.id.profileTextView);
        wifiName = (TextView)findViewById(R.id.wifiNameText);
        conInfo = (TextView)findViewById(R.id.connInfoText);
        startTimerText = (TextView) findViewById(R.id.timerStartText);
        timeElapsedText = (TextView) findViewById(R.id.TimeElapsed);


        profileName.setText(intentProfileText);
        wifiName.setText(wifi.getWiFiSSID());


        //defSSID = "WYKOP OBRAZA PAPIEZA";

        if(defSSID.equals(wifi.getWiFiSSID()) && wifi.isWiFiConnected())
        {
            conInfo.setText("Connected!");
            canSaveHistory = true;
            if(timer.countingStart==null)
            {
                timer.setStartTime();
                startTimerText.setText(timer.countingStartToString());
                timeElapsedText.setText(timer.ElapsedTimeToString());
            }
            else {startTimerText.setText(timer.countingStartToString());
                timeElapsedText.setText(timer.ElapsedTimeToString());}

            if(!hasServiceStarted && wifi.isWiFiConnected())
            {
                startService(new Intent(getBaseContext(), WifiService.class));
            registerReceiver(broadcastReceiver, new IntentFilter(WifiService.BROADCAST_ACTION));
            hasServiceStarted = true;
            }


        }
        else
        {
            conInfo.setText("WiFI is offline");
            timeElapsedText.setText("Timer stopped");
            startTimerText.setText("Starting datetime");
        }

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override

        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "Receiving broadcast");
            stopCounting(intent);
        }
    };

    public void stopCounting(Intent intent)
    {
        conInfo = (TextView)findViewById(R.id.connInfoText);
        startTimerText = (TextView)findViewById(R.id.timerStartText);
        timeElapsedText = (TextView)findViewById(R.id.TimeElapsed);

        if(intent.getBooleanExtra("iswifioff",false))
        {

            //stopWifiService();
            //shit to make history happen
            if(canSaveHistory)
            {
                Toast.makeText(getApplicationContext(), "Saving to history...", Toast.LENGTH_SHORT).show();
                history = new History();
                historyHandler = new HistoryHandler(this);
                profileHandler = new ProfileHandler(this);
                history.setDate(timer.countingStartToString());
                history.setTime_connection(timer.ElapsedTimeToString());
                history.set_idProfile(profileHandler.getProfileId(profileNameIntent));
                historyHandler.addItem(history);

                //i chyba tyle?

            }

            conInfo.setText("WiFI is offline");

            timeElapsedText.setText("Timer stopped");
            startTimerText.setText("Starting datetime");


            canSaveHistory = false;
            hasServiceStarted = false;

            prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.commit();

            timer.clearCountingStart();




        }
    }

    //not used
   /* public void stopWifiService()
    {
        unregisterReceiver(broadcastReceiver);
        stopService(serviceIntent);
    }*/


    public void refresh(View view)
    {
        wifiName = (TextView)findViewById(R.id.wifiNameText);
        conInfo = (TextView)findViewById(R.id.connInfoText);
        startTimerText = (TextView) findViewById(R.id.timerStartText);
        timeElapsedText = (TextView) findViewById(R.id.TimeElapsed);
        wifiName.setText(wifi.getWiFiSSID());
        //defSSID = "WYKOP OBRAZA PAPIEZA";
        if(defSSID.equals(wifi.getWiFiSSID()) && wifi.isWiFiConnected())
        {
            conInfo.setText("Connected!");
            canSaveHistory = true;
            if(timer.countingStart==null)
            {
                timer.setStartTime();
                startTimerText.setText(timer.countingStartToString());
                timeElapsedText.setText(timer.ElapsedTimeToString());

        }
            else {startTimerText.setText(timer.countingStartToString());
                timeElapsedText.setText(timer.ElapsedTimeToString());}

            if(!hasServiceStarted && wifi.isWiFiConnected())
            {
                startService(new Intent(getBaseContext(), WifiService.class));
                registerReceiver(broadcastReceiver, new IntentFilter(WifiService.BROADCAST_ACTION));
                hasServiceStarted = true;
            }


        }
        else
        {
            conInfo.setText("WiFI is offline");
            timeElapsedText.setText("Timer stopped");
            startTimerText.setText("Starting datetime");
        }

    }

//zrobie pare niepotrzebnych linijek zeby bylo wiecej w rankingu




    public void HistButtonClicked(View view)
    {
        Intent newIntent;
        newIntent = new Intent(this,HistoryListActivity.class);
        newIntent.putExtra("profil",profileNameIntent);
        startActivity(newIntent);
    }



    ///o wlasnie


    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(TAG,"ON RESUME!!!!!!!");
        if(hasServiceStarted) {
            prefs = getSharedPreferences(prefName, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("milisStart", timer.getMilisCountingStart());
            editor.commit();
        }

    }


    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(TAG,"ON RESUME!!!!!!!");
        if(hasServiceStarted){
        prefs = getSharedPreferences(prefName,MODE_PRIVATE);
        long milis = prefs.getLong("milisStart",timer.getMilisCountingStart());
        timer.setCountingStart(milis);
        startTimerText = (TextView) findViewById(R.id.timerStartText);
        timeElapsedText = (TextView) findViewById(R.id.TimeElapsed);
        startTimerText.setText(timer.countingStartToString());
        timeElapsedText.setText(timer.ElapsedTimeToString());}
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
    /*public void HistButtonClicked(View view) {
        Intent myIntent = new Intent(view.getContext(), HistoryAcitivity.class);
        startActivityForResult(myIntent, 0);

    }*/

}
