package com.cambiar.ludusz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.model.LoginForm;
import com.cambiar.ludusz.util.AndroidUtil;
import com.cambiar.ludusz.webservice.LuduszUrlConstants;
import com.cambiar.ludusz.webservice.WebServiceEngine;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class SignInActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = SignInActivity.class.getSimpleName();
    private EditText editTextUserMail, editTextUserPassword;
    private TextInputLayout textInputLayoutUserMail, textInputLayoutUserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();

    }

    private void initViews() {
        editTextUserMail = (EditText) findViewById(R.id.et_sign_in_user_name);
        editTextUserPassword = (EditText) findViewById(R.id.et_sign_in_user_password);
        textInputLayoutUserMail = (TextInputLayout) findViewById(R.id.til_user_name);
        textInputLayoutUserPassword = (TextInputLayout) findViewById(R.id.til_user_password);

        findViewById(R.id.btn_sign_in_register_user).setOnClickListener(this);
        findViewById(R.id.btn_sign_in).setOnClickListener(this);
        findViewById(R.id.btn_sign_in_forget_password).setOnClickListener(this);
        findViewById(R.id.btn_sign_in_google_login).setOnClickListener(this);
        findViewById(R.id.btn_sign_in_twitter_login).setOnClickListener(this);
        findViewById(R.id.btn_sign_in_facebook_login).setOnClickListener(this);
    }

    private void startActivity(Class mClass) {
        Intent intent = new Intent(this, mClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private boolean validateLogin() {
        if (!AndroidUtil.isValidEmail(editTextUserMail.getText().toString())) {
            //textInputLayoutUserMail.setErrorEnabled(true);
            editTextUserMail.setError(getResources().getString(R.string.enter_valid_email));
            return false;
        }
        if (editTextUserPassword != null && editTextUserPassword.getText().toString().length() == 0) {
            editTextUserPassword.setError("Enter password");
            return false;
        }
        /*if (editTextUserPassword != null && editTextUserPassword.getText().toString().length() < 6) {
            editTextUserPassword.setError("Enter a valid password");
            return false;
        }*/
        return true;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in: {
                if (validateLogin()) {
                    doLogin(editTextUserMail.getText().toString().trim(), editTextUserPassword.getText().toString().trim());
                }
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
            case R.id.btn_sign_in_google_login: {
                startActivity(GoogleSignInActivity.class);
                break;
            }
            case R.id.btn_sign_in_facebook_login: {
                break;
            }
            case R.id.btn_sign_in_twitter_login: {
                break;
            }
            default:
                break;
        }

    }

    private void doLogin(String user_name, String user_password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LuduszUrlConstants.BASE_URL)
                .client(WebServiceEngine.getCachedOkHttpClient(this))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LuduszLoginUser luduszLoginuser = retrofit.create(LuduszLoginUser.class);

        Call<LoginForm> celebrities = luduszLoginuser.login(user_name, user_password);

        celebrities.enqueue(new Callback<LoginForm>() {

            @Override
            public void onResponse(Response<LoginForm> response) {
                LoginForm loginInfo = response.body();
                Log.e(TAG, loginInfo.toString());
                startActivity(MainActivity.class);
                Toast.makeText(SignInActivity.this, loginInfo.getData().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(SignInActivity.this, "Login failed!! try again.. ", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getMessage() + "");
            }
        });
    }

    public interface LuduszLoginUser {
        @POST("dblayer.php?action=login")
        Call<LoginForm> login(@Query("user_email") String user_email, @Query("user_password") String user_password);
    }
}
