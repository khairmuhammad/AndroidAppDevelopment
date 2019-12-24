package com.example.android.intent_service;

import android.app.IntentService;
import android.content.Intent;

public class CashbackIntentService extends IntentService{
    final static String CASHBACK_INFO = "cashback_info";
    public CashbackIntentService() {
        super("Cashback IntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String cb_category = intent.getStringExtra("cashback_cat");

        String cbinfo = getCashbackInfo(cb_category);
        sendCashbackInfoToClient(cbinfo);
    }
    private String getCashbackInfo(String cbcat){
        String cashback;
        if("electronics".equals(cbcat)){
            cashback = "Upto 20% cashback on electronics";
        }else if("fashion".equals(cbcat)){
            cashback = "Upto 60% cashbak on all fashion items";
        }else{
            cashback = "All other categories except fashion and electronics, flat 30% cashback";
        }
        return cashback;
    }
    private void sendCashbackInfoToClient(String msg){
        Intent intent = new Intent();
        intent.setAction(CASHBACK_INFO);
        intent.putExtra("cashback",msg);
        sendBroadcast(intent);
    }
}