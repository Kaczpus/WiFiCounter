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
    String WifiSSID;
    String WifiBSSID;
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
            WifiSSID = wifiInfo.getSSID();

            return WifiSSID;
        }

        else
        {
            return "Please enable WiFi first";
        }
    }

    public String getWiFiBSSID()
    {
        if(wifiManager.isWifiEnabled())
        {
            wifiInfo = wifiManager.getConnectionInfo();
            WifiBSSID = wifiInfo.getBSSID();

            return WifiBSSID;
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






}