package com.taopao.tiktok.ui.base.delegate.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.taopao.tiktok.ui.base.IActivity;
import com.taopao.tiktok.ui.base.delegate.activity.ActivityDelegate;
import com.taopao.tiktok.utils.MvpUtils;

/**
 * @Author：淘跑
 * @Date: 2018/9/7  11:55
 * @Use： {@link ActivityDelegate} 默认实现类
 */
public class ActivityDelegateImpl implements ActivityDelegate {
    private Activity mActivity;

    private IActivity mIActivity;

    public ActivityDelegateImpl(@NonNull Activity activity) {
        mActivity = activity;
        mIActivity = (IActivity) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        //这里提供 AppComponent 对象给 BaseActivity 的子类, 用于 Dagger2 的依赖注入
        mIActivity.setupActivityComponent(MvpUtils.obtainAppComponentFromContext(mActivity));

    }


    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onDestroy() {

    }
}
