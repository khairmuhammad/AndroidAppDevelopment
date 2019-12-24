package com.example.android.memory_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {
    //create variable
    private TextView mDisplyName;
    private TextView mDisplayPoints;
    private Button mReset;
    private ImageView iv_11, iv_12, iv_21, iv_22, iv_31, iv_32;
    int firstCard, secondCard, clickedFirst, clickedSecond;
    int cardNumber = 1;
    int points = 0;
    //create a array of cards
    Integer[] cardsArray = {101, 102, 103, 201, 202, 203};
    //create integers for actual cards
    int image101, image102, image103, image201, image202, image203;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Assign value to the ImageView and TextView variable
        mDisplyName = (TextView) findViewById(R.id.tv_name);
        mDisplayPoints = (TextView) findViewById(R.id.tv_points);
        mReset = (Button) findViewById(R.id.reset);
        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);

        //Use the getIntent method to store the Intent that started this Activity in a variable
        Intent intentThatStartedThisActivity = getIntent();
        //Create an if statement to check if this Intent has the extra we passed from MainActivity
        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
            final String textReceived = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            //Set the TextView text
            mDisplyName.setText(textReceived);
        }

        //set tags for each ImageView
        setTags();
        //load the front cards images foe each card
        loadFaceCardImage();
        //shuffle the images array
        Collections.shuffle(Arrays.asList(cardsArray));

        //This button will reset the game going to the main screen
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Storing the Context in a variable
                Context context = GameActivity.this;
                // Creating the class that we want to start (and open) when the button is clicked
                Class destinationActivity = MainActivity.class;
                //Create the Intent that will start the Activity we specified above
                Intent startMainActivityIntent = new Intent(context, destinationActivity);
                //start the MainActivity
                startActivity(startMainActivityIntent);
            }
        });

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cast the tag view to a string and convert to integer
                int theCard = Integer.parseInt((String) view.getTag());
                playGame(iv_11, theCard);
            }
        });

        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                playGame(iv_12, theCard);
            }
        });

        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                playGame(iv_21, theCard);
            }
        });

        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                playGame(iv_22, theCard);
            }
        });

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                playGame(iv_31, theCard);
            }
        });

        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard = Integer.parseInt((String) view.getTag());
                playGame(iv_32, theCard);
            }
        });

    }
    //This method will save the InstanceState
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //this will save the user score if the user change the app orientation
        outState.putInt("points", points);
        //this will save the cards display (array order) if the user change the app orientation
        int c1 = cardsArray[0];
        outState.putInt("card1", c1);
        int c2 = cardsArray[1];
        outState.putInt("card2", c2);
        int c3 = cardsArray[2];
        outState.putInt("card3", c3);
        int c4 = cardsArray[3];
        outState.putInt("card4", c4);
        int c5 = cardsArray[4];
        outState.putInt("card5", c5);
        int c6 = cardsArray[5];
        outState.putInt("card6", c6);
        //this will save the view visibility state
        int iv11 = iv_11.getVisibility();
        outState.putInt("iv11", iv11);
        int iv12 = iv_12.getVisibility();
        outState.putInt("iv12", iv12);
        int iv21 = iv_21.getVisibility();
        outState.putInt("iv21", iv21);
        int iv22 = iv_22.getVisibility();
        outState.putInt("iv22", iv22);
        int iv31 = iv_31.getVisibility();
        outState.putInt("iv31", iv31);
        int iv32 = iv_32.getVisibility();
        outState.putInt("iv32", iv32);

        Log.d("OnSaveInstance","This is my message restore");
    }
    //This method will restore the saveInstanceState using OnRestoreInstanceState implementation
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            //this will restore the user score
            points=savedInstanceState.getInt("points");
            mDisplayPoints.setText("points: "+ points);
            //this will restore the cards display (array order)
            cardsArray[0] = savedInstanceState.getInt("card1");
            cardsArray[1] = savedInstanceState.getInt("card2");
            cardsArray[2] = savedInstanceState.getInt("card3");
            cardsArray[3] = savedInstanceState.getInt("card4");
            cardsArray[4] = savedInstanceState.getInt("card5");
            cardsArray[5] = savedInstanceState.getInt("card6");
            //this will restore the view visibility
            iv_11.setVisibility(savedInstanceState.getInt("iv11"));
            iv_12.setVisibility(savedInstanceState.getInt("iv12"));
            iv_21.setVisibility(savedInstanceState.getInt("iv21"));
            iv_22.setVisibility(savedInstanceState.getInt("iv22"));
            iv_31.setVisibility(savedInstanceState.getInt("iv31"));
            iv_32.setVisibility(savedInstanceState.getInt("iv32"));


        }else
        {points=0;}
    }
    //This method will manage the way that the game is played
    private  void playGame(ImageView iv, int card){
        // match the integer from the array of cards with the corespondent image
        if(cardsArray[card] == 101)
            iv.setImageResource(image101);
        else if (cardsArray[card] == 102)
            iv.setImageResource(image102);
        else if (cardsArray[card] == 103)
            iv.setImageResource(image103);
        else if (cardsArray[card] == 201)
            iv.setImageResource(image201);
        else if (cardsArray[card] == 202)
            iv.setImageResource(image202);
        else if (cardsArray[card] == 203)
            iv.setImageResource(image203);
        //check which card turn is selected and save it to temporary variable
        if(cardNumber == 1){
            firstCard = cardsArray[card];
            if(firstCard > 200) {
                firstCard = firstCard - 100;  //in order to match the pair card
            }
            cardNumber = 2;  //change the card turn
            clickedFirst = card;
            //disable the card
            iv.setEnabled(false);
        }
        else if(cardNumber ==2){
            secondCard = cardsArray[card];
            if(secondCard > 200) {
                secondCard = secondCard - 100;  //in order to match the pair card
            }
            cardNumber = 1;   //change the card turn
            clickedSecond = card;
            //disable the cards
            disbleCards();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if the selected images are equal and update score
                    keepScore();
                }
            }, 1000);

        }
    }
    //this method will verify if the turned cards are the same, will add or deduct points, and will remove or turn back cards
    private void keepScore(){
        //if images are the same remove them and add points (set the ImageView invisible)
        if(firstCard == secondCard){
            if(clickedFirst == 0 )
                iv_11.setVisibility(View.INVISIBLE);
            else if (clickedFirst == 1 )
                iv_12.setVisibility(View.INVISIBLE);
            else if (clickedFirst == 2 )
                iv_21.setVisibility(View.INVISIBLE);
            else if (clickedFirst == 3 )
                iv_22.setVisibility(View.INVISIBLE);
            else if (clickedFirst == 4 )
                iv_31.setVisibility(View.INVISIBLE);
            else if (clickedFirst == 5 )
                iv_32.setVisibility(View.INVISIBLE);

            if(clickedSecond == 0 )
                iv_11.setVisibility(View.INVISIBLE);
            else if (clickedSecond == 1 )
                iv_12.setVisibility(View.INVISIBLE);
            else if (clickedSecond == 2 )
                iv_21.setVisibility(View.INVISIBLE);
            else if (clickedSecond == 3 )
                iv_22.setVisibility(View.INVISIBLE);
            else if (clickedSecond == 4 )
                iv_31.setVisibility(View.INVISIBLE);
            else if (clickedSecond == 5 )
                iv_32.setVisibility(View.INVISIBLE);
            //add points
            points++;
            mDisplayPoints.setText("Points: "+ points);
        }
        else {
            //if images are not the same
            //turn back the cards again, and...
            turnBackCards();
            //deduct points
            points--;
            mDisplayPoints.setText("Points: "+ points);
        }
        //enable again the cards
        enableCards();
    }
    //this method will load the front cards images for each card
    private void loadFaceCardImage() {
        image101 = R.drawable.emoji101;
        image102 = R.drawable.emoji102;
        image103 = R.drawable.emoji103;
        image201 = R.drawable.emoji201;
        image202 = R.drawable.emoji202;
        image203 = R.drawable.emoji203;
    }
    //this method will set tags for each ImageView
    private void setTags(){
        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_21.setTag("2");
        iv_22.setTag("3");
        iv_31.setTag("4");
        iv_32.setTag("5");
    }
    //this method will enable the cards
    private void enableCards(){
        iv_11.setEnabled(true);
        iv_12.setEnabled(true);
        iv_21.setEnabled(true);
        iv_22.setEnabled(true);
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
    }
    //this method will disable the cards
    private void disbleCards() {
        iv_11.setEnabled(false);
        iv_12.setEnabled(false);
        iv_21.setEnabled(false);
        iv_22.setEnabled(false);
        iv_31.setEnabled(false);
        iv_32.setEnabled(false);
    }
    //this method will turn back the cards
    private void turnBackCards(){
        iv_11.setImageResource(R.drawable.back_emoji);
        iv_12.setImageResource(R.drawable.back_emoji);
        iv_21.setImageResource(R.drawable.back_emoji);
        iv_22.setImageResource(R.drawable.back_emoji);
        iv_31.setImageResource(R.drawable.back_emoji);
        iv_32.setImageResource(R.drawable.back_emoji);
    }
}
