package com.taopao.tiktok;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.barlibrary.ImmersionBar;
import com.jess.arms.utils.ArmsUtils;
import com.taopao.commonsdk.AppSettingUtils;
import com.taopao.commonsdk.RouterHub;
import com.taopao.commonsdk.Utils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

@Route(path = RouterHub.APP_SPLASHACTIVITY)
public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.iv_launcher_icon)
    ImageView mIvLauncherIcon;
    @BindView(R.id.iv_launcher_name)
    TextView mIvLauncherName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .transparentNavigationBar()//透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
                .init();   //默认状态栏字体颜色为黑色
        initStartAnim();
        AppSettingUtils.setFirstStart(false);
        if (AppSettingUtils.isFirstStart()) {
            //进入第一次欢迎页
        } else {
            //跳转主页
            Observable.timer(2, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            Utils.navigation(SplashActivity.this, RouterHub.APP_HOMEACTIVITY);
                            finish();
                        }
                    });
        }
    }

    private static final int ANIM_TIME = 1000;

    /**
     * 启动动画
     */
    private void initStartAnim() {
        // 渐变展示启动屏
//        AlphaAnimation aa = new AlphaAnimation(0.4f, 1.0f);
//        aa.setDuration(ANIM_TIME * 2);
//        aa.setAnimationListener(this);
//        mImageView.startAnimation(aa);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(ANIM_TIME);
        mIvLauncherIcon.startAnimation(sa);

        RotateAnimation ra = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(ANIM_TIME);
        mIvLauncherName.startAnimation(ra);

    }
}