package com.aecc.aecc.Controlador;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Clock {
    private Calendar calendar;
    public Clock() {
        calendar = Calendar.getInstance();
    }

    public String getNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy h:mm");
        String currentDate = formatter.format(calendar.getTime());
        return currentDate;
    }

    public Calendar getCalendar(){
        return calendar;
    }

    public long getNowMillis(){
        return calendar.getTimeInMillis();
    }

    public boolean equals(String date){
        if(date.compareTo(getNow()) == 0){
            return true;
        }
        return false;
    }

    public int diferenceInDays(long date1, long date2){
        long result = date2 - date1;
        return (int) result/(1000*60*60*24);
    }

    public int diferenceInMinutes(long date1, long date2){
        long result = date2 - date1;
        return (int) result/(1000*60);
    }
}