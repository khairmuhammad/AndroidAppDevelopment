package com.example.android.rolling_ball;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    TextView txt1,txt2,txt3;
    ImageView imageView;
    private long lastUpdate;
    public static int x = 0;
    public static int y = 0;
    public static int z = 0;

    SensorManager mSensorManager;
    Sensor mSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.img_ball);
        txt1 = (TextView) findViewById(R.id.txt_1);
        txt2 = (TextView) findViewById(R.id.txt_2);
        txt3 = (TextView) findViewById(R.id.txt_3);


        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);
        lastUpdate = System.currentTimeMillis();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && x>=0) {

                x -= (int) event.values[0];
                y += (int) event.values[1];
                z += (int) event.values[2];

                imageView.setY(y);
                imageView.setX(x);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    imageView.setZ(z);
                }
            }

        txt1.setText("X: "+x);
        txt2.setText("Y: "+y);
        txt3.setText("Z: "+z);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
