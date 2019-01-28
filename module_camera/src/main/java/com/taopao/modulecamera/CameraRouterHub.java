package com.taopao.modulecamera;

import com.taopao.commonsdk.RouterHub;

/**
 * @Author：淘跑
 * @Date: 2019/1/9 10:57
 * @Use：
 */
public interface CameraRouterHub {
    /**
     * 相机主界面
     * {@link CameraHomeActivity}
     */
    String CAMERA_LAUNCHERACTIVITY = RouterHub.CAMERA + "/CameraHomeActivity";
    /**
     * 录制界面
     * {@link AudioActivity}
     */
    String CAMERA_AUDIO_ACTIVITY = RouterHub.CAMERA + "/AudioActivity";
    /**
     * 权限申请界面
     * {@link CameraPersimmonActivity}
     */
    String CAMERA_PERSIMINON_ACTIVITY = RouterHub.CAMERA + "/CameraPersimmonActivity";
}
