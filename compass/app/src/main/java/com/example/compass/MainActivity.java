package com.example.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Compass compass;
    SensorManager sensorManager;
    private float[] rotateVector = new float[16]; // 旋转向量
    private float[] orientationVector = new float[3]; // 方向值
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compass = (Compass) findViewById(R.id.compass);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
                /*
    ----------------------- 注册加速度计和磁力计监听事件 ----------------------------------
//         */
//        if (selectedSensorId == R.id.accelerometerMagnetometer) {
//
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 通过旋转向量传感器的值 获取旋转矩阵
        SensorManager.getRotationMatrixFromVector(rotateVector, event.values);
        // 通过旋转矩阵获取方向
        SensorManager.getOrientation(rotateVector, orientationVector);
        // 转化为度
        float azimuthDegree = (float) Math.toDegrees(orientationVector[0]);
        compass.rotate(azimuthDegree);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
