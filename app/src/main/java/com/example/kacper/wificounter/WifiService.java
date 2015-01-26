package com.example.kacper.wificounter;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Hunt on 2015-01-26.
 */
public class WifiService extends Service
{
    private static final String TAG = "MyActivity";


    WiFi wifiMan;
    Timer timer;
    Intent intent;
    public static final String BROADCAST_ACTION = "com.example.kacper.wificounter.MainActivity";
    private Handler handler = new Handler();
    @Override
    public void onCreate() {
        super.onCreate();
        //Toast.makeText(getApplicationContext(), "msg msg", Toast.LENGTH_SHORT).show();
        wifiMan = new WiFi(this);
        timer = new Timer();
        intent = new Intent(BROADCAST_ACTION);
        handler.removeCallbacks(sendUpdatesToUI);
        handler.postDelayed(sendUpdatesToUI, 10000); // 1 second



    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            WifiChecker();
            handler.postDelayed(this, 5000); // 1 seconds
        }
    };

    private void WifiChecker() {

            if(!wifiMan.isWiFiConnected()){
            intent.putExtra("iswifioff", true);
                Log.d(TAG, "Sending broadcast");
            sendBroadcast(intent);}
        }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}
