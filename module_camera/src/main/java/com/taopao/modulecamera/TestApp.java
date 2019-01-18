package com.taopao.modulecamera;

import android.app.Application;
import android.view.Gravity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.Utils;
import com.taopao.rxtoast.RxToast;

/**
 * @Author：淘跑
 * @Date: 2019/1/9 11:06
 * @Use：
 */
public class TestApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        RxToast.init(this)
                .setBackgroundColor("#CC000000")
                .setTextColor("#FFFFFF")
                .setGravity(Gravity.CENTER)
                .setPadding(16, 16, 12, 12)
                .setMaxLines(2)
                .setTextSize(12)
                .setZ(30)
                .setMaxLines(3)
                .apply();

        Utils.init(this);


    }
}
