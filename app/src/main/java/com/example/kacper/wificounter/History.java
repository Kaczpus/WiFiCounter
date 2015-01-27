package com.example.kacper.wificounter;

/**
 * Created by Kacper on 2015-01-18.
 */
public class History {

    private int _id;
    private  String date;
    private String time_connection;
    private int _idProfile;



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTime_connection() {
        return time_connection;
    }

    public void setTime_connection(String time_connection) {
        this.time_connection = time_connection;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void set_idProfile(int idProfile) {this._idProfile=idProfile;}
    public int get_idProfile(){return _idProfile;}


    History() {}

    public History(String _date, String _time_connection)
    {
        this.date=_date;
        this.time_connection=_time_connection;
    }
}
