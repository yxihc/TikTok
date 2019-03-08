package com.taopao.commonsdk;

import com.blankj.utilcode.util.SPUtils;

/**
 * @Author：淘跑
 * @Date: 2019/1/7 17:16
 * @Use：
 */
public class AppSettingUtils {

    private static String spName = "AppSetting";

    /**
     * @return app是否是第一次启动
     */
    public static boolean isFirstStart() {
        return SPUtils.getInstance(spName).getBoolean("isFirstStart", true);
    }

    /**
     * 设置app是否是第一次启动
     *
     * @param isFirstStart
     */
    public static void setFirstStart(boolean isFirstStart) {
        SPUtils.getInstance(spName).put("isFirstStart", isFirstStart);
    }
}
