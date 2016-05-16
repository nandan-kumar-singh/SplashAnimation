package com.cambiar.ludusz.model;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by vibes on 1/5/16.
 */
public class UserData extends Data implements Serializable {
    public static final String CLASS_NAME = UserData.class.getSimpleName();
    private static final long serialVersionUID = 1L;
    private String userMail;
    private String userKey;
    private int userType;
    private String userName;
    private String userAddress;
    private String userPic;
    private Double userLocationLatitude;
    private Double userLocationLongitude;

    public UserData() {
    }

    public Double getUserLocationLongitude() {
        return userLocationLongitude;
    }

    public void setUserLocationLongitude(Double userLocationLongitude) {
        this.userLocationLongitude = userLocationLongitude;
    }

    public Double getUserLocationLatitude() {
        return userLocationLatitude;
    }

    public void setUserLocationLatitude(Double userLocationLatitude) {
        this.userLocationLatitude = userLocationLatitude;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public static UserData getObject(Context c) {
        final File suspend_f = new File(c.getCacheDir(), UserData.class.getSimpleName());

        UserData simpleClass = null;
        FileInputStream fis = null;
        ObjectInputStream is = null;

        try {
            fis = new FileInputStream(suspend_f);
            is = new ObjectInputStream(fis);
            simpleClass = (UserData) is.readObject();
        } catch (Exception e) {
            String val = e.getMessage();
        } finally {
            try {
                if (fis != null) fis.close();
                if (is != null) is.close();
            } catch (Exception e) {
            }
        }

        return simpleClass;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }
}

