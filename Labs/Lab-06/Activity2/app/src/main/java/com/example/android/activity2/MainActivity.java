package com.example.android.activity2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    String fileName = "contacts.txt";
    FileInputStream fileInputStream;
    Scanner scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Contacts");
        TextView tv = (TextView) findViewById(R.id.textView);
        //Button btn_deleteAll = (Button) findViewById(R.id.deleteAll);
        try {
            fileInputStream = openFileInput(fileName);
            scanner = new Scanner(fileInputStream);
            String allText = "Name\t-\tNumber\t-\tEmail\t-\tStreet\t-\tCity\n";
            while (scanner.hasNextLine()) {
                allText += scanner.nextLine() + "\n";
            }
            tv.setText(allText);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "No file found!",
                    Toast.LENGTH_SHORT).show();
        }

//        btn_deleteAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PrintWriter writer = null;
//                try {
//                    FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
//                    writer = new PrintWriter(fileOutputStream);
//                    writer.print("");
//                    writer.close();
//                } catch (FileNotFoundException e) {
//                    Toast.makeText(getApplicationContext(), "Not Found", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
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
