package com.taopao.tiktok.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.taopao.tiktok.ui.mvp.IPresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 16:40
 * @Use： Activity基类
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity {
    private Unbinder mUnbinder;
//    @Inject
//    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int viewResID = layoutResID();
        if (viewResID != 0) {
            setContentView(viewResID);
            //绑定到butterknife
            mUnbinder = ButterKnife.bind(this);
        } else {
            //没有设置界面 不setContentView()
        }
        initView();
        initData(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            //解绑ButterKnife
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }

    /**
     * 界面布局
     *
     * @return
     */
    @Override
    public int layoutResID() {
        return 0;
    }

    /**
     * view设置
     */
    @Override
    public void initView() {

    }

    /**
     * 数据设置
     *
     * @param savedInstanceState
     */
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
