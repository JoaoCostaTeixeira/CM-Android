package com.example.cm_hw1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import android.content.Intent;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout);
        Intent intent = getIntent();

        //verify if the call was made using a speed call
        String message = intent.getStringExtra(FirtActivity.EXTRA_MESSAGE);
        String [] aux = message.split(",");
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView6);
        if(aux.length==2){
            textView.setText(aux[1]);
            textView2.setText(aux[0]);
        }else{
            textView.setText(aux[0]);
        }

    }

    public void stopCall(View view){
        super.onBackPressed();
    }
}
