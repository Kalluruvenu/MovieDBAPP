package com.example.vk.piratedmovies;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by VK on 6/10/2017.
 */

public class SearchMoviesAPI {
    String url = "https://api.themoviedb.org/3/search/movie";
    public static int totalPages;
    public static int pageNumber;
    private UpdateInterface updateInterface;
    ArrayList<Movies> moviesArrayList = new ArrayList<>();

    public ArrayList<Movies> movies(String movieSearch, int pageNo, final UpdateInterface updateInterface) {

        this.updateInterface = updateInterface;

        String uri = String.format("https://api.themoviedb.org/3/search/movie?api_key=4b66e3b5d30f2766f7cac852a19ccd94&language=en-US&query="+movieSearch+"&page="+pageNo);

        String tag_string_req = "req_login";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,uri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);


                     pageNumber = jsonObject.getInt("page");
                    int totalResults = jsonObject.getInt("total_results");
                     totalPages = jsonObject.getInt("total_pages");
                    JSONArray results = jsonObject.getJSONArray("results");
                    final int numberOfItemsInResp = results.length();
                    for (int i = 0; i < numberOfItemsInResp; i++){
                        JSONObject item = results.getJSONObject(i);
                        Movies movies  = new Movies();
                        movies.setTitle(item.getString("title"));
                        movies.setImage_path("http://image.tmdb.org/t/p/w185/"+item.getString("poster_path"));
                        movies.setOriginal_title(item.getString("overview"));
                        moviesArrayList.add(movies);

                    }
                    if(!moviesArrayList.isEmpty()){
                    updateInterface.updatedlist(moviesArrayList);}
                    Log.e("size11", String.valueOf(moviesArrayList.size()));

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Login Response: ",volleyError.toString());
            }
        }) {
            };

        AppController.getInstance().addToRequestQueue(stringRequest,tag_string_req);
        Log.e("size1345", String.valueOf(moviesArrayList.size()));

        return moviesArrayList;
    }



}
