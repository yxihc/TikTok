package com.taopao.tiktok.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.taopao.tiktok.ui.base.cache.Cache;
import com.taopao.tiktok.ui.base.cache.LruCache;
import com.taopao.tiktok.ui.di.component.AppComponent;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 17:02
 * @Use：
 */
public interface IActivity {
    /**
     * 提供在 {@link Activity} 生命周期内的缓存容器, 可向此 {@link Activity} 存取一些必要的数据
     * 此缓存容器和 {@link Activity} 的生命周期绑定, 如果 {@link Activity} 在屏幕旋转或者配置更改的情况下
     * 重新创建, 那此缓存容器中的数据也会被清空, 如果你想避免此种情况请使用
     *
     * @return like {@link LruCache}
     */
    @NonNull
    Cache<String, Object> provideCache();

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
