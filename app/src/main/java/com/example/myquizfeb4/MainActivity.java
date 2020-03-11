package com.example.myquizfeb4;
                                                                    //submit java file, xml file, and strings.xml as deliverable one also include screenshot of output
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.String.valueOf;
import static java.util.Optional.of;

public class MainActivity extends AppCompatActivity
{

    public boolean first = true;
    public  int newScore=0;
    private boolean ansRight = false;
    private boolean mCheated = false;
    private TextView mUserScore;
    private Button mCheatButton;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 1;
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

    public Score myScore = new Score(0);





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "Inside onCreate");
        if(savedInstanceState!=null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            getIntent().getExtras().getInt("score");                //TODO This wasnt here
        }


        setContentView(R.layout.activity_main);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        //identify button here - this is links to xml file
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        mUserScore = (TextView) findViewById(R.id.user_score);
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        updateQuestion();
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View v)
           {
                //button action here
              checkAnswer(true);
              Log.d(TAG, "score is: "+ valueOf(newScore));



            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View v) {
                //button action here
               checkAnswer(false);
               Log.d(TAG, "score is: "+ valueOf(newScore));




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

        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick (View v){
                Log.d(TAG, "cheat button pressed");
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                boolean b = questionBanks[currentIndex].checkQuestionAnswer();
                i.putExtra("name", "troy");
                i.putExtra("number",currentIndex);
                i.putExtra("TRUE_FALSE", b);
                i.putExtra("score", newScore);

               // startActivity(i);
                startActivityForResult(i,REQUEST_CODE_CHEAT);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) if (requestCode == REQUEST_CODE_CHEAT) {
            if (data != null) {
                mCheated = CheatActivity.wasCheatShown(data);
                Log.d(TAG,"Is cheated activity called");
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "Inside Onstart");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "Inside OnRestart");

    }    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Inside OnResume");
        Log.d(TAG, "Did use cheat"+ mCheated);
        if(first = true) {
            newScore = myScore.getScore();
            myScore.setScore(newScore - 1);
            first = false;
        } else if (first = false) {
            newScore = myScore.getScore();
            myScore.setScore(newScore+1);
        }


    }    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");

    }    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Inside OnPause");

    }

    void updateQuestion(){
        int questionNo= questionBanks[currentIndex].getQuestion();
        mQuestionTextView.setText(questionNo);
        ansRight = false;

    }
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"Inside onSavedInstance");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }

    /*public void showScore(int userScore){
        mUserScore.setText(userScore);
    }*/





    private void checkAnswer(boolean userPressedTrue){
        boolean questionAnswer = questionBanks[currentIndex].checkQuestionAnswer();
        int messageResID=0;
        if(mCheated){
            messageResID = R.string.cheat_toast;
        }else{
        if(userPressedTrue==questionAnswer)
        {
            messageResID=R.string.correct_toast;
                 newScore = myScore.getScore()+1;
                myScore.setScore(newScore);
                first = false;
                ansRight = true;

        }
        else
            messageResID=R.string.incorrect_toast;}
        Toast.makeText(this,messageResID,Toast.LENGTH_SHORT).show();

        }

    }


