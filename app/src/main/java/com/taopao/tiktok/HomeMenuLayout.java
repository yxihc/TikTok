package com.taopao.tiktok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author：淘跑
 * @Date: 2018/12/29 14:57
 * @Use： 主页底部导航
 */
public class HomeMenuLayout extends FrameLayout {
    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.view_home)
    View mViewHome;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.tv_gz)
    TextView mTvGz;
    @BindView(R.id.view_gz)
    View mViewGz;
    @BindView(R.id.ll_gz)
    LinearLayout mLlGz;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.view_message)
    View mViewMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.tv_me)
    TextView mTvMe;
    @BindView(R.id.view_me)
    View mViewMe;
    @BindView(R.id.ll_me)
    LinearLayout mLlMe;
    private Context mContext;

    public HomeMenuLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public HomeMenuLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomeMenuLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_home_menu, this, true);
        ButterKnife.bind(inflate);
        initView();
    }

    private void initView() {

        ScaleAnimation s = new ScaleAnimation(1, 1, 1, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
        s.setDuration(10);
        s.setFillAfter(false);

        mViewGz.startAnimation(s);
        mViewMessage.startAnimation(s);
        mViewMe.startAnimation(s);
    }

    int mCheckId = 0;
    int mBeforeId = -1;

    @OnClick({R.id.ll_home, R.id.ll_gz, R.id.ll_message, R.id.ll_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                mBeforeId = mCheckId;
                mCheckId = 0;
                home(mTvHome, mViewHome);
                break;
            case R.id.ll_gz:
                mBeforeId = mCheckId;
                mCheckId = 1;
                home(mTvGz, mViewGz);
                break;
            case R.id.ll_message:
                mBeforeId = mCheckId;
                mCheckId = 2;
                home(mTvMessage, mViewMessage);
                break;
            case R.id.ll_me:
                mBeforeId = mCheckId;
                mCheckId = 3;
                home(mTvMe, mViewMe);
                break;
        }
    }


    private void home(View tv, View view) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.05f, 1.0f, 1.05f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setInterpolator(new OvershootInterpolator(2));
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);
        tv.startAnimation(scaleAnimation);


        ScaleAnimation s = new ScaleAnimation(1, 1, 0, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
        s.setDuration(500);
        s.setFillAfter(true);
        view.startAnimation(s);


        if (mBeforeId != -1 && mBeforeId != mCheckId) {
            switch (mBeforeId) {
                case 0:
                    noCheck(mTvHome, mViewHome);
                    break;
                case 1:
                    noCheck(mTvGz, mViewGz);
                    break;
                case 2:
                    noCheck(mTvMessage, mViewMessage);
                    break;
                case 3:
                    noCheck(mTvMe, mViewMe);
                    break;
            }
        }
    }

    public void noCheck(View tv, View view) {
        tv.animate().scaleX(1.05f)
                .scaleY(1.05f)
                .setDuration(500)
                .setInterpolator(new OvershootInterpolator(2))
                .start();

        ScaleAnimation s = new ScaleAnimation(1, 1, 1, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
        s.setDuration(500);
        s.setFillAfter(true);
        view.startAnimation(s);
    }

    /**
     * dp转px
     *
     * @param context 上下文
     * @param dpValue dp值
     * @return px值
     */
    private static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param context 上下文
     * @param spValue sp值
     * @return px值
     */
    private static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
