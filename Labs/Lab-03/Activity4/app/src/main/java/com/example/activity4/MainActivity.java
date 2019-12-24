package com.example.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText txt;
    EditText txt1;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this,ThirdActivity.class);
        prefs = getSharedPreferences("myData",MODE_PRIVATE);
        if(prefs.getBoolean("isLogined",false) == true)
            startActivity(intent);
    }

    public void register(View view)
    {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }



    public void login(View view)
    {
        txt = (EditText) findViewById(R.id.userName);
        txt1 = (EditText) findViewById(R.id.password);

        prefs = getSharedPreferences("myData",MODE_PRIVATE);
        editor = prefs.edit();


        if(txt.getText().equals("khair@gmail.com") || txt1.getText().toString().equals("12345")) {
            Toast.makeText(this,"call login",Toast.LENGTH_SHORT).show();
            editor.putBoolean("isLogined", true).apply();
            editor.commit();
            Intent intent = new Intent(this,ThirdActivity.class);
            prefs = getSharedPreferences("myData",MODE_PRIVATE);
            if(prefs.getBoolean("isLogined",false) == true)
                startActivity(intent);
        }
    }

}
