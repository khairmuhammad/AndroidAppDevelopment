package com.example.android.lab_exam_mid_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity3 extends AppCompatActivity {

    Intent intent;
    ListView list;
    ArrayList<Book> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        intent = getIntent();
        Bundle args = intent.getExtras();
        arrayList = (ArrayList<Book>) args.getSerializable("bookList");

        Integer[] imageId = {
                R.drawable.test1,
                R.drawable.test2,
                R.drawable.test3,
                R.drawable.test4,
                R.drawable.test5,
                R.drawable.test6,
                R.drawable.test7
        };

        CustomList listAdapter = new
                CustomList(Activity3.this, imageId, arrayList);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Activity3.this, "You Clicked at " + arrayList.get(+position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
