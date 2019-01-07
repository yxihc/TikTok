package com.taopao.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.taopao.commonsdk.RouterHub;

@Route(path = RouterHub.CAMERA_HOMEACTIVITY)
public class CameraHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_home);
    }
}