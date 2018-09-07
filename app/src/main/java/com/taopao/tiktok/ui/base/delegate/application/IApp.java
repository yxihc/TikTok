package com.taopao.tiktok.ui.base.delegate.application;

import android.support.annotation.NonNull;

import com.taopao.tiktok.ui.dl.component.AppComponent;

/**
 * @Author：淘跑
 * @Date: 2018/9/7 0007 13:43
 * @Use： 框架要求框架中的每个 {@link android.app.Application} 都需要实现此类, 以满足规范
 */
public interface IApp {
    @NonNull
    AppComponent getAppComponent();
}
