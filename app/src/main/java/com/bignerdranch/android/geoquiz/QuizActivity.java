package com.bignerdranch.android.geoquiz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;

    private Button mFalseButton;

    private TextView mQuestionTextView;

    private ImageButton mNextButton;

    private ImageButton mPrevButton;

    private static final String TAG = "QuizActivity";

    private static final String KEY_INDEX = "index";
    

    private int mCurrentIndex = 0;



    private Question[] mQuestionBank = new Question[]
            {
                    new Question(R.string.question_australia,
                            true),
                    new Question(R.string.question_oceans, true),
                    new Question(R.string.question_mideast,
                            false),
                    new Question(R.string.question_africa,
                            false),
                    new Question(R.string.question_americas,
                            true),
                    new Question(R.string.question_asia, true),
            };

    private ArrayList<Integer> questionAnswerBank = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            questionAnswerBank = savedInstanceState.getIntegerArrayList(KEY_INDEX);
        }
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);


        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               checkAnswer(true);
                disableButtons();
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               checkAnswer(false);
                disableButtons();

            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                nextQuestion();
                checkIfAnswered();

            }

        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {nextQuestion();}
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevQuestion();
                checkIfAnswered();
            }
        });

        updateQuestion();

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,
                mCurrentIndex);
        savedInstanceState.putIntegerArrayList(KEY_INDEX,questionAnswerBank);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    } @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void nextQuestion() {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
    }

    private void prevQuestion(){

        if(mCurrentIndex == 0){
            mCurrentIndex = mQuestionBank.length - 1;
        }
        else
        {
            mCurrentIndex = (mCurrentIndex - 1);
        }

        updateQuestion();
    }

    private void updateQuestion(){
        int question =
                mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        questionAnswerBank.add(mCurrentIndex);

        int messageResId = 0;

        messageResId = userPressedTrue == answerIsTrue ? R.string.correct_toast : R.string.incorrect_toast;

        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();
    }

    //Disable true and false button after user has enter answer.
    private void disableButtons(){
        mFalseButton.setEnabled(false);
        mTrueButton.setEnabled(false);
    }

    private void enableButtons(){
        mFalseButton.setEnabled(true);
        mTrueButton.setEnabled(true);
    }

    private void checkIfAnswered(){
        if (questionAnswerBank.contains(mCurrentIndex)) {
            disableButtons();
        }
        else{
            enableButtons();
        }
    }
}

