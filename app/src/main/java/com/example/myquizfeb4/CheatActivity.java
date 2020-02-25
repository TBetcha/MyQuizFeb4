package com.example.myquizfeb4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;



public class CheatActivity extends AppCompatActivity {
    private static final String TAG = "Cheat Activity";

    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Log.d(TAG, "On create");

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                name = extras.getString("name");
                Log.d(TAG, name);
            }else{

                Log.d(TAG, name);
            }
      /*  else{
            name = (String)savedInstanceState.getSerializable("name");

            Log.d(TAG, name);
        }*/
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "on start");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "on resume");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "on destroy");
    }



}
