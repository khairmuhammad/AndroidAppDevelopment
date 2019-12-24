package com.example.android.dictionary;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;

public class WordProvider extends ContentProvider {

    static final String PROVIDER_NAME = "com.example.android.dictionary.WordProvider";
    static final String URL = "content://"+ PROVIDER_NAME + "/words";
    static final Uri CONTENT_URI = Uri.parse(URL);


    static final String id = "ID";
    static final String word = "WORD";
    static final String meaning = "MEANING";
    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    private static HashMap<String, String> values;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "words", uriCode);
        uriMatcher.addURI(PROVIDER_NAME, "words/*", uriCode);
    }

    @Override
    public boolean onCreate() {

        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);

        db = dbHelper.getWritableDatabase();

        if (db != null)
            return true;

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);

        switch (uriMatcher.match(uri))
        {
            case uriCode:
                qb.setProjectionMap(values);
                 break;
                 default:
                     throw new IllegalArgumentException("Unknown URI "+ uri);
        }

        if (sortOrder == null || sortOrder == "")
            sortOrder = id;

        Cursor c = qb.query(db,projection,selection,selectionArgs,null,null,sortOrder);
        c.setNotificationUri(getContext().getContentResolver(),uri);

        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/words";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        long rowID = db.insert(TABLE_NAME,null,values);

        if(rowID > 0)
        {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }

        throw new SQLiteException("Failed to insert into "+uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        int count = 0;

        switch (uriMatcher.match(uri))
        {
            case uriCode:
                count = db.delete(TABLE_NAME,selection,selectionArgs);
                break;
                default:
                    throw new IllegalArgumentException("Unknown URI "+uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        int count = 0;

        switch (uriMatcher.match(uri))
        {
            case uriCode:
                count = db.update(TABLE_NAME,values,selection,selectionArgs);
                break;
                default:
                    throw new IllegalArgumentException("Unknown URI "+uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }


    private SQLiteDatabase db;
    public static final String DATABASE_NAME = "Dictionary.db";
    public static final String TABLE_NAME = "WordsTable";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE = " CREATE TABLE " + TABLE_NAME
            + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " WORD VARCHAR NOT NULL, MEANING VARCHAR NOT NULL);";

    private static class DatabaseHelper extends SQLiteOpenHelper{

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+CREATE_DB_TABLE);
            onCreate(db);
        }
    }



}
