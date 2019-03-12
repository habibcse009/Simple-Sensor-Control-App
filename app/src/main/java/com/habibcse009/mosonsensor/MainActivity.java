package com.habibcse009.mosonsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView txtNumber;
    SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNumber = findViewById(R.id.txt_number);
        //initioal sensor object
        sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float value[] = event.values;
            float x = value[0];
            float y = value[1];
            float z = value[2];
            Toast.makeText(this, "\t\t\t\t\t\t\t\t\t\t\tMoson Changed\n"+"x=" + x + " y=" + y + " z=" + z, Toast.LENGTH_SHORT).show();

            float asr = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
            if (asr >= 2) {
                Random r = new Random();
                int i = r.nextInt(2000);
                txtNumber.setText("" + i);
            }
        }
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
