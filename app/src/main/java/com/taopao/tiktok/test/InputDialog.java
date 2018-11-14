package com.taopao.tiktok.test;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.taopao.tiktok.R;

/**
 * @Author：淘跑
 * @Date: 2018/11/14 17:20
 * @Use：
 */
public class InputDialog extends Dialog {
    public InputDialog(@NonNull Context context) {
        super(context);
//        super(context, R.style.CustomDialog);
        init(context);
    }

    public Context mContext;
    public View mRootView;

    public void init(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.dialog_input, null);
        setContentView(mRootView);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        window.setGravity(Gravity.BOTTOM);
    }

}
