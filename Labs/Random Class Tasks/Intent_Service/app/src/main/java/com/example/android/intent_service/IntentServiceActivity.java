package com.example.android.intent_service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class IntentServiceActivity extends AppCompatActivity {

    private CashbackReciver cashbackReciver;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.cb_results);
        registerCashbackReceiver();
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(cashbackReciver);
    }
    public void startCashbackService(View view){
        EditText et = (EditText) findViewById(R.id.cashback_cat);

        Intent cbIntent =  new Intent();
        cbIntent.setClass(this, CashbackIntentService.class);
        cbIntent.putExtra("cashback_cat", et.getText().toString());
        startService(cbIntent);
    }
    private void registerCashbackReceiver(){
        cashbackReciver = new CashbackReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CashbackIntentService.CASHBACK_INFO);

        registerReceiver(cashbackReciver, intentFilter);
    }
    private class CashbackReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String cbinfo = intent.getStringExtra("cashback");
            tv.setText(cbinfo);
        }
    }

}