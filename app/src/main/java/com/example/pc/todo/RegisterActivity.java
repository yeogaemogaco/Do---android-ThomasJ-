package com.example.pc.todo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RegisterActivity extends AppCompatActivity {
    EditText emailText;
    EditText passwordText;
    EditText nameText;
    TextView registerButton;
    TextView dismissButton;
    private static final String Register_url = "http://ruddmsdl000.cafe24.com/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailText = (EditText) findViewById(R.id.id_email);
        passwordText = (EditText) findViewById(R.id.id_password);
        nameText = (EditText) findViewById(R.id.id_username);

        registerButton = (TextView) findViewById(R.id.registerButton);
        dismissButton = (TextView) findViewById(R.id.dismissButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  registerUser();

                                              }
                                          }
        );
    }
        private void registerUser() {
            String username = nameText.getText().toString();
            String email = emailText.getText().toString();
            String password = passwordText.getText().toString();
            register(password,username,email);
    }
    public void register(String password, String name, String email) {
        final String url_suffix = "?name="+name+"&password="+password+"&email="+email;

        class RegisterUser extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this, "please wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPreExecute();
                loading.dismiss();
                Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                Log.d("background",s);
                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(Register_url+s);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;
                    result = bufferedReader.readLine();
                    Log.d("result",result);
                    return result;
                }
                catch (Exception e) {
                    return null;
                }

            }
        }
        RegisterUser ur = new RegisterUser();
        Log.d("ur",url_suffix);
        ur.execute(url_suffix);
    }
}


