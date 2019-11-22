package com.taopao.modulecamera;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = CameraRouterHub.CAMERA_PERSIMINON_ACTIVITY)
public class CameraPersimmonActivity extends AppCompatActivity {
    @BindView(R.id.tv_camera)
    TextView mTvCamera;
    @BindView(R.id.tv_audio)
    TextView mTvAudio;
    @BindView(R.id.tv_sdcard)
    TextView mTvSdcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_persimmon);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_camera, R.id.tv_audio, R.id.tv_sdcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_camera:
                RxPermissions rxPermissions = new RxPermissions(this);
//                rxPermissions.req
                break;
            case R.id.tv_audio:
                break;
            case R.id.tv_sdcard:
                break;
        }
    }
}
