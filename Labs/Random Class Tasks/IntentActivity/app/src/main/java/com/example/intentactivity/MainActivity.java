package com.example.intentactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        EditText et = (EditText) findViewById(R.id.editText);
        String text = et.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,text);
        startActivity(intent);
    }


}
