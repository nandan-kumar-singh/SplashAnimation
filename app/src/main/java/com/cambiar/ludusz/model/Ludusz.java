package com.cambiar.ludusz.model;

import android.app.Application;

/**
 * Created by vibes on 20/4/16.
 */
public class Ludusz extends Application{
    private static User _user;
    private static UserData userData;
    public static User getUser() {
        return _user;
    }

    public static void setUser(User user) {
        _user = user;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public enum User {
        PLAYER, COACH, CO_ORDINATOR, EVENT_ORGANISER
    }
}
