package com.cambiar.ludusz.model;

import android.app.Application;

/**
 * Created by vibes on 20/4/16.
 */
public class Ludusz extends Application{
    private static User _user;

    public enum User {
        PLAYER, COACH, CO_ORDINATOR, EVENT_ORGANISER
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static User getUser() {
        return _user;
    }

    public static void setUser(User user) {
        _user = user;
    }
}
