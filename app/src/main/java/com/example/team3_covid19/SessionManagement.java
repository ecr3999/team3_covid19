package com.example.team3_covid19;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.Date;

public class SessionManagement {
    public static final String SESSION_PREFERENCE = "com.example.team3_covid19.SessionManagement.SESSION_PREFERENCE";
    public static final String SESSION_TOKEN = "com.example.team3_covid19.SessionManagement.SESSION_TOKEN";
    public static final String SESSION_EXPIRE_TIME = "com.example.team3_covid19.SessionManagement.SESSION_EXPIRE_TIME";

    private static SessionManagement instance;
    public static SessionManagement getInstance(){
       if(instance==null){
              instance = new SessionManagement();
       }
       return instance;
    }

    public void startUserSession(Context context, int expired){
        Calendar calendar = Calendar.getInstance();
        Date userLoggedTime = calendar.getTime();
        calendar.setTime(userLoggedTime);
        calendar.add(Calendar.SECOND, expired);
        Date expiryTime = calendar.getTime();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(SESSION_EXPIRE_TIME, expiryTime.getTime());
        editor.apply();
    }

    public boolean isSessionActive(Context context, Date currentTime){
        Date sessionExpireAt = new Date(context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE).getLong(SESSION_EXPIRE_TIME, 0));
        return !currentTime.after(sessionExpireAt);
    }

    public void endUserSession(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SESSION_PREFERENCE, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear();
        sharedPreferences.edit().apply();
    }
}
