package com.cambiar.ludusz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cambiar.ludusz.R;

public class SignInActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = SignInActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();

    }

    private void initViews() {
        findViewById(R.id.btn_sign_in_register_user).setOnClickListener(this);
        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_sign_in_forget_password).setOnClickListener(this);
    }

    private void startActivity(Class mClass) {
        Intent intent = new Intent(this, mClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private boolean validateLogin() {
        return false;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in: {
                startActivity(MainActivity.class);
                break;
            }
            case R.id.btn_sign_in_register_user: {
                startActivity(RegisterUserActivity.class);
                break;
            }
            case R.id.btn_sign_in_forget_password: {
                startActivity(ResetPasswordActivity.class);
                break;
            }
            default:
                break;
        }
    }

}
