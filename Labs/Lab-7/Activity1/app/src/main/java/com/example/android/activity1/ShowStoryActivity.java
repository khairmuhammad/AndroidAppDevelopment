package com.example.android.activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowStoryActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mStartOverButton, btn_showStories;
    SQLiteDatabase db;
    ContentValues cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story);
        setTitle("Complete Story with Words");

        //Creating database for stories
        db = openOrCreateDatabase("Stories", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Story(id INTEGER NOT NULL PRIMARY KEY," +
                "story VARCHAR);");

        mTextView = (TextView) findViewById(R.id.txt_story);
        mStartOverButton = (Button) findViewById(R.id.btn_startOver);

        Intent intent = getIntent();
        String stringThing = intent.getStringExtra("story");
        mTextView.setText(Html.fromHtml(stringThing));


        Cursor cr = db.rawQuery("select max(id) from Story;", null);

        int id;
        cr.moveToNext();
        do {
            id = cr.getInt(0);
        } while (cr.moveToNext());
        //inserting into database
        cv = new ContentValues();
        cv.put("id", ++id);
        cv.put("story", stringThing);
        db.insert("Story", null, cv);

        mStartOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });

        btn_showStories = findViewById(R.id.btn_showStories);

        btn_showStories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startShowStoryActivity();
            }
        });

    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void startShowStoryActivity() {
        Intent intent = new Intent(this, ShowStoriesActivity.class);
        startActivity(intent);
    }
}
