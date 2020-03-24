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
    private int lastScore;
    private  int newScore=1;
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
            new QuestionBank(R.string.questions_4,false),
            new QuestionBank(R.string.questions_5, true),
            new QuestionBank(R.string.questions_6,false),
            new QuestionBank(R.string.questions_7, true),
            new QuestionBank(R.string.questions_8,true),
            new QuestionBank(R.string.questions_9, false),
            new QuestionBank(R.string.questions_10,true),
            new QuestionBank(R.string.questions_11,false)

    };

    private Score[] scores = new Score[]{
            new Score(0, "0"),
            new Score(1, " 1"),
            new Score(2, "2"),
            new Score (3, "3"),
            new Score (4,"4"),
            new Score (5, "5"),
            new Score (6, "6"),
            new Score (7,"7"),
            new Score (8, "8"),
            new Score (9, "9"),
            new Score (10,"10"),
            new Score (11, "11")
    };
    //this point to current question
    private int currentIndex = 0;

    public Score myScore = new Score(0, "zero");





    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "Inside onCreate");
        if(savedInstanceState!=null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            getIntent().getExtras().getInt("score");                //TODO This wasnt here
        }


        setContentView(R.layout.activity_main);
        mUserScore = (TextView) findViewById(R.id.user_score);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        //identify button here - this is links to xml file
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (Button) findViewById(R.id.next_button);
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
               String finalScore = scores[newScore].getNumber();
               if((scores[newScore].getNumber().equals(scores[10].getNumber()))){
                   youWin();
               }else {
                   mUserScore.setText(finalScore);
               }

            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View v) {
                //button action here
               checkAnswer(false);
               Log.d(TAG, "score is: "+ valueOf(newScore));
               String finalScore = scores[newScore].getNumber();
               mUserScore.setText(finalScore);
               if((scores[newScore].getNumber().equals(scores[10].getNumber()))){
                   youWin();
               }else {
                   mUserScore.setText(finalScore);
               }



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
                mCheated = true;

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
            myScore.setScore(newScore);
            first = false;
        }else if(first&&mCheated) {
            newScore = myScore.getScore()-1;
            myScore.setScore(newScore);
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

    private void youWin(){
        Toast.makeText(this,"You Win! Close the game and start over!", Toast.LENGTH_LONG).show();
    }




    private void checkAnswer(boolean userPressedTrue){
        boolean questionAnswer = questionBanks[currentIndex].checkQuestionAnswer();
        int messageResID=0;
        if(mCheated){
            messageResID = R.string.cheat_toast;
            mCheated = false;
        }else{
        if(userPressedTrue==questionAnswer)
        {
            messageResID=R.string.correct_toast;
                 newScore = myScore.getScore()+1;
                myScore.setScore(newScore);
                first = false;
                ansRight = true;
                mCheated = false;

        }
        else
            messageResID=R.string.incorrect_toast;}
        Toast.makeText(this,messageResID,Toast.LENGTH_SHORT).show();
        mCheated =false;

        }

    }




