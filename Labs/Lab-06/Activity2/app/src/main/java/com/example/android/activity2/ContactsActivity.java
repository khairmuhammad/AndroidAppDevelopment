package com.example.android.activity2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ContactsActivity extends AppCompatActivity {

    EditText txt_phone, txt_email, txt_city, txt_name, txt_street;
    Button btn_save;
    String fileName = "contacts.txt";
    FileOutputStream fileOutputStream;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setTitle("Add Contact");
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
                    String content = txt_name.getText() + " - " + txt_phone.getText() + " - " + txt_email.getText()
                            + " - " + txt_street.getText() + " - " + txt_city.getText() + "\n";
                    saveToFile(content);
                }
            }
        });
    }


    public void saveToFile(String fileContents) {

        try {
            fileOutputStream = openFileOutput(fileName, Context.MODE_APPEND);
            fileOutputStream.write(fileContents.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Contact Saved",
                    Toast.LENGTH_SHORT).show();
            finish();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(),"No File Found",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
