package com.example.android.memory_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // Fields that will store EditText, TextView and Button
    private Button startButton;
    private EditText mNameEntry;
    private TextView welcomeMessage;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //media player starts
        mp= MediaPlayer.create(this, R.raw.silento);
        mp.start();

        startButton = (Button) findViewById(R.id.start_button);
        mNameEntry = (EditText) findViewById(R.id.et_text_entry);
        welcomeMessage =(TextView) findViewById(R.id.welcomeText);

        /* Setting an OnClickListener allows us to do something when this button is clicked. */
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Get the text entered by the user in the EditText
                String textEntered = mNameEntry.getText().toString();
                //Storing the Context in a variable
                Context context = getApplicationContext();
                // Creating the class that we want to start (and open) when the button is clicked
                Class destinationActivity = GameActivity.class;
                //Create the Intent that will start the Activity we specified above
                Intent startGameActivityIntent = new Intent(context, destinationActivity);
                //putExtra method to put the String from the EditText in the Intent
                startGameActivityIntent.putExtra(Intent.EXTRA_TEXT, textEntered);
                //start the ChildActivity
                startActivity(startGameActivityIntent);
            }
        });
    }
    //this method will stop the music player when this activity will be paused
    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
        Log.v("testing","onStart was called!");
    }
    //this method will start from where there was to play the music player when this activity will be resumed
    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
    }

}
