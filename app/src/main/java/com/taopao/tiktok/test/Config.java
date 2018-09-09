package com.taopao.tiktok.test;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.taopao.tiktok.ui.base.delegate.application.AppLifecycles;
import com.taopao.tiktok.ui.integration.ConfigModule;

import java.util.List;

/**
 * @Author：淘跑
 * @Date: 2018/9/7 0007 16:15
 * @Use：
 */
public class Config implements ConfigModule {
    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {
        lifecycles.add(new ApplicationTestImpl());
        lifecycles.add(new ApplicationTestImpl2());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        lifecycles.add(new ActivityLifecycleCallbacksImpl());
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }
}
