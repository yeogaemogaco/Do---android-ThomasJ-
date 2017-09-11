package com.example.pc.todo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {


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

            th.start();
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
