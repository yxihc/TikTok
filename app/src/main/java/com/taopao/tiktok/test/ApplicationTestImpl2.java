package com.taopao.tiktok.test;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.taopao.tiktok.ui.base.delegate.application.AppLifecycles;

/**
 * @Author：淘跑
 * @Date: 2018/9/8 11:02
 * @Use：
 */
public class ApplicationTestImpl2 implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {

        Log.d("~~~~~~~~~~~~~~~~~~~~~~", "ApplicationTestImpl2: ");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}