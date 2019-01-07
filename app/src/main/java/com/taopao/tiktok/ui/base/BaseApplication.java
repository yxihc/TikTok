package com.taopao.tiktok.ui.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.taopao.tiktok.ui.base.delegate.application.AppDelegate;
import com.taopao.tiktok.ui.base.delegate.application.AppLifecycles;
import com.taopao.tiktok.ui.base.delegate.application.IApp;
import com.taopao.tiktok.ui.di.component.AppComponent;
import com.taopao.tiktok.utils.MvpUtils;
import com.taopao.tiktok.utils.Preconditions;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 16:55
 * @Use：
 */
public class BaseApplication extends Application implements IApp {
    private AppLifecycles mAppDelegate;

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    /**
     * 这里会在 {@link BaseApplication#onCreate} 之前被调用,可以做一些较早的初始化
     * 常用于 MultiDex 以及插件化框架的初始化
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null) {
            mAppDelegate = new AppDelegate(base);
        }
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mAppDelegate != null) {
            mAppDelegate.onCreate(this);
        }
        mContext = getApplicationContext();
        CrashReport.initCrashReport(getApplicationContext(), "c6dc9a5939", true);
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null) {
            mAppDelegate.onTerminate(this);
        }
    }

    /**
     * 将 {@link AppComponent} 返回出去, 供其它地方使用, {@link AppComponent} 接口中声明的方法所返回的实例, 在 {@link #getAppComponent()} 拿到对象后都可以直接使用
     *
     * @return AppComponent
     * @see MvpUtils # obtainAppComponentFromContext(Context) 可直接获取 {@link AppComponent}
     */
    @NonNull
    @Override
    public AppComponent getAppComponent() {
        Preconditions.checkNotNull(mAppDelegate, "%s cannot be null", AppDelegate.class.getName());
        Preconditions.checkState(mAppDelegate instanceof IApp, "%s must be implements %s", mAppDelegate.getClass().getName(), IApp.class.getName());
        return ((IApp) mAppDelegate).getAppComponent();
    }
}
