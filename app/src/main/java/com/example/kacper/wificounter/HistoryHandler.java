package com.example.kacper.wificounter;

/**
 * Created by Kacper on 2015-01-18.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.Object;

public class HistoryHandler  extends DatabaseHandler{


    public void addItem(Object object){

        ContentValues values = new ContentValues();
       values.put(DATE,((History) object).getDate());
        SQLiteDatabase db =getWritableDatabase();
        db.insert(TABLE_HISTORY,null,values);
        db.close();
    }

    public void deleteItem(Object historyDate){
        SQLiteDatabase db =getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_HISTORY + "WHERE " + DATE + "=\"" + (String)historyDate + "\";");
    }

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

   public HistoryHandler(Context context){
       super(context);

   }

}
