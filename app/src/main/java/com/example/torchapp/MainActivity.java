package com.example.torchapp;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean isTorchOn = false;
    CameraManager cameraManager;
    String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnToggle = findViewById(R.id.btnToggle);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        btnToggle.setOnClickListener(v -> {
            try {
                if (isTorchOn) {
                    cameraManager.setTorchMode(cameraId, false);
                    isTorchOn = false;
                    btnToggle.setText("Turn ON");
                } else {
                    cameraManager.setTorchMode(cameraId, true);
                    isTorchOn = true;
                    btnToggle.setText("Turn OFF");
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
