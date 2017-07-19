package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity
{

    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";

    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";


    private boolean mAnswerIsTrue;

    private Button mShowAnswerButton;

    private TextView mAnswerTextView;


    /***
     *
     * @param packageContext
     * @param answerIsTrue
     * There is no reason for
        QuizActivity, or any other code in
        your app, to know the implementation
        details of what CheatActivity
        expects as extras on its Intent.
     * @return intent
     */
    static Intent newIntent(Context packageContext, boolean answerIsTrue)
    {
        Intent intent = new Intent(packageContext,
                CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,
                answerIsTrue);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mShowAnswerButton = (Button) findViewById(R.id.show_button);
        mAnswerTextView = (TextView) findViewById(R.id.cheat_text_view);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mAnswerIsTrue)
                {
                    mAnswerTextView.setText(R.string.true_button);
                } else
                {
                    mAnswerTextView.setText(R.string.false_button);
                }

                setAnswerShownResult(true);
            }
        });
    }

    /***
     *
     * @param result
     * @return
     */
    public static boolean wasAnswerShown(Intent result)
    {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,
                false);
    }

    /***
     *
     * @param isAnswerShown
     */
    private void setAnswerShownResult(boolean isAnswerShown)
    {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

}
