package com.taopao.tiktok.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.taopao.tiktok.ui.di.component.AppComponent;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 17:02
 * @Use：
 */
public interface IActivity {

    @LayoutRes
    int layoutResID();

    void initView();

    void initData(@Nullable Bundle savedInstanceState);

    /**
     * 提供 AppComponent (提供所有的单例对象) 给实现类, 进行 Component 依赖
     *
     * @param appComponent
     */
    void setupActivityComponent(@NonNull AppComponent appComponent);
}
