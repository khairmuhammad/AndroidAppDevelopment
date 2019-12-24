package com.example.internalstorage3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        File outDir = getExternalFilesDir(null); //get root directory
        File outFile = new File(outDir,"example.txt");
        PrintStream out=null;

        try {
            out = new PrintStream(outFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        out.println("Hello World");
        out.close();

        // read list of pictures in external storage
        //File photoDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File downloadDir = Environment.
                getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        String fileNames="";
        for (File file : downloadDir.listFiles())
        {
            String fileName = file.getName()+"\n";
            fileNames+=fileName;
        }

        textView.setText(fileNames);
    }

    /* Checks if external storage is available
     * for reading and writing */
    public boolean isExternalStorageWritable()
    {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /* Checks if external storage is available
     * for reading */
    public boolean isExternalStorageReadable() {
        return isExternalStorageWritable() ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(
                        Environment.getExternalStorageState());
    }

}
