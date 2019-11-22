package com.taopao.modulecamera;

import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.FileUtils;
import com.taopao.modulecamera.audio.MediaRecorderManager;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

//@Route(path = CameraRouterHub.CAMERA_AUDIO_ACTIVITY)
public class AudioActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
    }

    public void start1(View view) {
        // 开始录音
        String dirName = Environment.getExternalStorageDirectory().getPath() + "/" + getResources().getString(R.string.app_name) + "/Audio/";
        String fileName = DateFormat.format("yyyyMMdd_HHmmss", Calendar.getInstance(Locale.CHINA)) + ".m4a";
        MediaRecorderManager.getInstance().start(dirName, fileName);
    }

    public void stop1(View view) {
        MediaRecorderManager.getInstance().stop();
    }

    public void start2(View view) {


    }

    public void stop2(View view) {


    }
}
