package com.cambiar.ludusz.model;

import android.app.Application;

import com.cambiar.ludusz.util.AndroidUtil;

/**
 * Created by vibes on 20/4/16.
 */
public class Ludusz extends Application {
    public static final String CLASS_NAME = Ludusz.class.getSimpleName();
    private static User _user;

    public UserData getUserData() {
        UserData userData = UserData.getObject(this);
        if (userData == null)
            return new UserData();
        return userData;
    }

    public void setUserData(UserData userData) {
        AndroidUtil.saveObject(this, userData, UserData.class.getSimpleName());
    }

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
