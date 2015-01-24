package com.example.kacper.wificounter;

/**
 * Created by Kacper on 2015-01-18.
 */
public class History {

    private int _id;
    private  int date;
    private int time_connection;




    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getTime_connection() {
        return time_connection;
    }

    public void setTime_connection(int time_connection) {
        this.time_connection = time_connection;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
    History() {}
}
