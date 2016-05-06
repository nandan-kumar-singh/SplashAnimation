package com.cambiar.ludusz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cambiar.ludusz.R;

/**
 * Created by vibes on 2/5/16.
 */
public class AppDescriptionActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_description);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AppDescriptionActivity.this, SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                overridePendingTransition(R.anim.zoom_in, R.anim.zoom_exit);
                AppDescriptionActivity.this.finish();
            }
        }, 2 * 1000);
    }

    @Override
    public void onBackPressed() {

    }
}
