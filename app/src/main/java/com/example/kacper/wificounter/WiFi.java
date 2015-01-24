package com.example.kacper.wificounter;

/**
 * Created by Kacper on 2015-01-18.
 */
public class WiFi {
    private int _id;
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
    }
}