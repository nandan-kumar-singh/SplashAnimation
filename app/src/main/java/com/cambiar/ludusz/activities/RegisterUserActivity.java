package com.cambiar.ludusz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cambiar.ludusz.R;

public class RegisterUserActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initViews();

    }

    private void initViews() {
        findViewById(R.id.btn_register_user).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_user: {
                startActivity(MainActivity.class);
            }
        }
    }

    private void startActivity(Class mClass) {
        Intent intent = new Intent(this, mClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

}
