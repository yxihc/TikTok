package com.taopao.delegateapp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：淘跑
 * @Date: 2018/9/8 15:08
 * @Use：
 */
public class AppDelegate implements AppLifecycles {
    private Application mApplication;
    private List<Application.ActivityLifecycleCallbacks> mActivityLifecycles = new ArrayList<>();

    private AppConfig mAppConfigs;

    public AppDelegate() {
        mAppConfigs = new AppConfigImpl();
    }

    @Override
    public void attachBaseContext(@NonNull Context base) {
        mAppConfigs.injectActivityLifecycle(base, mActivityLifecycles);
    }

    @Override
    public void onCreate(@NonNull Application application) {
        this.mApplication = application;
        for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
            mApplication.registerActivityLifecycleCallbacks(lifecycle);
        }
    }

}
