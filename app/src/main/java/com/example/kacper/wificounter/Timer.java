package com.example.kacper.wificounter;

import java.util.Calendar;

/**
 * Created by Kacper on 2015-01-18.
 */
public class Timer {


    //w activity getujemy profil wyslany w intent

    Calendar countingStart;

    public Timer()
    {
        //countingStart = Calendar.getInstance();
    }

    public void setStartTime()
    {
        countingStart = Calendar.getInstance();
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

        return Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year) + " " + Integer.toString(hours) + ":" + strMinutes;


    }

    public String getElapsedTime()
    {
        Calendar newTime = Calendar.getInstance();

        long milisDiff = newTime.getTimeInMillis() - countingStart.getTimeInMillis();

        long hours = milisDiff / (1000 * 60 * 60);
        long minutes = milisDiff / (1000 * 60);
        long seconds = milisDiff / 1000;

        seconds %= 60;
        minutes %= 60;

        return Long.toString(hours) + "h "  + Long.toString(minutes) + "min " + Long.toString(seconds) + "s elapsed";
    }


}
