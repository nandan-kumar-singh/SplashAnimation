package com.cambiar.ludusz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cambiar.ludusz.R;

import butterknife.ButterKnife;

public class SignInActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = SignInActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {

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
            case R.id.btn_register_user: {
                startActivity(RegisterUserActivity.class);
                break;
            }
            default:
                break;
        }
    }

}
