package com.example.internalstorage2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // write a short text file to the internal storage
        PrintStream output = null;
        try {
             output = new PrintStream(openFileOutput("out.txt",MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        output.println("Hello World");
        output.println("How are you?");
        output.println("How do you do?\nWhat's up?");
        output.close();


        // read the same file, and put its contents into a TextView
        Scanner scan = null;
        TextView textView = (TextView) findViewById(R.id.textView);
        try {
                scan = new Scanner(openFileInput("out.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String allText=""; //read all text from file

        while (scan.hasNextLine())
        {
            String line=scan.nextLine()+"\n";
            allText+=line;
        }
        textView.setText(allText);
        scan.close();
    }
}
