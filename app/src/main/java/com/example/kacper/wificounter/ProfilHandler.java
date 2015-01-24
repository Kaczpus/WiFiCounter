package com.example.kacper.wificounter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kacper on 2015-01-18.
 */
public class ProfilHandler  extends DatabaseHandler {

    public ProfilHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void addItem(Object object) {

        ContentValues values = new ContentValues();
        values.put(PROFILE_NAME, ((Profil) object).get_profilname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PROFILE, null, values);
        db.close();
    }


    public void deleteItem(Object historyDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PROFILE + "WHERE " + PROFILE_NAME + "=\"" + (String) historyDate + "\";");
    }


    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PROFILE + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
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

    public ProfilHandler(Context context) {
        super(context);

    }
}
