package com.cambiar.ludusz.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by vibes on 8/3/16.
 */
public class AndroidUtil {
    @DrawableRes
    static int colorRed;

    public static void enableGPS(Context context) {
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        context.sendBroadcast(intent);
    }

    public static void turnGPSOn(Context context) {
        Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
        intent.putExtra("enabled", true);
        context.sendBroadcast(intent);

        String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if (!provider.contains("gps")) { //if gps is disabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            context.sendBroadcast(poke);


        }
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static boolean saveObject(Context context, Object objectToSave, String fileName) {
        final File suspend_f = new File(context.getCacheDir(), fileName);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean keep = true;

        try {
            fos = new FileOutputStream(suspend_f);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(objectToSave);
        } catch (Exception e) {
            keep = false;
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
                if (keep == false) suspend_f.delete();
            } catch (Exception e) {

                /* do nothing */
            }
        }

        return keep;
    }

    @Nullable
    public static Object getObject(Context c, String fileName) {
        if (!(new File(fileName).exists())) {
            return null;
        }
        final File suspend_f = new File(c.getCacheDir(), fileName);

        Object simpleClass = null;
        FileInputStream fis = null;
        ObjectInputStream is = null;

        try {
            fis = new FileInputStream(suspend_f);
            is = new ObjectInputStream(fis);
            simpleClass = is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) fis.close();
                if (is != null) is.close();
            } catch (Exception e) {
            }
        }

        return simpleClass;
    }

    public static boolean validateEmail(String text) {
        boolean isValid = false;
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            isValid = false;
        } else {
            isValid = true;
        }
        return isValid;
    }

    public static boolean validatePassword(String text) {
        boolean isValid = false;
        if (text.length() < 6) {
            isValid = false;
        } else {
            isValid = true;
        }
        return isValid;
    }
}