package com.example.kacper.wificounter;

/**
 * Created by Kacper on 2015-01-18.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.Object;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HistoryHandler  extends DatabaseHandler{

    public HistoryHandler(Context context)
    {
        super(context);
    }
    @Override
    public void addItem(Object object){

        ContentValues values = new ContentValues();
        //SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM");
        values.put(DATE,((History) object).getDate());
        values.put(TIME_CONNECTION,((History)object).getTime_connection());
        values.put("_id_profile",((History)object).get_idProfile());
        SQLiteDatabase db =getWritableDatabase();
        db.insert(TABLE_HISTORY,null,values);
        db.close();
    }
    @Override
    public void deleteItem(Object historyDate){
        SQLiteDatabase db =getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_HISTORY + "WHERE " + DATE + "=\"" + (String)historyDate + "\";");
    }
    @Override
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_HISTORY + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("Date")) != null) {
                dbString += c.getString(c.getColumnIndex("Date"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public ArrayList<History> historyToArray(int profile)
    {

        ArrayList<History> array_list = new ArrayList<History>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM History WHERE _id_profile = " + Integer.toString(profile) + " ;",null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(new History(res.getString(res.getColumnIndex("Date")),res.getString(res.getColumnIndex("Time_connection"))));

            res.moveToNext();
        }
        return array_list;
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        String profile =  "CREATE TABLE " + TABLE_PROFILE + "(_id INTEGER PRIMARY KEY, Profile_name TEXT, ssid TEXT);";
       /* String history = "CREATE TABLE " + TABLE_HISTORY + "(" + COLUMN_ID + "INTEGER PRIMARY KEY " + DATE + "TEXT , "
                + TIME_CONNECTION + " , " + " INTEGER, "
                + " _id_profile " + " INTEGER, " + " FOREIGN KEY REFERENCES " + PROFILE_NAME + " (_id_profile) "  + " ); ";
*/

        db.execSQL(profile);
        db.execSQL("create table History ( _id integer primary key, Date TEXT , Time_connection integer , _id_profile integer , foreign key(_id_profile) references  Profile  ( _id ))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        onCreate(db);
    }

}
