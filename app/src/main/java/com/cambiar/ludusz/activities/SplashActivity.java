package com.cambiar.ludusz.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.model.Ludusz;
import com.cambiar.ludusz.util.AndroidUtil;

public class SplashActivity extends BaseActivity {
    public static final String TAG = SplashActivity.class.getSimpleName();
    private Ludusz ludusz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash);
        ludusz = (Ludusz) getApplicationContext();
        try {
            ((TextView) findViewById(R.id.tv_splash_version)).setText("Ver." + AndroidUtil.getAppVersion(this));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, AppDescriptionActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_exit);
                SplashActivity.this.finish();
            }
        }, 3 * 1000);
    }

    @Override
    public void onBackPressed() {

    }
}
