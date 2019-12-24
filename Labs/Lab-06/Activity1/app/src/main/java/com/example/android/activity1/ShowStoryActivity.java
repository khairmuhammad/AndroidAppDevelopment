package com.example.android.activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowStoryActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mStartOverButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story);

        mTextView = (TextView)findViewById(R.id.txt_story);
        mStartOverButton = (Button)findViewById(R.id.btn_startOver);

        Intent intent = getIntent();
        String stringThing = intent.getStringExtra("story");
        mTextView.setText(Html.fromHtml(stringThing));

        mStartOverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
