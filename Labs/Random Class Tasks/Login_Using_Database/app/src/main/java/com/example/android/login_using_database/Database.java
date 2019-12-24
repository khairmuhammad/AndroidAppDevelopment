package com.example.android.login_using_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    Context context;

    public Database(@Nullable Context context) {
        super(context, "Donate", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS donation_table " +
                "(userID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, value VARCHAR, type VARCHAR);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    
    public void insertData(String v, String type){
        try {
            SQLiteDatabase db =getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            
            contentValues.put("value",  v);
            contentValues.put("type", type);
            
            db.insert("donation_table", null ,contentValues);
            
        }catch (Exception e){
            Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from donation_table",null);

        return cursor ;
    }
}
