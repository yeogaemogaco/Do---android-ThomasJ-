package com.example.pc.todo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String LOGIN_URL = "http://ruddmsdl000.cafe24.com/Login.php";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String LOGIN_SUCCESS = "success";
    public static final String SHARED_PREF_NAME = "tech";
    public static final String EMAIL_SHARED_PRET = "email";
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
    public static  String currentUserEmail;
    private TextView emailText;
    private TextView passwordText;
    private TextView loginButton;
    private TextView registerButton;
    private boolean loggedIn = false;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = (TextView) findViewById(R.id.emailText);
        passwordText = (TextView) findViewById(R.id.passwordText);
        loginButton = (TextView) findViewById(R.id.loginButton);
        registerButton = (TextView) findViewById(R.id.registerButton);
        sharedPreferences = MainActivity.this.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        registerButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
                                                  MainActivity.this.startActivity(registerIntent);

                                              }
                                          }


        );
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                login();

            }
        });
    };
    private void login() {
        final String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                if (response.trim().equalsIgnoreCase(LOGIN_SUCCESS)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("pref_userEmail", email);
                    editor.commit();
                    currentUserEmail = sharedPreferences.getString("pref_userEmail","n/a");
                    Page1Activity p = new Page1Activity();
                    p.getEmail = currentUserEmail;
                    //String tmp = getUserEmail(currentUserEmail);
                    Log.d("?????/",p.getEmail);
                    editor.putBoolean(LOGGEDIN_SHARED_PREF, true);
                    editor.apply();
                    Intent intent = new Intent(MainActivity.this, Page1Activity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_LONG);
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put(KEY_EMAIL,email);
                params.put(KEY_PASSWORD,password);
                return  params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        loggedIn = sharedPreferences.getBoolean(LOGGEDIN_SHARED_PREF, false);
        currentUserEmail = sharedPreferences.getString("pref_userEmail","n/a");
        Log.d("currentEmail",currentUserEmail);
        Page1Activity p = new Page1Activity();
        p.getEmail = currentUserEmail;
        if(loggedIn) {

            Intent intent = new Intent(MainActivity.this, Page1Activity.class);
            startActivity(intent);


        }

    }


}

