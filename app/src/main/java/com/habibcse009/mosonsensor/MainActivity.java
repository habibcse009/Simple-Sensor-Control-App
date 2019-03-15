package com.habibcse009.mosonsensor;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView txtNumber;
    SensorManager sm;
    private RelativeLayout relativeLayout;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNumber = findViewById(R.id.txt_number);

        //initioal sensor object
        sm = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor
                (Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        getSupportActionBar().hide();

        // init relativeLayout
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        // initializing animation drawable by getting background from relative layout
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float value[] = event.values;
            float x = value[0];
            float y = value[1];
            float z = value[2];

            float asr = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
            if (asr >= 2) {
                Random r = new Random();
                int i = r.nextInt(2000);
                txtNumber.setText("" + i);
                Toast.makeText(this, "\t\t\t\t\t\t\t\t\t\t\tMoson Changed\n"+"x=" + x + " y=" + y + " z=" + z, Toast.LENGTH_SHORT).show();

            }
        }
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }

}
