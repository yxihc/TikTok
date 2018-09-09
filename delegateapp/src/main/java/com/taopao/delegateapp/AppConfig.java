package com.taopao.delegateapp;

import android.app.Application;
import android.content.Context;

import java.util.List;

/**
 * @Author：淘跑
 * @Date: 2018/9/8 15:15
 * @Use：
 */
public interface AppConfig {
    /* 使用{@link Application.ActivityLifecycleCallbacks}在Activity的生命周期中注入一些操作
     * @param context
     * @param lifecycles
     */
    void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles);
}
