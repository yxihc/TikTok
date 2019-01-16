package com.taopao.tiktok.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.taopao.rxtoast.RxToast;
import com.taopao.tiktok.BuildConfig;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * ================================================
 * 展示 {@link AppLifecycles} 的用法
 * <p>
 * Created by JessYan on 04/09/2017 17:12
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class AppLifecyclesImpl implements AppLifecycles {

    @Override
    public void attachBaseContext(@NonNull Context base) {
//          MultiDex.install(base);  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(@NonNull Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        //leakCanary内存泄露检查
        ArmsUtils.obtainAppComponentFromContext(application).extras().put(RefWatcher.class.getName(), BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);

        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
        //bugly
        CrashReport.initCrashReport(application, "c6dc9a5939", true);

        RxToast.init(application)
                .setBackgroundColor("#CC000000")
                .setTextColor("#FFFFFF")
                .setGravity(Gravity.CENTER)
                .setPadding(16, 16, 12, 12)
                .setMaxLines(2)
                .setTextSize(12)
                .setZ(30)
                .setMaxLines(3)
                .apply();
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
