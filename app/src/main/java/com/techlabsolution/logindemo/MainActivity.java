package com.techlabsolution.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.techlabsolution.logindemo.API.APIClient;
import com.techlabsolution.logindemo.Pojo.ClsGlobal;
import com.techlabsolution.logindemo.Pojo.Login.ClsLoginPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    EditText etEmail, etPassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiInterface = APIClient.getClient().create(ApiInterface.class);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

    }

    public void goToSingUp(View view) {
        startActivity(new Intent(MainActivity.this, SingupActivity.class));
    }

    public void goToLogin(View view) {
        try {
            if (ClsGlobal.CheckInternetConnection(this)) {
//                Toast.makeText(this,String.valueOf(ClsGlobal.isValidEmail(etEmail.getText().toString())), Toast.LENGTH_SHORT).show();
                if (!ClsGlobal.isValidEmail(etEmail.getText().toString())) {
                    Toast.makeText(this, R.string.wrongEmail, Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().length() < 3) {
                    Toast.makeText(this, R.string.sixDigitsPass, Toast.LENGTH_SHORT).show();
                } else {
                    if (ClsGlobal.isValidEmail(etEmail.getText().toString())) {
                        login(etEmail.getText().toString(), etPassword.getText().toString());
                    }
                }
            } else {
                Toast.makeText(this, R.string.noInternet, Toast.LENGTH_SHORT).show();
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void login(String email, String password) {
        progressDialog.show();
        Call<ClsLoginPojo> call = apiInterface.login(email, password);
        call.enqueue(new Callback<ClsLoginPojo>() {
            @Override
            public void onResponse(Call<ClsLoginPojo> call, Response<ClsLoginPojo> response) {
                if (response.code() == 200) {
                    ClsLoginPojo loginPojo = response.body();
                    if (loginPojo.getStatus().equals("1")) {
                        Toast.makeText(MainActivity.this, loginPojo.getMessage(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, HomeActivity2.class));
                        finish();

                    } else {
                        Toast.makeText(MainActivity.this, loginPojo.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ClsLoginPojo> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }


}