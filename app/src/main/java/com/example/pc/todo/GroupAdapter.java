package com.example.pc.todo;

/**
 * Created by ola on 25/09/2017.
 */
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;


public class GroupAdapter extends RecyclerView.Adapter<GroupHolder> {
    private String[] mDataSet;
    public static Context mContext;
    private Random mRandom = new Random();

    public GroupAdapter(Context context,String[] DataSet){
        mDataSet = DataSet;
        mContext = context;
    }
    @Override
    public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_view,parent,false);
        GroupHolder vh = new GroupHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(GroupHolder holder, int position) {
        //int count = position%9;
        holder.textView.setText(mDataSet[position]);
        holder.textView.getLayoutParams().height = getRandomIntInRange(250,200);
        //holder.itemView.setBackgroundColor(Color.YELLOW);

    }



    @Override
    public int getItemCount(){

        return FetchData.colorsLength;
    }

    // Custom method to get a random number between a range
    protected int getRandomIntInRange(int max, int min){
        return mRandom.nextInt((max-min)+min)+min;
    }


}