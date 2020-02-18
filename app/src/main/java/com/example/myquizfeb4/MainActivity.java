package com.example.myquizfeb4;
                                                                    //submit java file, xml file, and strings.xml as deliverable one also include screenshot of output
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private static final String TAG="MainActivity";
    private TextView  mQuestionTextView;
    private QuestionBank[] questionBanks = new QuestionBank[]{
            new QuestionBank(R.string.questions_1,true),
            new QuestionBank(R.string.questions_2,true),
            new QuestionBank(R.string.questions_3,false),
            new QuestionBank(R.string.questions_4,false)
    };
    //this point to current question
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();
        //identify button here - this is links to xml file
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View v)
           {
                //button action here
              checkAnswer(true);

            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View v) {
                //button action here
               checkAnswer(false);

            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override

                public void onClick (View v) {
                if(currentIndex<questionBanks.length-1){
                    currentIndex = currentIndex+1;}
                else{
                    currentIndex=0;}

                    int questionNo = questionBanks[currentIndex].getQuestion();
                    mQuestionTextView.setText(questionNo);
                }
                //button action here


        });


    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "Inside Onstart");

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "Inside Onstart");

    }    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Inside Onstart");

    }    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside Onstart");

    }    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Inside Onstart");

    }

    void updateQuestion(){
        int questionNo= questionBanks[currentIndex].getQuestion();
        mQuestionTextView.setText(questionNo);

    }

    private void checkAnswer(boolean userPressedTrue){
        boolean questionAnswer = questionBanks[currentIndex].checkQuestionAnswer();
        int messageResID=0;
        if(userPressedTrue==questionAnswer){
            messageResID=R.string.correct_toast;}
        else{
            messageResID=R.string.incorrect_toast;}
        Toast.makeText(this,messageResID,Toast.LENGTH_SHORT).show();
        }
    }

