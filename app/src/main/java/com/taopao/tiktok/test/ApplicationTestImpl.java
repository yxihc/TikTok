package com.taopao.tiktok.test;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.taopao.tiktok.ui.base.delegate.application.AppLifecycles;

/**
 * @Author：淘跑
 * @Date: 2018/9/7 0007 16:16
 * @Use：
 */
public class ApplicationTestImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {

        Log.d("~~~~~~~~~~~~~~~~~~~~~~", "onCreate: ");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
