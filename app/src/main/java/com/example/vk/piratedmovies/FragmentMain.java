package com.example.vk.piratedmovies;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import android.os.Handler;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by VK on 6/9/2017.
 */

public class FragmentMain extends Fragment implements SearchView.OnQueryTextListener {


    private ArrayList<Movies> moviesList = new ArrayList<>();
    private MyRecyclerAdapter adapter;
    private int totalItemCount, visibleItemCount, pastVisiblesItems;
    private LinearLayoutManager setlayout;
    private Boolean userScrolled = true;
    private String Search ="a";
    private int pageNo = 1;
    Movies movies;
    @BindView(R.id.searchView)
    SearchView searchMovie;
    @BindView(R.id.button2)
    Button submit;
    @BindView(R.id.recview_moviellist)
    RecyclerView rcviewList;

    @BindView(R.id.progressBar)
    RelativeLayout relativeLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        searchMovie.setOnQueryTextListener(this);

       new SearchMoviesAPI().movies(Search, pageNo, new UpdateInterface() {
            @Override
            public void updatedlist(ArrayList<Movies> list) {
                moviesList.addAll(list);
            }
        });
        rcviewList.setHasFixedSize(true);
        setlayout = new LinearLayoutManager(getContext());
        rcviewList.setLayoutManager(setlayout);
        adapter = new MyRecyclerAdapter(getContext(), moviesList);
        rcviewList.setAdapter(adapter);
        paginationListner();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Search= searchMovie.getQuery().toString();
              new SearchMoviesAPI().movies(Search, pageNo, new UpdateInterface() {
                  @Override
                  public void updatedlist(ArrayList<Movies> list) {
                     moviesList.clear();
                      moviesList.addAll(list);
                      adapter.notifyDataSetChanged();
                  }
              });



            }
        });

        return view;


    }


    private void paginationListner() {
        if (SearchMoviesAPI.pageNumber <= SearchMoviesAPI.totalPages) {
            relativeLayout.setVisibility(View.VISIBLE);
            rcviewList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    visibleItemCount = setlayout.getChildCount();
                    totalItemCount = setlayout.getItemCount();
                    pastVisiblesItems = setlayout.findFirstVisibleItemPosition();
                    if (userScrolled && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
                        Log.e("insert", "ture");
                        userScrolled = false;
                        SearchMoviesAPI.pageNumber++;
                        if (SearchMoviesAPI.pageNumber <= SearchMoviesAPI.pageNumber) {
                            Log.e("insert", "false");

                            updateRecyclerview(SearchMoviesAPI.pageNumber);
                        }
                    }
                }
            });

        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("query", query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.e("query2", newText);
        return false;
    }


    private void updateRecyclerview(int PageNo) {
        relativeLayout.setVisibility(View.VISIBLE);
        updateHandler(PageNo);

    }


    private void updateHandler(final int pageNo) {
        relativeLayout.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new SearchMoviesAPI().movies(Search,pageNo, new UpdateInterface() {
                    @Override
                    public void updatedlist(ArrayList<Movies> list) {
                        moviesList.addAll(list);
                    }
                });
                adapter.notifyDataSetChanged();
                userScrolled=true;
                relativeLayout.setVisibility(View.GONE);

            }
        }, 6000);

    }

}
