package com.taopao.commonsdk;

public interface RouterHub {
    /**
     * 组名
     */
    String APP = "/app";//宿主 App 组件
    String AUDIO = "/audio";//录音分组
    String CAMERA = "/camera";//相机分组组件
    /**
     * 宿主 App 分组
     */
    String APP_SPLASHACTIVITY = APP + "/SplashActivity";
    String APP_HOMEACTIVITY = APP + "/HomeActivity";
    String APP_MAINACTIVITY = APP + "/MainActivity";

    /**
     * 相机分组 主界面
     */
    String CAMERA_HOMEACTIVITY = CAMERA + "/CameraHomeActivity";

    /**
     * 录音分组 主界面
     */
    String ADDIO_HOMEACTIVITY = AUDIO + "/AudioHomeActivity";


}
