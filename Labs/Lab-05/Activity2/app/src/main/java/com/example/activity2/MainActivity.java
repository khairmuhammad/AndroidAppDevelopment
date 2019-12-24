package com.example.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mNewGameButton;
    private Button mGuessButton;
    private Button micButton;

    private TextView mGuessTextView;
    private EditText mGuessLetterEditText;
    private TextView mHintTextView;

    private ImageView mHangImageView;

    private String mTargetWord;

    private StringBuffer mTempWord;
    private StringBuffer mGuessedLetters;

    private static final int TOTAL_TIMES = 6;
    private int mLeftTimes;

    private boolean mIsGameOver;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewGameButton = (Button) findViewById(R.id.new_button);

        //Text to Speech Code
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Speech to Text Code (In Progress)
//        micButton = (Button) findViewById(R.id.micButton);
//        micButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault());
//                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak Please");
//
//            }
//        });


        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
            }
        });

        mGuessLetterEditText = (EditText) findViewById(R.id.guess_letter);

        mGuessButton = (Button) findViewById(R.id.guess_button);
        mGuessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guess(mGuessLetterEditText.getText().toString());
                mGuessLetterEditText.setText("");
            }
        });

        mGuessTextView = (TextView) findViewById(R.id.guess_text_view);

        mHintTextView = (TextView) findViewById(R.id.hint);

        mHangImageView = (ImageView) findViewById(R.id.hang_view);

        initGame();

    }

    private void initGame() {

        String[] words = getResources().getStringArray(R.array.words);

        mTargetWord = words[new Random().nextInt(words.length)];

        mTempWord = new StringBuffer();
        mGuessedLetters = new StringBuffer();

        for (int i = 0; i < mTargetWord.length(); i++) {
            mTempWord.append("?");
        }

        mGuessTextView.setText(mTempWord);

        mLeftTimes = TOTAL_TIMES;

        mIsGameOver = false;

        mHangImageView.setImageResource(R.drawable.hangman6);

        mHintTextView.setText("");
    }

    private void guess(String guess) {

        if (mIsGameOver) {
            return;
        }

        if (TextUtils.isEmpty(guess) || guess.length() > 1 ||
                !Character.isLetter(guess.charAt(0))) {
            Toast.makeText(this, "Please enter a letter", Toast.LENGTH_LONG).show();
            //Text to Speech Code
            textToSpeech.speak("Please enter a letter",TextToSpeech.QUEUE_FLUSH,null);
            return;
        }

        Character letter = guess.charAt(0);

        if (mGuessedLetters.toString().contains(letter + "")) {
            Toast.makeText(this, "You have guessed the letter", Toast.LENGTH_LONG).show();
            //Text to Speech Code
            textToSpeech.speak("You have guessed the letter",TextToSpeech.QUEUE_FLUSH,null);
            return;
        }

        mGuessedLetters.append(letter);

        if (mTargetWord.contains(letter + "")) {
            for (int i = 0; i < mTargetWord.length(); i++) {
                if (mTargetWord.charAt(i) == letter) {
                    mTempWord.setCharAt(i, letter);
                }
            }
        } else {
            mLeftTimes--;
        }

        mGuessTextView.setText(mTempWord);

        mHintTextView.setText("You have guessed: " + mGuessedLetters +
                "(" + mLeftTimes + " guess(es) left)" + "\n" + "?");
        //Text to Speech Code
        textToSpeech.speak("You have entered: " + guess +
                "(" + mLeftTimes + " guess(es) left)",TextToSpeech.QUEUE_FLUSH,null);

        if (mTempWord.toString().equals(mTargetWord)) {
            mIsGameOver = true;
            Toast.makeText(this, "You survive!", Toast.LENGTH_LONG).show();

            mHintTextView.setText("You have guessed: " + mGuessedLetters +
                    "(" + mLeftTimes + " guess(es) left)" + "\n" + mTargetWord);
            //Text to Speech Code
            textToSpeech.speak("You have entered: " + guess +
                    "(" + mLeftTimes + " guess(es) left)"+" You Survived Congratulations",TextToSpeech.QUEUE_FLUSH,null);

            return;
        } else if ((mLeftTimes) == 0 ) {
            mIsGameOver = true;
            Toast.makeText(this, "You are hung...", Toast.LENGTH_LONG).show();

            mHintTextView.setText("You have guessed: " + mGuessedLetters +
                    "(" + mLeftTimes + " guess(es) left)" + "\n" + mTargetWord);

            //Text to Speech Code
            textToSpeech.speak("You have entered: " + guess +
                    "(" + mLeftTimes + " guess(es) left)"+"You are hanged.. so sad :(",TextToSpeech.QUEUE_FLUSH,null);
        }

        mHangImageView.setImageResource(getResources().getIdentifier("hangman" + mLeftTimes,
                "drawable", "com.example.activity2"));

    }
}