package com.example.pc.todo;

/**
 * Created by ola on 26/09/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.v4.app.ActivityCompat.startActivity;


/**
 * Created by ola on 25/09/2017.
 */

public class GroupHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView textView;
    public GroupHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        textView = (TextView) itemView.findViewById(R.id.tv);
        //mTextView = (TextView)itemView.findViewById(R.id.tv);
    }

    @Override
    public void onClick(View view) {
        //Todo View를 객체화 시켜서 각각 불러와야 함

        Intent intent = new Intent(view.getContext(),TodoActivity.class);
        view.getContext().startActivity(intent,null);

    }




}