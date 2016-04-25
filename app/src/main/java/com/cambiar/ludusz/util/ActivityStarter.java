package com.cambiar.ludusz.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cambiar.ludusz.activities.MainActivity;

/**
 * Created by vibes on 23/4/16.
 */
public class ActivityStarter {
    private static void startMainActivity(Context mContext, Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        mContext.startActivity(intent);
    }
}
