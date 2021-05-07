package com.techlabsolution.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techlabsolution.logindemo.API.APIClient;
import com.techlabsolution.logindemo.Pojo.ClsGlobal;
import com.techlabsolution.logindemo.Pojo.Login.ClsLoginPojo;
import com.techlabsolution.logindemo.Pojo.Singup.ClsSingupPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingupActivity extends AppCompatActivity {
    EditText etName, etPhone, etEmail, etAddress, etLicenceNumber, etLicenceNumber2, etGst, etPassword;
    Button btnSingUp;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etLicenceNumber = findViewById(R.id.etLicenceNumber);
        etLicenceNumber2 = findViewById(R.id.etLicenceNumber2);
        etGst = findViewById(R.id.etGst);
        etPassword = findViewById(R.id.etPassword);
        btnSingUp = findViewById(R.id.btnSingUp);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singup(etPhone.getText().toString(),
                        etEmail.getText().toString(),
                        etPassword.getText().toString(),
                        etName.getText().toString(),
                        etLicenceNumber.getText().toString(),
                        etLicenceNumber2.getText().toString(),
                        etGst.getText().toString(),
                        etAddress.getText().toString()
                );
            }
        });

    }

    private void Singup(String mobile, String email, String password, String shopname, String licence_number, String licence_number1, String gst_no, String address) {
        try {
            if (!checkValidations()) {


                if (ClsGlobal.CheckInternetConnection(this)) {

                    progressDialog.show();

                    Call<ClsSingupPojo> call = apiInterface.signup(mobile, email, password, shopname, licence_number, licence_number1, gst_no, address, "ok");
                    call.enqueue(new Callback<ClsSingupPojo>() {
                        @Override
                        public void onResponse(Call<ClsSingupPojo> call, Response<ClsSingupPojo> response) {
                            if (response.code() == 200) {
                                ClsSingupPojo singupPojo = response.body();
                                if (singupPojo.getStatus().equals("1")) {
                                    Toast.makeText(SingupActivity.this, singupPojo.getMessage(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SingupActivity.this, HomeActivity2.class));
                                    progressDialog.dismiss();
                                    finish();

                                } else {
                                    Toast.makeText(SingupActivity.this, singupPojo.getMessage(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();

                                }
                            } else {
                                Toast.makeText(SingupActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();

                            }
                        }

                        @Override
                        public void onFailure(Call<ClsSingupPojo> call, Throwable t) {

                            Toast.makeText(SingupActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                        }
                    });

                } else {
                    Toast.makeText(this, R.string.noInternet, Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }

    }

    private boolean checkValidations() {
        boolean invalid = false;
        if (etPhone.getText().toString() == null || etPhone.getText().toString().equals("")) {
            etPhone.setError(getString(R.string.required));
            invalid = true;
        } else if (etPhone.getText().toString().length() != 10) {
            etPhone.setError(getString(R.string.tenDigitMobile));
            Toast.makeText(this, R.string.tenDigitMobile, Toast.LENGTH_SHORT).show();
            invalid = true;

        }

        if (!etEmail.getText().toString().equals("")) {
            if (!ClsGlobal.isValidEmail(etEmail.getText().toString())) {
                etEmail.setError(getString(R.string.enterEmail));
                Toast.makeText(this, R.string.enterEmail, Toast.LENGTH_SHORT).show();
                invalid = true;
            }

        }

        if (etPassword.getText().toString() == null || etPassword.getText().toString().equals("")) {
            // etPassword.setError("");
            Toast.makeText(this, R.string.passRequired, Toast.LENGTH_SHORT).show();

            invalid = true;

        } else if (etPassword.getText().toString().length() < 6) {
            Toast.makeText(this, R.string.sixDigitPassReqed, Toast.LENGTH_SHORT).show();
            invalid = true;

        }


        if (etName.getText().toString() == null || etName.getText().toString().equals("")) {
            etName.setError(getString(R.string.required));
            invalid = true;

        }
        if (etAddress.getText().toString() == null || etAddress.getText().toString().equals("")) {
            etAddress.setError(getString(R.string.required));
            invalid = true;
        }

        if (etGst.getText().toString() == null || etGst.getText().toString().equals("")) {
            etGst.setError(getString(R.string.required));
            invalid = true;

        }

        if (etLicenceNumber.getText().toString() == null || etLicenceNumber.getText().toString().equals("")) {
            etLicenceNumber.setError(getString(R.string.required));
            invalid = true;
        }

        if (etLicenceNumber2.getText().toString() == null || etLicenceNumber2.getText().toString().equals("")) {
            etLicenceNumber2.setError(getString(R.string.required));
            invalid = true;
        }
        return invalid;
    }


}