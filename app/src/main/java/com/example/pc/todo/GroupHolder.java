package com.example.pc.todo;

/**
 * Created by ola on 26/09/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.app.ActivityCompat.startActivity;


public class GroupHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView textView;
    Context context;
    TodoActivity todoActivity;
    String url_suffix ;//= "?fgroup="+MainActivity.currentUserEmail;
    public  String groupname;
    public GroupHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        textView = (TextView) itemView.findViewById(R.id.tv);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        this.context = GroupAdapter.mContext;
        groupname = GroupAdapter.mDataSet[position];
        //값보내고 값 받아오기
        //url_suffix = "?fgroup="+groupname;
        //todoActivity.url_suffix = url_suffix;
        todoActivity.groupname_suffix = groupname;
        Intent intent = new Intent(context,TodoActivity.class);
        context.startActivity(intent);

    }




}