package com.example.pc.todo;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.pc.todo.R.id.nameText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);

        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        TextView dismissButton = (TextView) findViewById(R.id.dismissButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString();
                String password = passwordText.getText().toString();
                String name = nameText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원등록에 성공하였습니다.")
                                        .setPositiveButton("Done",null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                RegisterActivity.this.startActivity(intent);

                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("회원등록에 실패하였습니다.")
                                        .setNegativeButton("Try again",null).create().show();
                            }

                        }

                        catch(JSONException e){
                            e.printStackTrace();
                        }
                               }
                };
                RegisterRequest registerRequest = new RegisterRequest(id,password,name,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);



            }
        });


        }
    }

