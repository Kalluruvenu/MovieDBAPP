package com.example.vk.piratedmovies;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VK on 6/9/2017.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyCustomViewHolder> {
 
    private List<Movies> listItems = new ArrayList<>();
    private Context mContext;
    private OnLoadMoreListener onLoadMoreListener;


    public MyRecyclerAdapter(Context context, List<Movies> listItems) {
        this.listItems = listItems;
        this.mContext = context;
    }
 
    @Override
    public MyCustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
 
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_movies, null);
        MyCustomViewHolder vh = new MyCustomViewHolder(view);
        return vh;
         
    }
 
    @Override
    public void onBindViewHolder(MyCustomViewHolder customViewHolder, int position) {
 
        final Movies listItem = listItems.get(position);
 
        //Download image using picasso library and set Thumbnail
        Picasso.with(mContext).load(listItem.getImage_path())
                .error(android.R.color.holo_red_light)
                .placeholder(R.mipmap.ic_launcher)
                .into(customViewHolder.imageView);
 
        //Set title
        customViewHolder.textView.setText(listItem.getTitle());
        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent details = new Intent(mContext,DetailsActivity.class);
                details.putExtra(StringVariables.TITLE,listItem.getTitle());
                details.putExtra(StringVariables.MOIVE_DETAILS,listItem.getOriginal_title());
                mContext.startActivity(details);
            }
        });
    }
 
    @Override
    public int getItemCount() {
        return (null != listItems ? listItems.size() : 0);
    }


}