package com.taopao.videostudy;

import android.app.Activity;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.IOException;

public class CameraActivity extends AppCompatActivity {
    private SurfaceView surfaceview;
    private Camera camera;
    private boolean isPreview;
    private boolean isRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initView();
    }

    private void initView() {
        surfaceview = (SurfaceView) findViewById(R.id.surfaceview);
        surfaceview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    // Camera,open() 默认返回的后置摄像头信息
                    camera = Camera.open();
                    //此处也可以设置摄像头参数
                    Camera.Parameters parameters = camera.getParameters();//得到摄像头的参数
                    parameters.setJpegQuality(100);//设置照片的质量
                    parameters.setPreviewSize(1920, 1080);//设置预览尺寸
                    parameters.setPictureSize(1920, 1080);//设置照片尺寸
                    parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 连续对焦模式
                    camera.setParameters(parameters);

                    //设置角度，此处 CameraId 我默认 为 0 （后置）
                    // CameraId 也可以 通过 参考 Camera.open() 源码 方法获取
                    setCameraDisplayOrientation(CameraActivity.this, 0, camera);
                    camera.setPreviewDisplay(holder);//通过SurfaceView显示取景画面
                    camera.startPreview();//开始预览
                    isPreview = true;//设置是否预览
                } catch (IOException e) {
                    Log.e("surfaceCreated", e.toString());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (camera != null) {
                    if (isPreview) {//正在预览
                        camera.stopPreview();
                        camera.release();
                    }
                }
            }
        });
    }


    /**
     * 设置 摄像头的角度
     *
     * @param activity 上下文
     * @param cameraId 摄像头ID（假如手机有N个摄像头，cameraId 的值 就是 0 ~ N-1）
     * @param camera   摄像头对象
     */
    public static void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera) {
        Camera.CameraInfo info = new Camera.CameraInfo();
        //获取摄像头信息
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        //获取摄像头当前的角度
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            // 前置摄像头
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else {
            // 后置摄像头
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }


}