package com.example.android.serviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button start,stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stop = findViewById(R.id.stop_btn);
        start = findViewById(R.id.start_btn);
    }

    public void start(View v)
    {
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    public void stop(View v)
    {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }



}
