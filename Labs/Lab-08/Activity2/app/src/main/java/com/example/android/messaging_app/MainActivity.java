package com.example.android.messaging_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.sip.SipSession;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements MessageListener {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Register sms listener
        MessageReceiver.bindListener(this);

        tv = (TextView) findViewById(R.id.text);

    }

    @Override
    public void messageReceived(String message) {
        tv.setText("New Message Received: " + message);
       // Toast.makeText(this, "New Message Received: " + message, Toast.LENGTH_SHORT).show();
    }
}
