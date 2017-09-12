package com.example.pc.todo;

import android.app.Activity;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Page1Activity extends Activity {
    FloatingActionButton fab_main;
    FloatingActionButton fab_logout;
    FloatingActionButton fab_add_group;
    Animation fab_close,fab_open,rotate_anticlock,rotate_clock;
    boolean isOpen = false;

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
}
