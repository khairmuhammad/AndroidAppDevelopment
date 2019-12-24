package com.example.activity2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Button button;
    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("To-do List App");

        final ListView list = (ListView) findViewById(R.id.listView);

        button = (Button) findViewById(R.id.btnAdd);
        txt = (EditText) findViewById(R.id.txt);

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                listItems);


        list.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Khair's code to add in list
                listItems.add(txt.getText().toString());
                txt.setText("");
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), txt.getText().toString() + " is added to list", Toast.LENGTH_SHORT).show();
            }
        });

        list.setLongClickable(true);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                //Khair's code to remove from list
                listItems.remove(arg2);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Removed Selected Item", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
