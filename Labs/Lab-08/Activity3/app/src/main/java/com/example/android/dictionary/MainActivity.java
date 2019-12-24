package com.example.android.dictionary;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView list;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Dictionary");

        list = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,listItems);

        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.android.dictionary.WordProvider/words"),
                null,
                null,
                null,
                null,
                null);

        listItems.removeAll(listItems);
        if (cursor.getCount() == 0) {
            // show message that no record found
            Toast.makeText(getBaseContext(), "No Words Found", Toast.LENGTH_LONG).show();
            return;
        }

        while (cursor.moveToNext()) {
            listItems.add(cursor.getInt(0)+" - "+cursor.getString(1));
        }

        adapter.notifyDataSetChanged();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_word)
        {

        }

        else if (item.getItemId() == R.id.search)
        {

        }


        return super.onOptionsItemSelected(item);
    }
}
