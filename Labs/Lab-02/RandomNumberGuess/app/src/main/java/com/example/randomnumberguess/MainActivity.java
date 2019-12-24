package com.example.randomnumberguess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void validate(View view)
    {
        Random rand = new Random();

        int numberToGuess = rand.nextInt(1000);

        int numberOfTries = 0;

        TextView txt = (TextView) findViewById(R.id.textView);

        EditText guessBox = (EditText) findViewById(R.id.editText);

        txt.setText("Guess a number between 1 and 1000: ");

        int guess = Integer.parseInt(guessBox.getText().toString());

        if(guess == numberToGuess)
        {

        }
        else if (guess<numberToGuess)
        {
            txt.setText("Your guess is too low");
        }
        else if(guess>numberToGuess)
        {
            txt.setText("Your guess is too high");
        }

    }
}
