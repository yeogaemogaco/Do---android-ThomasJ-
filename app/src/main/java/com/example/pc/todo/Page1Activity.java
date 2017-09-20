package com.example.pc.todo;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.pc.todo.MainActivity.SHARED_PREF_NAME;

public class Page1Activity extends Activity {
    SharedPreferences sharedPreferences;
    Context context;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    FloatingActionButton fab_main;
    FloatingActionButton fab_logout;
    FloatingActionButton fab_add_group;
    Animation fab_close,fab_open,rotate_anticlock,rotate_clock;
    boolean isOpen = false;
    Button createButton;
    EditText editGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        fab_main = (FloatingActionButton)findViewById(R.id.fab);
        fab_logout = (FloatingActionButton)findViewById(R.id.fab_logout);
        fab_add_group = (FloatingActionButton)findViewById(R.id.fab_groupadd);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        rotate_anticlock  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        rotate_clock  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        editGroupName = (EditText)findViewById(R.id.edit_groupname);

        findViewById(R.id.fab_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPreferenceManager.getInstance(getApplicationContext()).logout();
            }
        });
        findViewById(R.id.fab_groupadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page1Activity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_dialog,null);
                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(android.graphics.Color.TRANSPARENT));
                createButton = (Button)dialogView.findViewById(R.id.create_button);
                createButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();

                    }
                });
                dialog.show();

            }
        });


/*
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);


        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3,1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);
*/



        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen) {
                    fab_add_group.startAnimation(fab_close);
                    fab_logout.startAnimation(fab_close);
                    fab_logout.setClickable(false);
                    fab_add_group.setClickable(false);
                    isOpen = false;
                }
                else {
                    fab_add_group.startAnimation(fab_open);
                    fab_logout.startAnimation(fab_open);
                    fab_logout.setClickable(true);
                    fab_add_group.setClickable(true);
                    isOpen = true;
                }
            }
        });
    }

    public void logout() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private void registerUser() {
        String groupName = editGroupName.getText().toString();
        //session 유지 되고 있는 user id를 가져오는 코드를 추후에 추가해야 한다
        //
       //  addGroupName(groupName);
        }





    }

