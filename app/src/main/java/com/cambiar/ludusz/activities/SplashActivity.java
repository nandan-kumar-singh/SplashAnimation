package com.cambiar.ludusz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cambiar.ludusz.R;

public class SplashActivity extends BaseActivity {
    public static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_exit);
                SplashActivity.this.finish();
            }
        }, 3 * 1000);
    }
}
