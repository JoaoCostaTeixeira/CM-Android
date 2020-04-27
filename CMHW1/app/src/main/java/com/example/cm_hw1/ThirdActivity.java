package com.example.cm_hw1;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.EditText;


public class ThirdActivity extends AppCompatActivity {
    EditText phone;
    EditText name;
    EditText name2;
    TextView phoneT;
    TextView nameT;
    TextView name2T;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout3);
        phone = findViewById(R.id.editText);
        name = findViewById(R.id.editText4);
        name2 = findViewById(R.id.editText3);
        phoneT = findViewById(R.id.textView2);
        nameT = findViewById(R.id.textView3);
        name2T = findViewById(R.id.textView4);

        // speed dial number
        Intent intent = getIntent();
        message = intent.getStringExtra(FirtActivity.EXTRA_MESSAGE2);
        System.out.println(message);

    }

    // save speed dial
    public void saveDial(View view) {

        String s_phone = phone.getText().toString();
        String s_name = name.getText().toString();
        String s_name2 = name2.getText().toString();

        // verify is the user completed the form
        // And highlight whats missing
        boolean ready = true;
        if(s_phone.length()==0){
            phoneT.setTextColor(Color.RED);
            ready = false;
        }
        if(s_name.length()==0){
            nameT.setTextColor(Color.RED);
            ready = false;
        }
        if(s_name2.length()==0){
            name2T.setTextColor(Color.RED);
            ready = false;
        }

        // gets the prefs
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // if the form was completed
        if(ready){
                myEdit.putString("number"+message+"number", s_phone);
                myEdit.putString("number"+message+"name", s_name);
                myEdit.putString("number"+message+"name2", s_name2);
                myEdit.commit();
                super.onBackPressed();
        }
    }
}
