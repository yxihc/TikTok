package com.taopao.cameraview.tool;

import android.content.Context;
import android.hardware.Camera;

import com.taopao.cameraview.RxCameraView;


public class RxCameraTool {

    private static Camera camera;

    /**
     * 打开闪光灯
     *
     * @return
     */
    public static void openFlashLight() {
        try {
            if (camera == null) {
                camera = Camera.open();
                camera.startPreview();
            }
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭闪光灯
     *
     * @return
     */
    public static void closeFlashLight() {
        try {
            if (camera == null) {

            } else {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameters);
                camera.release();
                camera = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void takePic(Context mContext, final RxCameraView mCameraView) {
    }

    public static void initCameraEvent(final Context mContext,
                                       final RxCameraView mCameraView,
                                       final byte[] data,
                                       final String fileDir,
                                       final String picName,
                                       final double mLongitude,
                                       final double mLatitude,
                                       final boolean isEconomize
    ) {
    }
}
