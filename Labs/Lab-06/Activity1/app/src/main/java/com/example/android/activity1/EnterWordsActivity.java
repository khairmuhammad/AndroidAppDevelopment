package com.example.android.activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EnterWordsActivity extends AppCompatActivity {

    private Button mOKButton;
    private EditText mMadLibEditText;
    private TextView mWordSpecifierTextView;
    private TextView mWordsLeftTextView;
    private Story mMainStory;
    private Scanner mScanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_words);

        mOKButton = (Button)findViewById(R.id.btn_ok);
        mMadLibEditText = (EditText)findViewById(R.id.edit_txt_word);
        mWordSpecifierTextView = (TextView)findViewById(R.id.txt_hint);
        mWordsLeftTextView = (TextView)findViewById(R.id.txt_wordsLeft);

        ArrayList<Integer> files = new ArrayList<>();
        files.add(R.raw.madlib0);
        files.add(R.raw.madlib1);
        files.add(R.raw.madlib2);
        files.add(R.raw.madlib3);
        files.add(R.raw.madlib4);
        Random random = new Random();
        int index = random.nextInt(files.size());
        int id = files.get(index);
        mScanner = new Scanner(getResources().openRawResource(id));
        mMainStory = new Story(mScanner);

        mWordsLeftTextView.setText(mMainStory.getPlaceholderRemainingCount() + " word(s) left to complete");
        mWordSpecifierTextView.setText(mMainStory.getNextPlaceholder());
        mOKButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mMadLibEditText.getText().toString().equals("")) {
                    mMainStory.fillInPlaceholder(mMadLibEditText.getText().toString());
                    if (mMainStory.getNextPlaceholder().equals("")) {
                        startShowStoryActivity(mMainStory);
                    } else {
                        mMadLibEditText.setText("");
                        mWordsLeftTextView.setText(mMainStory.getPlaceholderRemainingCount() + " word(s) left to complete");
                        mWordSpecifierTextView.setText(mMainStory.getNextPlaceholder());
                    }
                }
            }
        });
    }
    private void startShowStoryActivity(Story story) {
        Intent intent = new Intent(this, ShowStoryActivity.class);
        intent.putExtra("story", story.toString());
        startActivity(intent);
    }

}
