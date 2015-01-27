package com.example.kacper.wificounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Kacper on 2015-01-18.
 */
public class ProfileHandler extends DatabaseHandler {

    @Override
    public void onCreate(SQLiteDatabase db) {
        String profile =  "CREATE TABLE " + TABLE_PROFILE + "(id INTEGER PRIMARY KEY, Profile_name TEXT, ssid TEXT, bssid TEXT);";
       /* String history = "CREATE TABLE " + TABLE_HISTORY + "(" + COLUMN_ID + "INTEGER PRIMARY KEY " + DATE + "TEXT , "
                + TIME_CONNECTION + " , " + " INTEGER, "
                + " _id_profile " + " INTEGER, " + " FOREIGN KEY REFERENCES " + PROFILE_NAME + " (_id_profile) "  + " ); ";
*/

        db.execSQL(profile);
        db.execSQL("create table History ( _id integer primary key, Date TEXT , Time_connection integer , _id_profile integer , foreign key(_id_profile) references Profile (_id))");
    }

    public ProfileHandler(Context context)
    {
        super(context);
    }
    @Override
    public void addItem(Object object) {

        ContentValues values = new ContentValues();
        values.put(PROFILE_NAME, ((Profile) object).get_profilname());
        values.put(SSID,((Profile)object).getSsid());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }

    @Override

    public void deleteItem(Object historyDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PROFILE + "WHERE " + PROFILE_NAME + "=\"" + (String) historyDate + "\";");
    }

    @Override

    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        //String query = "SELECT * FROM " + TABLE_PROFILE + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery("SELECT * FROM"  + TABLE_PROFILE +  "WHERE 1",null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("Profile_name")) != null) {
                dbString += c.getString(c.getColumnIndex("Profile_name"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public int getProfileId(String profile)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM" + TABLE_PROFILE + "WHERE " + PROFILE_NAME + "=\"" + profile + "\";",null);
        res.moveToFirst();
        int wantedId = res.getInt(res.getColumnIndex("id"));
        return wantedId;
    }

    public String getProfileSSID(String profile)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM" + TABLE_PROFILE + "WHERE " + PROFILE_NAME + "=\"" + profile + "\";",null);
        res.moveToFirst();
        String wantedSSID = res.getString(res.getColumnIndex("ssid"));

        return wantedSSID;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        onCreate(db);
    }


}
