package com.example.android.custom_listview_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.bookListView);
        ArrayList<Book> bookArrayList = new ArrayList<>();

        bookArrayList.add(new Book(R.drawable.test1,"The Fault in our stars","john green","2012"));
        bookArrayList.add(new Book(R.drawable.test2,"abc","Me","2022"));

        adapter = new CustomAdapter(this,bookArrayList);

        listView.setAdapter(adapter);

    }
}
