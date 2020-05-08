package com.example.cmhw2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.WordViewHolder> {
    LayoutInflater mInflater;
    String []  mWordList;
    private Button buttonPrev=null;
    public static final String EXTRA_MESSAGE = "com.example.cm_hw1.ADD";
    public RecyclerViewAdapter(Context context,String [] wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create view from layout
        View mItemView = mInflater.inflate(
                R.layout.list_ciry, parent, false);

        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        // Retrieve the data for that position
        String mCurrent = mWordList[position];
        // Add the data to the view
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        // Return the number of data items to display
        return mWordList.length;
    }



    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Button wordItemView;
        RecyclerViewAdapter mAdapter;
        public WordViewHolder(View itemView, RecyclerViewAdapter adapter) {
            super(itemView);
            // Get the layout
            wordItemView = itemView.findViewById(R.id.word);
            // Associate with this adapter
            this.mAdapter = adapter;
            // Add click listener, if desired
            wordItemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            FragmentManager manager = ((AppCompatActivity)view.getContext()).getSupportFragmentManager();
            Button text = (Button) view;
            String t =  ""+text.getText();

            int screenSize = view.getContext().getResources().getConfiguration().screenLayout &
                    Configuration.SCREENLAYOUT_SIZE_MASK;

            Second2 second = new Second2(t);
            System.out.println("\n\n\n"+screenSize);
            if( screenSize >= Configuration.SCREENLAYOUT_SIZE_LARGE){

                Button button = (Button) view.findViewById(view.getId());
                if(buttonPrev!=null){
                    System.out.println("ADASDASD");
                    buttonPrev.setBackgroundColor(Color.rgb(0, 221, 255));
                }
                button.setBackgroundColor(Color.rgb(0, 0, 255));
                buttonPrev = button;

                manager.beginTransaction()
                        .replace(R.id.fragmentsecond, second)
                        .commit();
            }else{
                manager.beginTransaction()
                        .replace(R.id.fragmentfirst, second)
                        .addToBackStack(null)
                        .commit();
            }

        }
    }


}


