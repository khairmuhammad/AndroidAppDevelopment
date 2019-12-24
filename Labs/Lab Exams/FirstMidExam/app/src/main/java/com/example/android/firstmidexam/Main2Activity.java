package com.example.android.firstmidexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView tv1, tv2;
    Double price, down_payment;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn_return = (Button) findViewById(R.id.button_loanReport);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        calculate();
    }

    public void calculate() {
        Intent intent = getIntent();
        tv1 = (TextView) findViewById(R.id.txt_21);
        tv2 = (TextView) findViewById(R.id.txt_22);

        price = intent.getDoubleExtra("price", 0);
        down_payment = intent.getDoubleExtra("down_payment", 0);
        year = intent.getIntExtra("year", 0);

        tv1.setText("\n\t\t\tMonthly Payment is: $" + Math.round(((price + (price * 0.07)) - down_payment) / (12 * year)) +
                "\n\nCar Sticker Price: $" + price +
                "\nyou will put down: $" + down_payment +
                "\nTax Amount: $" + Math.round(price * 0.07) +
                "\nYour cost: $" + (price + (price * 0.07)) +
                "\nBorrowed Amount: $" + Math.round((price + (price * 0.07)) - down_payment) +
                "\nInterest Amount: $" + (price * 0.09) +
                "\n\n\t\t\tLoan Term is: " + year + " years");

        tv2.setText("\nNote:" +
                "\n1.Loan information is made available by Tom's Car Dealership." +
                "\n2.A sales tax rate of 7% us required in the state of California." +
                "\n3.Vehicles are financed at an annual interest rate of 9%.");

    }
}
