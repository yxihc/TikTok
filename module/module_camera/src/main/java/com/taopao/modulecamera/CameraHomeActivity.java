package com.taopao.modulecamera;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.taopao.cameraview.RxCameraView;
import com.taopao.cameraview.tool.RxCameraTool;
import com.taopao.commonsdk.RouterHub;
import com.taopao.commonui.widget.CircleRecordView;
import com.taopao.commonui.widget.ThumbnailCountDownTimeView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = RouterHub.CAMERA_HOMEACTIVITY)
public class CameraHomeActivity extends AppCompatActivity {
    @BindView(R2.id.rxcameraview)
    RxCameraView mRxcameraview;
    @BindView(R2.id.record_start)
    CircleRecordView mRecordStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera_home);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (mRxcameraview != null) {
            mRxcameraview.addCallback(mCallback);
        }
        mRecordStart.setOnRecordChangeListener(new CircleRecordView.OnRecordChangeListener() {
            @Override
            public void onEventDown() {
                //按下录制
            }

            @Override
            public void onEventUp() {
                //松开录制
            }

            @Override
            public void onDontTouch() {

            }
        });
    }

    private Handler mBackgroundHandler;

    private Handler getBackgroundHandler() {
        if (mBackgroundHandler == null) {
            HandlerThread thread = new HandlerThread("background");
            thread.start();
            mBackgroundHandler = new Handler(thread.getLooper());
        }
        return mBackgroundHandler;
    }

    private RxCameraView.Callback mCallback
            = new RxCameraView.Callback() {
        @Override
        public void onCameraOpened(RxCameraView rxCameraView) {

        }

        @Override
        public void onCameraClosed(RxCameraView rxCameraView) {

        }

        @Override
        public void onPictureTaken(RxCameraView rxCameraView, byte[] data) {
            Log.d("rxCameraView", "onPictureTaken " + data.length);
            Toast.makeText(rxCameraView.getContext(), "sss", Toast.LENGTH_SHORT)
                    .show();
            getBackgroundHandler().post(new Runnable() {
                @Override
                public void run() {
                    File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                            "picture.jpg");
                    OutputStream os = null;
                    try {
                        os = new FileOutputStream(file);
                        os.write(data);
                        os.close();
                    } catch (IOException e) {
                        Log.w("rxCameraView", "Cannot write to " + file, e);
                    } finally {
                        if (os != null) {
                            try {
                                os.close();
                            } catch (IOException e) {
                                // Ignore
                            }
                        }
                    }
                }
            });
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            mRxcameraview.start();
        }
    }

    @Override
    protected void onPause() {
        mRxcameraview.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBackgroundHandler != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                mBackgroundHandler.getLooper().quitSafely();
            } else {
                mBackgroundHandler.getLooper().quit();
            }
            mBackgroundHandler = null;
        }
    }

    public void tackPhoto(View view) {

//        if (mRxcameraview != null) {
//            int facing = mRxcameraview.getFacing();
//            mRxcameraview.setFacing(facing == RxCameraView.FACING_FRONT ?
//                    RxCameraView.FACING_BACK : RxCameraView.FACING_FRONT);
//        }

        RxCameraTool.takePic(this, mRxcameraview);

//        if (mCameraView != null) {
//            mCurrentFlash = (mCurrentFlash + 1) % FLASH_OPTIONS.length;
//            item.setTitle(FLASH_TITLES[mCurrentFlash]);
//            item.setIcon(FLASH_ICONS[mCurrentFlash]);
//            mCameraView.setFlash(FLASH_OPTIONS[mCurrentFlash]);
//        }

    }

    public void cout(View view) {
        View showCountDown = LayoutInflater.from(this).inflate(R.layout.dialog_countdown, null);
        AlertDialog show = new AlertDialog.Builder(this)
                .setView(showCountDown)
                .show();
    }

}