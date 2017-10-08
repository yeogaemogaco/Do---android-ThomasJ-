package com.example.pc.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by ola on 30/09/2017.
 */

public class TodoActivity extends Activity {
    public static String[] tasks;

    public CheckBox check;
    public static String[] doDoNot;
    TodoData todoData;
    String url_suffix = "";
    public static String groupname_suffix="";
    // public String task;

    ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        mListView = (ListView) findViewById(R.id.lvItems);
        url_suffix = "?fgroup="+groupname_suffix;
        tasks = new String[100];
        doDoNot = new String[100];
        Log.d("url_suffix",url_suffix);
        todoData = new TodoData();
        todoData.execute(url_suffix);
        TodoAdapter todoAdapter = new TodoAdapter(this,tasks,doDoNot);
        mListView.setAdapter(todoAdapter);
    }
}
class TodoAdapter extends ArrayAdapter<String>{
    private String[] tasks;
    private String[] doDoNot;
    private Activity context;

    public TodoAdapter(Activity context, String[] tasks, String[] doDoNot) {
        super(context, R.layout.todo_items,tasks);
        this.context = context;
        this.tasks = tasks;
        this.doDoNot = doDoNot;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View r = convertView;
        TodoItems todoItems = null;
        if(r==null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.todo_items,null,true);
            todoItems = new TodoItems(r);
            r.setTag(todoItems);
        }
        else {
            todoItems = (TodoItems) r.getTag();
        }
        //todoItems.task.setText(tasks);
        //todoItems.check.setText(doDoNot[position]);
        return r;
    }
}
class TodoItems {
    TextView task;
    CheckBox check;

    TodoItems(View view) {
        task = (TextView) view.findViewById(R.id.taskText);
        //check = (CheckBox) view.findViewById(R.id.taskText);
    }
}