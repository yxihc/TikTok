package com.taopao.videostudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 通过三种方式绘制图片
     *
     * @param view
     */
    public void btn1(View view) {
        startActivity(new Intent(this, ThreeImgActivity.class));
    }

    public void btn2(View view) {
        startActivity(new Intent(this, CameraActivity.class));
    }
}
