package com.proyek.ibf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TTL = "ttl";
    private static final String KEY_NO_HP = "no_hp";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_EMPTY = "";
    //TTL, email, no hp, point
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private EditText etFullName;
    private EditText etEmail;
    private EditText etTTL;
    private EditText etNohp;
    private EditText etAlamat;
    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String email;
    private String ttl;
    private String noHp;
    private String alamat;
    private ProgressDialog pDialog;
    private String register_url = "http://mamina.id/ibf/register.php";
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        session = new SessionHandler(getApplicationContext());
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etTTL = findViewById(R.id.etTTL);
        etNohp = findViewById(R.id.etNohp);
        etAlamat = findViewById(R.id.etAlamat);

        Button login = findViewById(R.id.btnRegisterLogin);
        Button register = findViewById(R.id.btnRegister);

        //start Login page pas Button login diklik
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nerima data yang udah dimasukin di edtText
                username = etUsername.getText().toString().toLowerCase().trim();
                password = etPassword.getText().toString().trim();
                confirmPassword = etConfirmPassword.getText().toString().trim();
                fullName = etFullName.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                ttl = etTTL.getText().toString().trim();
                noHp = etNohp.getText().toString().trim();
                alamat = etAlamat.getText().toString().trim();
                if (validateInputs()) {
                    registerUser();
                }
            }
        });
    }

    /**
     * Nampilin progress bar pas register
     */
    private void displayLoader(){
        pDialog = new ProgressDialog(RegisterActivity.this);
        pDialog.setMessage("Signing Up.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    /**
     * Buka Login Activity ketika Sign up berhasil
     */

    private void loadLogin(){
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void registerUser(){
        displayLoader();
        JSONObject request = new JSONObject();
        try {
            //Populate the request parameters
            request.put(KEY_USERNAME, username);
            request.put(KEY_PASSWORD, password);
            request.put(KEY_FULL_NAME, fullName);
            request.put(KEY_EMAIL, email);
            request.put(KEY_TTL, ttl);
            request.put(KEY_NO_HP, noHp);
            request.put(KEY_ALAMAT, alamat);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsArrayRequest = new JsonObjectRequest
                (Request.Method.POST, register_url, request, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            //Check if user got registered successfully
                            if (response.getInt(KEY_STATUS) == 0) {
                                //Set the user session
                                loadLogin();

                            }else if(response.getInt(KEY_STATUS) == 1){
                                //Display error message if username is already existsing
                                etUsername.setError("Username already taken!");
                                etUsername.requestFocus();

                            }else{
                                Toast.makeText(getApplicationContext(),
                                        response.getString(KEY_MESSAGE), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();

                        //Display error message whenever an error occurs
                        Toast.makeText(getApplicationContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsArrayRequest);
    }

    /**
     * Memvalidasi input dan nampilin error (jika ada)
     * @return
     */

    private boolean validateInputs(){
        if(KEY_EMPTY.equals(fullName)){
            etFullName.setError("Full Name cannot be empty");
            etFullName.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(username)) {
            etUsername.setError("Username cannot be empty");
            etUsername.requestFocus();
            return false;
        }
        if (KEY_EMPTY.equals(email)){
            etEmail.setError("Email cannot be empty");
        }
        if (KEY_EMPTY.equals(ttl)) {
            etTTL.setError("Tempat, Tanggal Lahir cannot be empty");
        }
        if (KEY_EMPTY.equals(noHp)) {
            etNohp.setError("No HP cannot be empty");
        }
        if (KEY_EMPTY.equals(alamat)) {
            etAlamat.setError("Alamat cannot be empty");
        }

        if (KEY_EMPTY.equals(password)) {
            etPassword.setError("Password cannot be empty");
            etPassword.requestFocus();
            return false;
        }

        if (KEY_EMPTY.equals(confirmPassword)) {
            etConfirmPassword.setError("Confirm Password cannot be empty");
            etConfirmPassword.requestFocus();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Password and Confirm Password does not match");
            etConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }
}
