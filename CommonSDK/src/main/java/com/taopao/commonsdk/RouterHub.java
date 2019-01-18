package com.taopao.commonsdk;

public interface RouterHub {
    /**
     * 组名
     */
    String APP = "/app";//宿主 App 组件
    String ZHIHU = "/zhihu";//知乎组件
    String GANK = "/gank";//干货集中营组件
    String CAMERA = "/camera";//相机分组组件

    /**
     * 宿主 App 分组
     */
    String APP_SPLASHACTIVITY = APP + "/SplashActivity";
    String APP_HOMEACTIVITY = APP + "/HomeActivity";
    String APP_MAINACTIVITY = APP + "/MainActivity";
    /**
     * 知乎分组
     */
    String ZHIHU_HOMEACTIVITY = ZHIHU + "/ZhiHuHomeActivity";
    /**
     * 干货集中营分组
     */
    String GANK_HOMEACTIVITY = GANK + "/GankHomeActivity";

    /**
     * 相机分组
     */
    String CAMERA_HOMEACTIVITY = CAMERA + "/CameraHomeActivity";

}
