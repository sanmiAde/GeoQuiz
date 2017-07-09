package com.bignerdranch.android.geoquiz;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;

    private Button mFalseButton;

    private TextView mQuestionTextView;

    private ImageButton mNextButton;

    private ImageButton mPrevButton;



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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
               checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                nextQuestion();
            }

        });


        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prevQuestion();
            }
        });

        updateQuestion();

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

        int messageResId = 0;

        messageResId = userPressedTrue == answerIsTrue ? R.string.correct_toast : R.string.incorrect_toast;

        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();
    }

}

