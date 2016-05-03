package com.cambiar.ludusz.model;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by vibes on 1/5/16.
 */
public class UserData implements Serializable {
    private static final long serialVersionUID = 1L;

    public String title;
    public String startTime;
    public String endTime;
    public String day;

    public boolean classEnabled;

    public UserData() {
    }

    public UserData(String title, String startTime, boolean enable) {
        this.title = title;
        this.startTime = startTime;
        this.classEnabled = enable;
    }

    public boolean saveObject(Context context, UserData obj) {
        final File suspend_f = new File(context.getCacheDir(), "test");

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean keep = true;

        try {
            fos = new FileOutputStream(suspend_f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
        } catch (Exception e) {
            keep = false;
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
                if (keep == false) suspend_f.delete();
            } catch (Exception e) { /* do nothing */ }
        }

        return keep;
    }

    public UserData getObject(Context c) {
        final File suspend_f = new File(c.getCacheDir(), "test");

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
}

