package com.example.android.activity2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    TextView tv;
    String global;
    ListView listView;
    ArrayList<Contact> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contacts");
        tv = (TextView) findViewById(R.id.textView);
        //listView = findViewById(R.id.list_view);
        db = openOrCreateDatabase("ContactsDatabase",MODE_PRIVATE,null);
        db.execSQL("create table if not exists Contacts(id integer not null primary key," +
                " name varchar not null, phone not null, street varchar, email varchar, state varchar);");
        //populateDB();
        arrayList = new ArrayList<Contact>();
        Cursor cr = db.rawQuery("select * from Contacts",null);
        if(cr.getCount() != 0) {
            cr.moveToNext();
            do {
                global += "Name: "+cr.getString(1) + "\n Phone: " + cr.getString(2) + "\n Street: " + cr.getString(3) + "\n Email: " +
                        cr.getString(4) + "\n State: " + cr.getString(5) + "\n\n";
            } while (cr.moveToNext());

            tv.setText(global);
        }
        else
        {
            Toast.makeText(this,"Empty Contact List",Toast.LENGTH_SHORT).show();
        }
    }

    public void populateDB()
    {
        arrayList = new ArrayList<Contact>();
        Cursor cr = db.rawQuery("select * from Contacts",null);
        cr.moveToNext();
        do {
            global+=cr.getString(1)+"\n"+cr.getString(2)+"\n"+cr.getString(3)+"\n"+
            cr.getString(4)+"\n"+cr.getString(5)+"\n\n";
        }while (cr.moveToNext());

        tv.setText(global);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
