package com.taopao.tiktok.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.taopao.tiktok.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author：淘跑
 * @Date: 2018/11/27 10:05
 * @Use： dialog基类
 */
public abstract class BaseDialog extends Dialog implements DialogInterface.OnDismissListener {
    private Context mContext;
    public View mRootView;
    private Unbinder mBind;

    public BaseDialog(Context context) {
        super(context, R.style.Transparent_Dialog);
        init(context);
    }

    public void init(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(getContentResId(), null);
        mBind = ButterKnife.bind(this, mRootView);
        this.setOnDismissListener(this);
        setContentView(mRootView);
        initView();
        initAnimation();
    }

    public void initAnimation() {
//        Window window = getWindow();
//        window.setWindowAnimations(R.style.Dialog_Scale_Animation);
//        window.setGravity(Gravity.CENTER);
    }

    public abstract int getContentResId();

    public void initView() {

    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {

    }

    @Override
    protected void onStop() {
        super.onStop();
//        mBind.unbind();
    }


}