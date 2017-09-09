package com.example.pc.todo;

import android.content.Context;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import static com.example.pc.todo.R.attr.alpha;

/**
 * Created by ola on 08/09/2017.
 */

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsAdapter.ViewHolder> {
    private List<String> mDataSet;
    private Context mContext;
    private Random mRandom = new Random();

    public AnimalsAdapter(Context context,List<String> list) {
        mDataSet = list;
        mContext = context;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView;
        public ImageButton mRemoveButton;
        public RelativeLayout mRelativeLayout;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.tv);
            mRemoveButton = (ImageButton)v.findViewById(R.id.imageButton_remove);
            mRelativeLayout = (RelativeLayout)v.findViewById(R.id.rl);
        }

    }
    @Override
    public AnimalsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.custom_view,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTextView.setText((String)mDataSet.get(position));
        int color = getRandomHSVColor();

        holder.mTextView.setBackgroundColor(getLighterColor(color));
        holder.mRelativeLayout.setBackground(getGradientDrawable());
        applyEmbossMaskFilter(holder.mTextView);
        holder.mTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String itemLabel = mDataSet.get(position);
                mDataSet.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mDataSet.size());
                Toast.makeText(mContext,"Removed: "+itemLabel,Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount(){
        return mDataSet.size();
    }
    protected void applyEmbossMaskFilter(TextView tv) {
        EmbossMaskFilter embossFilter = new EmbossMaskFilter(
                new float[]{1f,5f,1f},0.8f,8,7f);
        tv.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        tv.getPaint().setMaskFilter(embossFilter);
    }
    public int getRandomHSVColor() {
        int hue = mRandom.nextInt(36);
        float staturation = 1.0f;
        float value = 1.0f;
        int color = Color.HSVToColor(alpha,new float[]{hue,staturation,value});
        return color;
    }
    protected GradientDrawable getGradientDrawable(){
        GradientDrawable gradient = new GradientDrawable();
        gradient.setGradientType(GradientDrawable.SWEEP_GRADIENT);
        gradient.setColors(new int[]{getRandomHSVColor(),getRandomHSVColor(),getRandomHSVColor()});
        return gradient;
    }
    protected int getDarkerColor(int color) {
        float[]hsv = new float[3];
        Color.colorToHSV(color,hsv);
        hsv[2]=0.2f+0.8f*hsv[2];
        return Color.HSVToColor(hsv);
    }
    protected int getLighterColor(int color) {
        float[] hsv = new float[3];
        Color.RGBToHSV(
                Color.red(color),
                Color.green(color),
                Color.blue(color),hsv
        );
        hsv[0] = (hsv[0]+180%360);
        return Color.HSVToColor(hsv);
    }
}
