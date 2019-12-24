package com.example.android.databasedictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create Database
        createDatabase();

        //insert into database
        insertDatabase("pressure","kuch b");
        insertDatabase("love","pyar");
        insertDatabase("hate","nafrat");
        insertDatabase("people","log");

    }

    private void createDatabase(){

        db = openOrCreateDatabase
                ("Dictionary",MODE_PRIVATE,null);

        String sql = "CREATE TABLE IF NOT EXISTS Words" +
                " (word VARCHAR(50) NOT NULL, meaning VARCHAR(100));";

        db.execSQL(sql);

    }

    private void insertDatabase(String word, String meaning)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("word",word);
        contentValues.put("meaning",meaning);
        db.insert("Words",null,contentValues);

    }

    public void onClick(View v)
    {
        try {
            EditText txt = (EditText) findViewById(R.id.edit_txt_word);
            String word = String.valueOf(txt.getText());
            TextView tv = (TextView) findViewById(R.id.txt_showMeaning);
            String meaning=null;

            Cursor cursor = db.rawQuery("SELECT * FROM Words where '"+word+"' like word", null);
            cursor.moveToFirst();
            do {
                String wordz = cursor.getString(
                        cursor.getColumnIndex("word"));
                meaning = cursor.getString(
                        cursor.getColumnIndex("meaning"));
                Toast.makeText(this,   wordz + " meaning " + meaning, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
            cursor.close();

        }
        catch (Exception e)
        {
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }


}
