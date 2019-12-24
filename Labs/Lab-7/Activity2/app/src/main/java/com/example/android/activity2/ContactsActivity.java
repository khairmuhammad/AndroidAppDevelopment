package com.example.android.activity2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactsActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText txt_phone, txt_email, txt_city, txt_name, txt_street;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setTitle("Add Contact");
        db = openOrCreateDatabase("ContactsDatabase", MODE_PRIVATE, null);

        txt_city = (EditText) findViewById(R.id.txt_city);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_phone = (EditText) findViewById(R.id.txt_phone);
        txt_street = (EditText) findViewById(R.id.txt_street);
        btn_save = (Button) findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_name.getText().toString().isEmpty() || txt_phone.getText().toString().isEmpty()
                        || txt_email.getText().toString().isEmpty() || txt_street.getText().toString().isEmpty()
                        || txt_city.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Insert all data",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cr = db.rawQuery("select max(id) from Contacts;", null);

                    int id;
                    cr.moveToNext();
                    do {
                        id = cr.getInt(0);
                    } while (cr.moveToNext());
                    //inserting into database
                    ContentValues cv = new ContentValues();
                    cv.put("id", ++id);
                    cv.put("name",txt_name.getText().toString());
                    cv.put("phone",txt_phone.getText().toString());
                    cv.put("street",txt_street.getText().toString());
                    cv.put("email",txt_email.getText().toString());
                    cv.put("state",txt_city.getText().toString());

                    db.insert("Contacts",null,cv);

                    Toast.makeText(getApplicationContext(),"Added to Database",Toast.LENGTH_SHORT).show();
                    clearAll();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    public void clearAll()
    {
        txt_city.setText("");
        txt_email.setText("");
        txt_name.setText("");
        txt_street.setText("");
        txt_phone.setText("");
    }
}
