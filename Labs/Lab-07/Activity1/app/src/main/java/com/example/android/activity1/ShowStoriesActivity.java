package com.example.android.activity1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowStoriesActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView listView;
    TextView textView;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stories);
        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.txt_story);

        db = openOrCreateDatabase("Stories", MODE_PRIVATE, null);
        Cursor cr = db.rawQuery("select * from Story", null);

        arrayList = new ArrayList<String>();
        cr.moveToFirst();
        do {

            arrayList.add(String.valueOf(Html.fromHtml(cr.getString(1))));

        } while (cr.moveToNext());

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayList);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(Html.fromHtml(arrayList.get(position)));
            }
        });

    }
}
