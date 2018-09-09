package com.taopao.delegateapp;

import android.app.Application;
import android.content.Context;

import java.util.List;

/**
 * @Author：淘跑
 * @Date: 2018/9/8 15:15
 * @Use：
 */
public class AppConfigImpl implements AppConfig {
    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {

    }
}
