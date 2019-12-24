package com.example.activity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Button btn_speak;
    //EditText txt;
    ListView list;
    private final int REQ_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("To-do List App");

        list = (ListView) findViewById(R.id.listView);

        btn_speak = (Button) findViewById(R.id.btnAdd);
        //txt = (EditText) findViewById(R.id.txt);

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item,
                listItems);

        list.setAdapter(adapter);

        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Please Speak");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                }
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

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        switch (requestCode){
            case REQ_CODE:{
                if(requestCode == RESULT_OK && intent !=null)
                {
                    ArrayList result = intent
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String spokenText = (String) result.get(result.size());
                    adapter.add(spokenText);
                    //list.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }
}
