package com.example.pc.todo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.example.pc.todo.MainActivity.colorsLength;

public class Page1Activity extends AppCompatActivity {
    private Context mContext;
    final String url_suffix = "?fcreate="+MainActivity.currentUserEmail;
    RelativeLayout mRelativeLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.ViewHolder mHolder;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton fab_main;
    FloatingActionButton fab_logout;
    FloatingActionButton fab_add_group;
    Animation fab_close,fab_open,rotate_anticlock,rotate_clock;
    boolean isOpen = false;
    public static String getEmail;
    FetchData process;
    public static String[] colors;
    public static String[] groupId;
    List<String> listViewItems;
    String groupName;

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
        process = new FetchData();

        process.execute(url_suffix);
        //colorsLength = process.colorsLength;
        colors = new String[100];

        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new GroupAdapter(mContext, colors);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //List<ItemObjects> gaggeredList = getListItemData();
        mRecyclerView.setAdapter(mAdapter);

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
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
                final EditText editGroupName = (EditText) dialog.findViewById(R.id.edit_groupname);
                Button createButton = (Button) dialog.findViewById(R.id.create_button);
                createButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       groupName = editGroupName.getText().toString();
                        if (groupName.isEmpty()) {
                            Toast.makeText(Page1Activity.this, "please enter new group name", Toast.LENGTH_SHORT).show();
                        } else {
                            create(groupName, getEmail);
                            editGroupName.setText("");
                            finish();
                            startActivity(getIntent());
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
                //Log.d("background",s);
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(groupcreate_url+s);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result = bufferedReader.readLine();

                    return result;
                }
                catch (Exception e) {
                    return null;
                }

            }
        }
        CreateGroup creategroup = new CreateGroup();
        creategroup.execute(url_suffix);
  }
    private void writeItems() {
        create(groupName, getEmail);
    }


    public static String[] toStringArray(JSONArray array) {
        if(array==null)
            return null;

        String[] arr=new String[array.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=array.optString(i);
        }
        return arr;
    }

}









