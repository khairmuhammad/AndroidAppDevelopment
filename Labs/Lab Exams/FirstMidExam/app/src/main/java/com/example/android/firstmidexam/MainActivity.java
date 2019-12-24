package com.example.android.firstmidexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_report;
    EditText txt_price, txt_down_payment;
    RadioGroup radioGroup;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_report = (Button) findViewById(R.id.button_loanReport);
        txt_price = (EditText) findViewById(R.id.txt_price);
        txt_down_payment = (EditText) findViewById(R.id.txt_cash_down_payment);
        radioGroup = (RadioGroup) findViewById(R.id.select_year);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = group.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index) {
                    case 0:
                        year = 2;
                        break;
                    case 1:
                        year = 3;
                        break;
                    case 2:
                        year = 4;
                        break;
                }
            }
        });

        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("price", Double.parseDouble(txt_price.getText().toString()));
                intent.putExtra("down_payment", Double.parseDouble(txt_down_payment.getText().toString()));
                intent.putExtra("year", year);
                if (year != 0)
                    startActivity(intent);
                else
                    Toast.makeText(getApplicationContext(), "Select Years", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
