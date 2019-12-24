package com.example.activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listItems;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.listView);

        String[] values = new String[] { "WhatsApp", "Dropbox", "Line",
                "Qoura", "SoundCloud", "SnapChat", "Google+", "Skype",
                "Camera" };
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);
        list.setAdapter(adapter);
    }

}
