package com.example.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    final int PROGRESS_BAR_INCREMENT = 10;

    Button mTrueButton,mFalseButton;
    TextView mQuestionTextView;
    int mIndex,mQuestion,mScore;
    TextView mScoreTextView;
    ProgressBar mProgressBar;
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1,true),
            new TrueFalse(R.string.question_2,true),
            new TrueFalse(R.string.question_3,true),
            new TrueFalse(R.string.question_4,true),
            new TrueFalse(R.string.question_5,true),
            new TrueFalse(R.string.question_6,false),
            new TrueFalse(R.string.question_7,true),
            new TrueFalse(R.string.question_8,false),
            new TrueFalse(R.string.question_9,true),
            new TrueFalse(R.string.question_10,true),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueButton = findViewById(R.id.button);
        mFalseButton = findViewById(R.id.button2);
        mQuestionTextView = findViewById(R.id.textView);
        mScoreTextView = findViewById(R.id.textView2);
        mProgressBar = findViewById(R.id.progressBar);

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(MainActivity.this, "True button pressed", Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                UpdateQuestion();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "False button pressed", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                UpdateQuestion();
            }
        });
    }
    private void UpdateQuestion(){
        mIndex = (mIndex+1)%mQuestionBank.length;
        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
    }

    private void checkAnswer(boolean userSelection){
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();
        if(correctAnswer == userSelection){
            Toast.makeText(this,R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore+=1;
            mScoreTextView.setText("Score :"+mScore+"/"+mQuestionBank.length);
        }
        else{
            Toast.makeText(this,R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
