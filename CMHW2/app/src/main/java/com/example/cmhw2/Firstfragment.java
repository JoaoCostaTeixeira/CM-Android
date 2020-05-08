package com.example.cmhw2;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Firstfragment extends Fragment {

    View v;
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mAdapter;
    public Firstfragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_firstfragment, container, false);
        Resources res = getResources();
        String[] city = res.getStringArray(R.array.city);
        mRecyclerView = v.findViewById(R.id.recyclerview);
        mAdapter = new RecyclerViewAdapter(getContext(), city);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

}
