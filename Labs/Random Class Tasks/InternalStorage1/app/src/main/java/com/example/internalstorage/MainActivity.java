package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);

        Scanner scan = new Scanner(getResources().
                openRawResource(R.raw.hello));
        String allText="";

        while (scan.hasNextLine())
        {
            String line = scan.nextLine();
            allText+=line;
        }

        textView.setText(allText);
        scan.close();
    }
}
