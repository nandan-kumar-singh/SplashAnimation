package com.cambiar.ludusz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.model.RegisterForm;
import com.cambiar.ludusz.util.AndroidUtil;
import com.cambiar.ludusz.util.LuduszConstants;
import com.cambiar.ludusz.webservice.WebServiceEngine;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RegisterUserActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = RegisterUserActivity.class.getSimpleName();
    private TextInputLayout textInputLayoutUserName,
            textInputLayoutUserEmail,
            textInputLayoutUserCountry,
            textInputLayoutUserTypes,
            textInputLayoutUserMobile,
            textInputLayoutUserPassword,
            textInputLayoutUserRePassword;
    private EditText editTextUserName,
            editTextUserEmail,
            editTextUserMobile,
            editTextUserPassword,
            editTextUserRePassword;
    private AutoCompleteTextView autoCompleteTextViewCountry;
    private Spinner spinnerUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        initViews();

    }

    private void initViews() {
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.til_register_user_name);
        textInputLayoutUserEmail = (TextInputLayout) findViewById(R.id.til_register_user_email);
        textInputLayoutUserCountry = (TextInputLayout) findViewById(R.id.til_register_user_country);
        textInputLayoutUserMobile = (TextInputLayout) findViewById(R.id.til_register_user_mobile);
        textInputLayoutUserPassword = (TextInputLayout) findViewById(R.id.til_register_user_password);
        textInputLayoutUserRePassword = (TextInputLayout) findViewById(R.id.til_register_user_re_password);

        editTextUserName = (EditText) findViewById(R.id.et_user_name);
        editTextUserEmail = (EditText) findViewById(R.id.et_user_email);
        autoCompleteTextViewCountry = (AutoCompleteTextView) findViewById(R.id.et_user_country);
        spinnerUserType = (AppCompatSpinner) findViewById(R.id.spinner_user_type);
        editTextUserMobile = (EditText) findViewById(R.id.et_user_mobile);
        editTextUserPassword = (EditText) findViewById(R.id.et_user_password);
        editTextUserRePassword = (EditText) findViewById(R.id.et_user_re_password);

        findViewById(R.id.btn_register_user).setOnClickListener(this);

        String[] countries = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> adapterCountry = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countries);
        autoCompleteTextViewCountry.setAdapter(adapterCountry);

        String[] userType = getResources().getStringArray(R.array.user_types);
        ArrayAdapter<String> adapterUser = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userType);
        spinnerUserType.setAdapter(adapterUser);
    }

    private boolean validateLogin() {

        if (editTextUserName != null && (editTextUserName.getText().toString().trim().length() == 0)) {
            editTextUserName.setError(getResources().getString(R.string.enter_user_name));
            return false;
        }
        if (!AndroidUtil.isValidEmail(editTextUserEmail.getText().toString())) {

            editTextUserEmail.setError(getResources().getString(R.string.enter_valid_email));
            return false;
        }
        if (autoCompleteTextViewCountry != null && (autoCompleteTextViewCountry.getText().toString().trim().length() == 0)) {
            //textInputLayoutUserMail.setErrorEnabled(true);
            autoCompleteTextViewCountry.setError(getResources().getString(R.string.select_a_country));
            return false;
        }
        if (editTextUserMobile != null && (editTextUserMobile.getText().toString().trim().length() == 0)) {
            //textInputLayoutUserMail.setErrorEnabled(true);
            editTextUserMobile.setError(getResources().getString(R.string.enter_a_mobile_number));
            return false;
        }
        if (!AndroidUtil.isValidMobileNumber(editTextUserMobile.getText().toString())) {
            editTextUserMobile.setError(getResources().getString(R.string.enter_a_valid_mobile_number));
            return false;
        }
        if (editTextUserPassword != null && (editTextUserPassword.getText().toString().trim().length() == 0)) {
            //textInputLayoutUserMail.setErrorEnabled(true);
            editTextUserPassword.setError(getResources().getString(R.string.enter_password));
            return false;
        }
        if (editTextUserPassword != null && (editTextUserPassword.getText().toString().trim().length() < 6)) {
            //textInputLayoutUserMail.setErrorEnabled(true);
            editTextUserPassword.setError(getResources().getString(R.string.enter_password_minimum_to_six_char));
            return false;
        }
        if (editTextUserRePassword != null && (editTextUserRePassword.getText().toString().trim().length() == 0)) {
            //textInputLayoutUserMail.setErrorEnabled(true);

            editTextUserRePassword.setError(getResources().getString(R.string.enter_re_password));
            return false;
        }
        if (!editTextUserPassword.getText().toString().equals(editTextUserRePassword.getText().toString())) {
            editTextUserRePassword.setError(getResources().getString(R.string.enter_re_password_confirm));
            return false;
        } else {
            textInputLayoutUserName.setError("");
            textInputLayoutUserEmail.setError("");
            textInputLayoutUserCountry.setError("");
            textInputLayoutUserMobile.setError("");
            textInputLayoutUserPassword.setError("");
            textInputLayoutUserRePassword.setError("");
        }
        return true;
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
                if (validateLogin()) {
                    RegisterForm registerForm = new RegisterForm();
                    registerForm.setUserName(editTextUserName.getText().toString().trim());
                    registerForm.setUserEmail(editTextUserEmail.getText().toString().trim());
                    registerForm.setUserCountry(autoCompleteTextViewCountry.getText().toString().trim());
                    registerForm.setUserMobile(editTextUserMobile.getText().toString().trim());
                    registerForm.setUserPassword(editTextUserPassword.getText().toString().trim());

                    doRegisterUser(registerForm);

                    Log.d(TAG, registerForm.toString());

                    //startActivity(MainActivity.class);
                }
            }
        }
    }

    private void startActivity(Class mClass) {
        Intent intent = new Intent(this, mClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void doRegisterUser(final RegisterForm registerForm) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LuduszConstants.BASE_URL)
                .client(WebServiceEngine.getCachedOkHttpClient(this))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LuduszLoginUser luduszLoginuser = retrofit.create(LuduszLoginUser.class);

        Call<RegisterForm> celebrities = luduszLoginuser.login(registerForm);

        celebrities.enqueue(new Callback<RegisterForm>() {
            @Override
            public void onResponse(Response<RegisterForm> response) {
                Toast.makeText(RegisterUserActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(RegisterUserActivity.this, "Login failed!! try again.. ", Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getMessage() + "");
            }
        });
    }

    public interface LuduszLoginUser {
        @GET("index.php?action=register")
        Call<RegisterForm> login(@Query("login") RegisterForm loginForm);
    }

}
