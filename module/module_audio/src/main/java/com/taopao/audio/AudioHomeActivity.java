package com.taopao.audio;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.taopao.commonsdk.RouterHub;
import com.taopao.commonsdk.Utils;
import com.taopao.commonsdk.permission.PermissionSettingPage;
import com.taopao.commonsdk.permission.RequestPermissions;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

@Route(path = RouterHub.ADDIO_HOMEACTIVITY)
public class AudioHomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_home);
    }

    public void joinMain(View view) {
        RxPermissions rxPermissions = new RxPermissions(AudioHomeActivity.this);
        rxPermissions.requestEachCombined(RequestPermissions.CAMERA, RequestPermissions.RECORD_AUDIO, RequestPermissions.READ_EXTERNAL_STORAGE,
                RequestPermissions.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            //全部同意后调用
                            Utils.navigation(AudioHomeActivity.this, RouterHub.CAMERA_HOMEACTIVITY);
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            //只要有一个选择：禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                            new AlertDialog.Builder(AudioHomeActivity.this)
                                    .setMessage("您拒绝了权限，无法开启录制，是否重新申请")
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            joinMain(null);
                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).show();
                        } else {
                            //只要有一个选择：禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                            new AlertDialog.Builder(AudioHomeActivity.this)
                                    .setMessage(getResources().getString(R.string.per))
                                    .setPositiveButton("设置权限", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            PermissionSettingPage.start(AudioHomeActivity.this, true);
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
}
