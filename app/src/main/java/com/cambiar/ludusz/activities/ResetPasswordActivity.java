package com.cambiar.ludusz.activities;

import android.os.Bundle;
import android.view.View;

import com.cambiar.ludusz.R;

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initViews();
    }

    private void initViews() {
        findViewById(R.id.btn_reset_pwd_cancel).setOnClickListener(this);
        findViewById(R.id.btn_reset_pwd_request).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset_pwd_cancel: {
                this.finish();
                break;
            }
            case R.id.btn_reset_pwd_request: {
                break;
            }
            default:
                break;
        }
    }

}
