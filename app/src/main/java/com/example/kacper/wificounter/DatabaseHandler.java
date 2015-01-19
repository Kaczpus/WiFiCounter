package com.example.kacper.wificounter;

/**
 * Created by Kacper on 2015-01-18.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME  = "wificounter.db";
    public static final String COLUMN_ID = "_id";
    public static final String TABLE_WIFI = "WiFi";
    public static final String WIFI_NAME = "WiFi_name";
    public static final String TABLE_PROFILE = "Profile";
    public static final String PROFIL_NAME = "Profil_name";
    public static final String TABLE_HISTORY = "History";
    public static final String DATE = "Date";
    public static final String TIME_CONNECTION = "Time_connection";





    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String wifi =  "CREATE TABLE " + TABLE_WIFI + "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" + WIFI_NAME + "TEXT "
        + ");";
         String profil =  "CREATE TABLE " + TABLE_PROFILE + "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" + PROFIL_NAME + "TEXT"
        + "wifi_id" + "INTEGER" + "FOREIGN KEY REFERENCES WiFi(wifi_id)"  + ");";
        String history = "CREATE TABLE " + TABLE_HISTORY + "(" + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" + DATE + "TEXT"
         + TIME_CONNECTION + "INTEGER"
             + "_id_profile" + "INTEGER" + "FOREIGN KEY REFERENCES" + PROFIL_NAME + "(_id_profile)"  + ");";

        db.execSQL(wifi);
        db.execSQL(profil);
        db.execSQL(history);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WIFI);
        onCreate(db);
    }


}

