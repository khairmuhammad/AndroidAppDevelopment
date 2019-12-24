package com.example.android.shaking_light;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;
    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL);

        textView = (TextView) findViewById(R.id.txt_values);
        imageView = (ImageView) findViewById(R.id.img_bulb);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        textView.setText("X: "+x+" Y:  "+y+" Z: "+z);

        if (x >= 0.1 && y>= .20 && z>=6.0)
            imageView.setImageResource(R.drawable.on_image);
        else
            imageView.setImageResource(R.drawable.off_image);



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
