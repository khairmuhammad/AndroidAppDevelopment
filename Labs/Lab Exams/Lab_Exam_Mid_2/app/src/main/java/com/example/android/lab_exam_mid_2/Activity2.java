package com.example.android.lab_exam_mid_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class Activity2 extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent intent;
    EditText editText;
    RadioGroup radioGroup;
    SQLiteDatabase db;
    Book book;
    ArrayList<Book> arrayList = new ArrayList<Book>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        db = openOrCreateDatabase("BooksDB",MODE_PRIVATE,null);
        db.execSQL("create table if not exists Book(id integer primary key autoincrement," +
                " book_name varchar, author varchar, year varchar);");


//        ContentValues cv = new ContentValues();
//        cv.put("book_name","The Fault in Our Stars");
//        cv.put("author","John Green");
//        cv.put("year","2012");
//        db.insert("Book",null,cv);
//
//        ContentValues cv1 = new ContentValues();
//        cv1.put("book_name","Lost in Paradise");
//        cv1.put("author","John Green");
//        cv1.put("year","2010");
//        db.insert("Book",null,cv1);


    }


    public void onClickDelete(View v)
    {
        db = openOrCreateDatabase("BooksDB",MODE_PRIVATE,null);

        for(int i=18; i<=22; i++) {
            db.execSQL("delete from Book where id=" + i + ";");
            Toast.makeText(this,"delete from Book where id="+i+";",Toast.LENGTH_SHORT).show();
        }

    }


    public void search(View v)
    {
        editText = (EditText) findViewById(R.id.edit_bookName);
        radioGroup = (RadioGroup) findViewById(R.id.radio_grp);

        int id = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(id);
        db = openOrCreateDatabase("BooksDB",MODE_PRIVATE,null);
        Cursor cr = db.rawQuery("select * from Book where "+radioButton.getTag()+" like '"+editText.getText().toString()+"';",null);

        if (cr.getCount()>0)
        {
            cr.moveToFirst();
            do {
                book = new Book(cr.getString(1),cr.getString(2),cr.getString(3));
                arrayList.add(book);
            }while (cr.moveToNext());


            intent = new Intent(this,Activity3.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("bookList",(Serializable) arrayList);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this,"No Book Found",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        sharedPreferences = getSharedPreferences("Login_Info",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (item.getItemId() == R.id.logout)
        {
            editor.putBoolean("isLogin",false).apply();
            editor.commit();
            intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
