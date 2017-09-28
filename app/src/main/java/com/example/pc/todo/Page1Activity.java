package com.example.pc.todo;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.pc.todo.MainActivity.SHARED_PREF_NAME;

public class Page1Activity extends AppCompatActivity {
    private Context mContext;
    RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton fab_main;
    FloatingActionButton fab_logout;
    FloatingActionButton fab_add_group;
    Animation fab_close,fab_open,rotate_anticlock,rotate_clock;
    boolean isOpen = false;
    public static String getEmail;
    public static String[] colors;
    FetchData process = new FetchData();
    private static final String groupcreate_url = "http://ruddmsdl000.cafe24.com/createnewgroup.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        fab_main = (FloatingActionButton) findViewById(R.id.fab);
        fab_logout = (FloatingActionButton) findViewById(R.id.fab_logout);
        fab_add_group = (FloatingActionButton) findViewById(R.id.fab_groupadd);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        rotate_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anticlockwise);
        rotate_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_clockwise);
        mContext = getApplicationContext();
        //process.execute();
        colors = new String[]{"Red", "Green", "Blue", "Yellow", "Magenta", "Cyan", "Orange",
                "Aqua", "Azure", "Beige", "Bisque", "Brown", "Coral", "Crimson"
        };

        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new GroupAdapter(mContext, colors);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Initialize a new instance of RecyclerView Adapter instance

        // Set the adapter for RecyclerView
        mRecyclerView.setAdapter(mAdapter);

        //db에서 정보 가져와서 담기 !!!

        //Log.d("::::::",getEmail);

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
                View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                final EditText editGroupName = (EditText) dialog.findViewById(R.id.edit_groupname);
                Button createButton = (Button) dialog.findViewById(R.id.create_button);
                createButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String groupName = editGroupName.getText().toString();
                        if (groupName.isEmpty()) {
                            Toast.makeText(Page1Activity.this, "please enter new group name", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("cccc", "ccccc");
                            MainActivity m = new MainActivity();

                            //getEmail = m.login()
                            create(groupName, getEmail);
                            editGroupName.setText("");

                        }


                    }
                });

            }
        });

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    fab_add_group.startAnimation(fab_close);
                    fab_logout.startAnimation(fab_close);
                    fab_logout.setClickable(false);
                    fab_add_group.setClickable(false);
                    isOpen = false;
                } else {
                    fab_add_group.startAnimation(fab_open);
                    fab_logout.startAnimation(fab_open);
                    fab_logout.setClickable(true);
                    fab_add_group.setClickable(true);
                    isOpen = true;
                }
            }
        });
    }
    public void create(String name, String fcreate) {
        final String url_suffix = "?name="+name+"&fcreate="+fcreate;

        class CreateGroup extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPreExecute();
            }
            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                Log.d("background",s);
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(groupcreate_url+s);
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
        CreateGroup creategroup = new CreateGroup();
        creategroup.execute(url_suffix);
        Log.d("3333333",url_suffix);
    }


}









