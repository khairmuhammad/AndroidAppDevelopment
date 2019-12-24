package com.example.activity4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void login(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void register(View view)
    {
        EditText fName = (EditText) findViewById(R.id.fullName);
        EditText email = (EditText) findViewById(R.id.emailAddress);
        EditText pass = (EditText) findViewById(R.id.password);

        Intent intent = new Intent(this,MainActivity.class);

        intent.putExtra("fullName",fName.getText().toString());
        intent.putExtra("email",email.getText().toString());
        intent.putExtra("pass",pass.getText().toString());

        startActivity(intent);
    }
}
