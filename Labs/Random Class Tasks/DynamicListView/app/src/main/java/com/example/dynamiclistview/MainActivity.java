package com.example.dynamiclistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    EditText txt;
    Button btn;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<>();
        arrayList.add("Abc");
        arrayList.add("Def");
        arrayList.add("Ghi");
        arrayList.add("Jkl");
        arrayList.add("Mno");

        listView = (ListView) findViewById(R.id.listView);
        btn = (Button) findViewById(R.id.btnAdd);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Code here
                Toast.makeText(getApplicationContext(),
                        "Selected "+ position,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
