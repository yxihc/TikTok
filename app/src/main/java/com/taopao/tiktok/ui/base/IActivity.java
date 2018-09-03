package com.taopao.tiktok.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 17:02
 * @Use：
 */
public interface IActivity {
    @LayoutRes
    int layoutResID();

    void initView(@Nullable Bundle savedInstanceState);

    void initData();
}
