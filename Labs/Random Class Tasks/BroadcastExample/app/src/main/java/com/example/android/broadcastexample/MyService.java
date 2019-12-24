package com.example.android.broadcastexample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.net.MalformedURLException;

public class MyService extends Service {
    public MyService() {
    }

    MediaPlayer mp;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp = MediaPlayer.create(this,R.raw.abc);
        mp.start();

        Intent intent1 = new Intent();
        intent1.setAction("com.example.jani");
        //put any data
        intent1.putExtra("Song1","data");
        sendBroadcast(intent1);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mp.stop();
        mp.release();
        stopSelf();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
