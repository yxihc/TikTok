package com.taopao.tiktok.utils;

import android.content.Context;

import com.taopao.tiktok.ui.base.delegate.application.IApp;
import com.taopao.tiktok.ui.dl.component.AppComponent;

/**
 * @Author：淘跑
 * @Date: 2018/9/7 0007 15:36
 * @Use：
 */
public class MvpUtils {
    private MvpUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    public static AppComponent obtainAppComponentFromContext(Context context) {
        Preconditions.checkNotNull(context, "%s cannot be null", Context.class.getName());
        Preconditions.checkState(context.getApplicationContext() instanceof IApp, "Application does not implements App");
        return ((IApp) context.getApplicationContext()).getAppComponent();
    }
}
