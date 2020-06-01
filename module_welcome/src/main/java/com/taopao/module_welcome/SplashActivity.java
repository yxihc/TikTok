package com.taopao.module_welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.taopao.module_welcome.databinding.ActivitySplashBinding;


public class SplashActivity extends AppCompatActivity {
    private com.taopao.module_welcome.databinding.ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
        mBinding= ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .transparentNavigationBar()//透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .init();   //默认状态栏字体颜色为黑色

        if (AppSettingUtils.isFirstStart()) {
            //进入第一次欢迎页
            startActivity(new Intent(this,WelcomeActivity.class));
            finish();
        } else {
            initStartAnim();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));

                }
            },2000);
        }
    }

    private Handler mHandler=new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {

        }
    };
    private static final int ANIM_TIME = 1000;

    /**
     * 启动动画
     */
    private void initStartAnim() {

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(ANIM_TIME);
        mBinding.ivLauncherIcon.startAnimation(sa);

        RotateAnimation ra = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(ANIM_TIME);
        mBinding.ivLauncherName.startAnimation(ra);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}