package com.example.vk.piratedmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by VK on 6/9/2017.
 */

public class FragmentDetails extends Fragment {


    private Intent intent;

    @BindView(R.id.textView)
    TextView txtMovieList;

    @BindView(R.id.textView2)
    TextView txtDeatsil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        intent = getActivity().getIntent();
        txtMovieList.setText(intent.getStringExtra(StringVariables.TITLE));
        txtDeatsil.setText(intent.getStringExtra(StringVariables.MOIVE_DETAILS));
        return view;
    }


}
