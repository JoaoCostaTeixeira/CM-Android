package com.example.cm_hw1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ImageView;


public class FirtActivity extends AppCompatActivity {
    TextView phoneNumber;
    ImageButton remove;
    ImageView call;
    SharedPreferences sharedPreferences;
    Button btn1, btn2, btn3;


    public static final String EXTRA_MESSAGE = "com.example.cm_hw1.CALL";
    public static final String EXTRA_MESSAGE2 = "com.example.cm_hw1.ADD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout2);
        phoneNumber = (TextView) findViewById(R.id.textView);
        remove = (ImageButton) findViewById(R.id.imageButton);
        call = (ImageView) findViewById(R.id.imageView);

        //disables call button
        call.setEnabled(false);

        sharedPreferences= getSharedPreferences("MySharedPref",MODE_PRIVATE);

         // speed dial buttons
         btn1 = (Button) findViewById(R.id.button);
         btn2 = (Button) findViewById(R.id.button2);
         btn3 = (Button) findViewById(R.id.button3);

         //verify Prefs
         refreshPrefs();

        // Speed dial 1
        btn1.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                final String s1 = sharedPreferences.getString("number1number", "");
                final String s2 = sharedPreferences.getString("number1name", "");
                if (s1.length() > 0) {
                    new AlertDialog.Builder(FirtActivity.this)
                            .setTitle("SpeedDial")
                            // call using speed dial
                            .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(FirtActivity.this, SecondActivity.class);
                                    intent.putExtra(EXTRA_MESSAGE, s1+ ","  + s2);
                                    startActivity(intent);
                                }
                            })
                            // remove speed dial
                            .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences.Editor myEdit= sharedPreferences.edit();
                                    myEdit.remove("number1number");
                                    myEdit.remove("number1name");
                                    myEdit.remove("number1name2");
                                    myEdit.commit();
                                    refreshPrefs();
                                }
                            })
                            .setIcon(android.R.drawable.ic_input_add)
                            .show();
                }else{
                    // Create a new speed dial
                    Intent intent = new Intent(FirtActivity.this, ThirdActivity.class);
                    intent.putExtra(EXTRA_MESSAGE2, "1");
                    startActivity(intent);
                }
                    return true;
                }

        });

        // Speed dial 2
        btn2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                final String s1 = sharedPreferences.getString("number2number", "");
                final String s2 = sharedPreferences.getString("number2name", "");
                if (s1.length() > 0) {
                    new AlertDialog.Builder(FirtActivity.this)
                            .setTitle("SpeedDial")
                            // call using speed dial
                            .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(FirtActivity.this, SecondActivity.class);
                                    intent.putExtra(EXTRA_MESSAGE, s1+ ","  + s2);
                                    startActivity(intent);
                                }
                            })
                            // remove speed dial
                            .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences.Editor myEdit= sharedPreferences.edit();
                                    myEdit.remove("number2number");
                                    myEdit.remove("number2name");
                                    myEdit.remove("number2name2");
                                    myEdit.commit();
                                    refreshPrefs();
                                }
                            })
                            .setIcon(android.R.drawable.ic_input_add)
                            .show();
                }else{
                    // Create a new speed dial
                    Intent intent = new Intent(FirtActivity.this, ThirdActivity.class);
                    intent.putExtra(EXTRA_MESSAGE2, "2");
                    startActivity(intent);
                }
                return true;
            }

        });

        // Speed dial 3
        btn3.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                final String s1 = sharedPreferences.getString("number3number", "");
                final String s2 = sharedPreferences.getString("number3name", "");
                if (s1.length() > 0) {
                    new AlertDialog.Builder(FirtActivity.this)
                            .setTitle("SpeedDial")
                            // call using speed dial
                            .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(FirtActivity.this, SecondActivity.class);
                                    intent.putExtra(EXTRA_MESSAGE, s1 + ","  + s2);
                                    startActivity(intent);
                                }
                            })
                            // remove speed dial
                            .setNegativeButton("Remove", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences.Editor myEdit= sharedPreferences.edit();
                                    myEdit.remove("number3number");
                                    myEdit.remove("number3name");
                                    myEdit.remove("number3name2");
                                    myEdit.commit();
                                    refreshPrefs();
                                }
                            })
                            .setIcon(android.R.drawable.ic_input_add)
                            .show();
                }else{
                    // Create a new speed dial
                    Intent intent = new Intent(FirtActivity.this, ThirdActivity.class);
                    intent.putExtra(EXTRA_MESSAGE2, "3");
                    startActivity(intent);
                }
                return true;
            }

        });

        // Clears the number
        remove.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                phoneNumber.setText("");
                return true;
            }

        });
    }

    // Adds one number
    public void sendMessage(View view) {
        Button btn = (Button) findViewById(view.getId());
        String phoneText = (String) phoneNumber.getText();

        // in case it is a active speed dial
        String aux = (String) btn.getText();
        String [] aux2 = aux.split("\n");
        String btntext = aux2[0];

        if (phoneText.length()==0){
            remove.setVisibility(View.VISIBLE);
            remove.setEnabled(true);
            call.setEnabled(true);
        }
        phoneNumber.setText(phoneText + btntext);
    }

    // Removes one number
    public void removeMessage(View view){
       String text =  (String) phoneNumber.getText();
        if (text.length()==1){
            remove.setVisibility(View.INVISIBLE);
            remove.setEnabled(false);
            call.setEnabled(false);
        }
        text = text.substring(0, text.length() - 1);
        phoneNumber.setText(text);

    }

    //call the number
    public void callNumber( View view){
        String text =  (String) phoneNumber.getText();
        Intent intent = new Intent(FirtActivity.this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, text);
        startActivity(intent);

    }


    // Call when the activity is resumed
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        refreshPrefs();
    }



    // Verify Prefs and change button text
    protected void refreshPrefs()
    {
        String s1 = sharedPreferences.getString("number1name2", "");
        String s2 = sharedPreferences.getString("number2name2", "");
        String s3 = sharedPreferences.getString("number3name2", "");

        if(s1.length()>0){
            btn1.setText("1\n"+s1);
        }else {
            btn1.setText("1");
        }
        if(s2.length()>0){
            btn2.setText("2\n"+s2);
        }else {
            btn2.setText("2");
        }
        if(s3.length()>0){
            btn3.setText("3\n"+s3);
        }else {
            btn3.setText("3");
        }
    }
}
