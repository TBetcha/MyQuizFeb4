package com.example.myquizfeb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends AppCompatActivity {
    private static final String TAG = "Cheat Activity";


    public int cheatScore;
    public int score;
    private TextView mCheatAnswerTextView;
    private Button mShowCheatButton;
    private String name;
    private int questionNo;
  //  private TextView nameTextView;
    private boolean ischeated = false;
    private boolean ischeatedButtonPressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Log.d(TAG, "On create");

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                name = extras.getString("name");
                questionNo = extras.getInt("number");
                score = extras.getInt("score");
                ischeatedButtonPressed = extras.getBoolean("TRUE_FALSE", false);
                Log.d(TAG, name);
                Log.d(TAG, String.valueOf(questionNo));
                mCheatAnswerTextView = findViewById(R.id.cheat_answer_text_view);
                mShowCheatButton = findViewById(R.id.show_cheat_button);
                mShowCheatButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ischeatedButtonPressed) {
                            mCheatAnswerTextView.setText(R.string.true_button);
                        } else {
                            mCheatAnswerTextView.setText(R.string.false_button);
                        }
                    }
                });
                //    nameTextView.setText(name);
            }/*else{
                nameTextView = findViewById(R.id.name_text_view);
                nameTextView.setText(name);
                Log.d(TAG, name);
                Log.d(TAG,String.valueOf(questionNo));
                if(questionNo>=0){
                    ischeated=true;
                }
            }*/

            mCheatAnswerTextView = findViewById(R.id.cheat_answer_text_view);
            //   nameTextView.setText(name);
            //   if(questionNo>=0)
            {
                ischeated = true;
                if (ischeated) {
                     cheatScore = score;

                }
            }

        }
    }


    protected static boolean wasCheatShown(Intent i){
        return i.getBooleanExtra("Is cheated", false);
    }

    private void setAnswerResult(boolean b){
        Intent i = getIntent();
        i.putExtra("Is cheated", b );
        i.putExtra("score",cheatScore );
        setResult(RESULT_OK, i);
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
