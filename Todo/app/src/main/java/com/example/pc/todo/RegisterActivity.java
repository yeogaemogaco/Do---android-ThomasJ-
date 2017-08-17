package com.example.pc.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText idText = (EditText) findViewById(R.id.idText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);
        EditText name = (EditText) findViewById(R.id.nameText);

        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        TextView dismissButton = (TextView) findViewById(R.id.dismissButton);
    }
}
