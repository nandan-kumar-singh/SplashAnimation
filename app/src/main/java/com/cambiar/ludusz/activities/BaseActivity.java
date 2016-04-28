package com.cambiar.ludusz.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cambiar.ludusz.R;

/**
 * Created by vibes on 11/4/16.
 */
public class BaseActivity extends AppCompatActivity {
    public static final class ActivityType {
        public static final int ACTIVITY_SPLASH = 0;
        public static final int ACTIVITY_MAIN = 1;
        public static final int ACTIVITY_GENERAL = 2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set transparent statusBar
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }*/
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.right_to_left_animation, R.anim.left_to_right_animation);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.right_to_left_animation, R.anim.left_to_right_animation);
    }
}
