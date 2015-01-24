package com.example.kacper.wificounter;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Kacper on 2015-01-18.
 */
public class WiFi {

    WifiManager wifiManager;
    WifiInfo wifiInfo;
    String WifiName;
    public WiFi(Context context)
    {
        wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);

    }

    public void connectToWiFi()
    {
        wifiManager.setWifiEnabled(true);
    }

    public void disconnectWiFi()
    {
        wifiManager.setWifiEnabled(false);
    }

    public String getWiFiSSID()
    {
        if(wifiManager.isWifiEnabled())
        {
            wifiInfo = wifiManager.getConnectionInfo();
            WifiName = wifiInfo.getSSID();

            return WifiName;
        }

        else
        {
            return "Please enable WiFi first";
        }
    }

    public boolean isWiFiConnected()
    {
        if(wifiManager.isWifiEnabled()) return true;
        else return false;
    }
    /*private int _id;
    private String _wifiname;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_wifiname() {
        return _wifiname;
    }

    public void set_wifiname(String _wifiname) {
        this._wifiname = _wifiname;
    }

    public WiFi(String _wifiname) {
        this._wifiname = _wifiname;
    }

    public WiFi() {
    }()*/






}