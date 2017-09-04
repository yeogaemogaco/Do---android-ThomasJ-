package com.example.pc.todo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Thread th = new Thread() {
        @Override
            public void run(){
            try
            {
                sleep(300);
            } catch(
            Exception e)

            {
                e.printStackTrace();
            } finally

            {
                Intent obj = new Intent(Main2Activity.this, RegisterActivity.class);
                startActivity(obj);
            }
        }
    };
    th.start();
}

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
