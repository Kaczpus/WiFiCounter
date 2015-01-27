package com.example.kacper.wificounter;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by Kacper on 2015-01-18...
 */
public class Timer {

    Calendar countingStart;
    boolean started;
    public Timer()
    {
        started = false;
    }

    public void setStartTime()
    {
        if(started==false)
        {
            countingStart = Calendar.getInstance();
            started = true;
        }
    }

    public void clearCountingStart()
    {
        countingStart = null;
        started = false;
    }

    public void setCountingStart(long milis)
    {
        countingStart = Calendar.getInstance();
        countingStart.setTimeInMillis(milis);
    }

    public long getMilisCountingStart()
    {
        return countingStart.getTimeInMillis();
    }

    public String countingStartToString()
    {
        int minutes = countingStart.get(Calendar.MINUTE);
        int hours = countingStart.get(Calendar.HOUR_OF_DAY);
        int day = countingStart.get(Calendar.DAY_OF_MONTH);
        int month = countingStart.get(Calendar.MONTH) + 1;
        int year = countingStart.get(Calendar.YEAR);

        String strMinutes = Integer.toString(minutes);
            if(strMinutes.length()<2) strMinutes = "0" + strMinutes;

        return Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " " + Integer.toString(hours) + ":" + strMinutes;


    }

    public long getElapsedTime()
    {
        Calendar newTime = Calendar.getInstance();

        long milisDiff = newTime.getTimeInMillis() - countingStart.getTimeInMillis();

        return milisDiff;
    }

    public String ElapsedTimeToString()
    {
        Calendar newTime = Calendar.getInstance();

        long milisDiff = newTime.getTimeInMillis() - countingStart.getTimeInMillis();

        long hours = milisDiff / (1000 * 60 * 60);
        long minutes = milisDiff / (1000 * 60);
        long seconds = milisDiff / 1000;

        seconds %= 60;
        minutes %= 60;

        String string_lol = Long.toString(hours) + "h "  + Long.toString(minutes) + "min " + Long.toString(seconds) + "s elapsed";

        if(string_lol.isEmpty()) return "Exception string";
        else return string_lol;
    }




}
