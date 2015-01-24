package com.example.kacper.wificounter;

/**
 * Created by Kacper on 2015-01-18.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.lang.Object;


public  class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME  = "wificounter.db";
    public static final String COLUMN_ID = "_id";
    public static final String TABLE_PROFILE = "Profile";
    public static final String PROFILE_NAME = "Profile_name";
    public static final String TABLE_HISTORY = "History";
    public static final String DATE = "Date";
    public static final String TIME_CONNECTION = "Time_connection";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String profile =  "CREATE TABLE " + TABLE_PROFILE + "(id INTEGER PRIMARY KEY, name TEXT, ssid TEXT, gssid TEXT);";
        String history = "CREATE TABLE " + TABLE_HISTORY + "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" + DATE + "TEXT"
                + TIME_CONNECTION + "INTEGER"
                + "_id_profile" + "INTEGER" + "FOREIGN KEY REFERENCES" + PROFILE_NAME + "(_id_profile)"  + ");";


        db.execSQL(profile);
        db.execSQL(history);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        onCreate(db);
    }




}

