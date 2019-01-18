package com.taopao.modulecamera;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.barlibrary.ImmersionBar;
import com.taopao.commonsdk.RouterHub;
import com.taopao.commonsdk.Utils;
import com.taopao.commonsdk.permission.PermissionSettingPage;
import com.taopao.commonsdk.permission.RequestPermissions;
import com.taopao.rxtoast.RxToast;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

@Route(path = CameraRouterHub.CAMERA_LAUNCHERACTIVITY)
public class CameraLauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_launcher);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .transparentNavigationBar()//透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .init();   //默认状态栏字体颜色为黑色
    }

    public void joinMain(View view) {
        RxPermissions rxPermissions = new RxPermissions(CameraLauncherActivity.this);
        rxPermissions.requestEachCombined(RequestPermissions.CAMERA, RequestPermissions.RECORD_AUDIO, RequestPermissions.READ_EXTERNAL_STORAGE,
                RequestPermissions.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            //全部同意后调用
                            Utils.navigation(CameraLauncherActivity.this, RouterHub.CAMERA_HOMEACTIVITY);
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            //只要有一个选择：禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                            RxToast.show("您拒绝了权限，无法开启相机");
                        } else {
                            //只要有一个选择：禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                            new AlertDialog.Builder(CameraLauncherActivity.this)
                                    .setMessage("您永久关闭了相机权限，请到设置页面手动开启")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            PermissionSettingPage.start(CameraLauncherActivity.this, true);
                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                        }
                    }
                });
    }

    public void audio(View view) {
        Utils.navigation(CameraLauncherActivity.this, CameraRouterHub.CAMERA_AUDIO_ACTIVITY);
    }
}
