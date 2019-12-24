package com.example.android.broadcastexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.jani");
        //creating a receiver
        Receiver receiver = new Receiver();

        //Registered the receiver
        registerReceiver(receiver,filter);

        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }

    public void stop(View v)
    {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }


    class Receiver extends BroadcastReceiver{

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(getApplicationContext(),
                    intent.getStringExtra("Song1"),Toast.LENGTH_SHORT).show();

            //creating notification manager to manage the notifications
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setContentTitle("Song is Playing");

            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            //adding action to notification
            NotificationCompat.Action action =
                    new NotificationCompat.Action.Builder(R.drawable.baseline, "Open", null)
                            .build();
            builder.addAction(action);


            //building notification
            Notification nf = builder.build();

            nm.notify(0,nf);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && nm != null) {
                //creating channel and notifying the notification manager that channel is linked to
                //the notification manager
                NotificationChannel nc = new NotificationChannel(NotificationChannel.DEFAULT_CHANNEL_ID, "Music", NotificationManager.IMPORTANCE_HIGH);
                nm.createNotificationChannel(nc);
            }
        }
    }
}
