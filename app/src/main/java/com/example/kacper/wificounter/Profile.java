package com.example.kacper.wificounter;

/**
 * Created by Kacper on 2015-01-18.
 */
public class Profile {

    private int _id;
    private String _profilname;
    private String ssid;
    private String bssid;

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_profilname() {
        return _profilname;
    }

    public void set_profilname(String _profilname) {
        this._profilname = _profilname;
    }

    public Profile(String profilname) {
        this._profilname = profilname;
    }

    public Profile() {}


}
