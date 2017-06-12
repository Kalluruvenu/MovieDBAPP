package com.example.vk.piratedmovies;
 
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by VK on 6/9/2017.
 */

public class MyCustomViewHolder extends RecyclerView.ViewHolder {
 
    protected ImageView imageView;
    protected TextView textView;
 
    public MyCustomViewHolder(View view) {
        super(view);
        this.imageView = (ImageView) view.findViewById(R.id.imgMovie);
        this.textView = (TextView) view.findViewById(R.id.txtMovietitle);
    }
 
}