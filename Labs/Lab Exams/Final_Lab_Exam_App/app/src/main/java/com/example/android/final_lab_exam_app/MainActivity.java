package com.example.android.final_lab_exam_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.final_lab_exam_app.R.*;

public class MainActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private NumberPicker numberPicker;
    private RadioButton direct, payPal;
    private EditText txtAmount;
    private Button donateButton;
    private TextView showDonateAmount;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ProgressBar progressBar;
    int pickerValue=0;
    private int totalAmount=0;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        //Initializing All the fields.
        numberPicker = findViewById(id.number_picker_amount);
        direct = findViewById(id.radio_direct);
        payPal = findViewById(id.radio_payoneer);
        txtAmount = findViewById(id.edit_text_amount);
        donateButton = findViewById(id.donate_button);
        showDonateAmount = findViewById(id.donate_amount_show);
        progressBar = findViewById(id.progressBar);

        //Setting values to Number Picker.
        setNumberPicker();

        sharedPreferences = getSharedPreferences("Donation",MODE_PRIVATE);
        editor = sharedPreferences.edit();


        //Checking Change in EditText
        txtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String txt = txtAmount.getText().toString();

                if (txt.isEmpty()){

                }
                else{
                    int value = Integer.parseInt(txtAmount.getText().toString());
                    if (value>1000){
                        Toast.makeText(getApplicationContext(), "Value can't exceed from 1000", Toast.LENGTH_SHORT).show();
                        txtAmount.setText("");
                        txtAmount.setHint("$$$");
                    }
                    numberPicker.setValue(value);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setNumberPicker(){
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(1000);
        progressBar.setMax(1000);
        pickerValue = numberPicker.getValue();
        numberPicker.setOnValueChangedListener(this);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        txtAmount.setText(newVal+"");
    }

    public void donate(View view){
        String type = "";
        if(direct.isChecked()){
            type = "direct";
            totalAmount += Integer.parseInt(txtAmount.getText().toString());
            editor = sharedPreferences.edit();
            editor.putString("method","direct");
            editor.putInt("amount",Integer.parseInt(txtAmount.getText().toString()));
            editor.putInt("totalAmount",totalAmount);
            editor.commit();
            editor.apply();
            progressBar.setProgress(totalAmount);
        }
        else if(payPal.isChecked()) {
            type = "payPal";
            totalAmount += Integer.parseInt(txtAmount.getText().toString());
            editor = sharedPreferences.edit();
            editor.putString("method","payPal");
            editor.putInt("amount",Integer.parseInt(txtAmount.getText().toString()));
            editor.putInt("totalAmount",totalAmount);
            editor.commit();
            editor.apply();
            progressBar.setProgress(totalAmount);
        }
        else
            Toast.makeText(getApplicationContext(), "Please check one Method Direct/PayPal", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == id.playMusic)
        {
            mediaPlayer = new MediaPlayer();
            mediaPlayer = MediaPlayer.create(this, raw.song);
            mediaPlayer.start();
        }

        return super.onOptionsItemSelected(item);
    }
}