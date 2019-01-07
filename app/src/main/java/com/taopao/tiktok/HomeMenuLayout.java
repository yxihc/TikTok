package com.taopao.tiktok;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
    @BindView(R.id.tv_gz)
    TextView mTvGz;
    @BindView(R.id.view_gz)
    View mViewGz;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.view_message)
    View mViewMessage;
    @BindView(R.id.tv_me)
    TextView mTvMe;
    @BindView(R.id.view_me)
    View mViewMe;
    @BindView(R.id.iv_home)
    ImageView mIvHome;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.iv_gz)
    ImageView mIvGz;
    @BindView(R.id.ll_gz)
    LinearLayout mLlGz;
    private Context mContext;
    private ScaleAnimation mViewNoChecked;
    private ScaleAnimation mTextChecked;
    private ScaleAnimation mViewChecked;
    private ScaleAnimation mNormalA;
    private Animation mRotateAnimation;

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

    /**
     * 设置加载中的图片
     *
     * @param imgId
     */
    public void setLoadingImgRes(@DrawableRes int imgId) {
        mIvHome.setImageResource(imgId);
        mIvGz.setImageResource(imgId);
    }

    private void initView() {
        if (mNormalA == null) {
            mNormalA = new ScaleAnimation(1, 1, 1, 0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
            mNormalA.setDuration(10);
            mNormalA.setFillAfter(true);
        }
        mViewGz.startAnimation(mNormalA);
        mViewMessage.startAnimation(mNormalA);
        mViewMe.startAnimation(mNormalA);
    }

    int mCheckId = 0;//当前选中的
    int mBeforeId = -1;//上一个选中的

    @OnClick({R.id.fl_home, R.id.fl_gz, R.id.fl_message, R.id.fl_me, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_home:
                mBeforeId = mCheckId;
                mCheckId = 0;
                checked(mTvHome, mViewHome);
                break;
            case R.id.fl_gz:
                mBeforeId = mCheckId;
                mCheckId = 1;
                checked(mTvGz, mViewGz);
                break;
            case R.id.fl_message:
                mBeforeId = mCheckId;
                mCheckId = 2;
                checked(mTvMessage, mViewMessage);
                break;
            case R.id.fl_me:
                mBeforeId = mCheckId;
                mCheckId = 3;
                checked(mTvMe, mViewMe);
                break;
            case R.id.iv_add:
                if (mOnHomeMenuClickListener != null) {
                    mOnHomeMenuClickListener.OnAddClick(null);
                }
                break;
        }
    }

    private void checkAnim(TextView tv, View view) {
        //设置字体颜色
        mTvHome.setTextColor(mContext.getResources().getColor(mCheckId == 0 ? R.color.white : R.color.gray));
        mTvGz.setTextColor(mContext.getResources().getColor(mCheckId == 1 ? R.color.white : R.color.gray));
        mTvMessage.setTextColor(mContext.getResources().getColor(mCheckId == 2 ? R.color.white : R.color.gray));
        mTvMe.setTextColor(mContext.getResources().getColor(mCheckId == 3 ? R.color.white : R.color.gray));
        //文字动画
        if (mTextChecked == null) {
            mTextChecked = new ScaleAnimation(1.0f, 1.05f, 1.0f, 1.05f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mTextChecked.setInterpolator(new OvershootInterpolator(2));
            mTextChecked.setDuration(300);
            mTextChecked.setFillAfter(false);
        }
        tv.startAnimation(mTextChecked);
        //文字下面横动画
        if (mViewChecked == null) {
            mViewChecked = new ScaleAnimation(1, 1, 0, 1.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
            mViewChecked.setDuration(300);
            mViewChecked.setFillAfter(true);
        }
        view.startAnimation(mViewChecked);
    }

    private void checked(TextView tv, View view) {
        if (mCheckId == 0 || mCheckId == 1) {
            if (mCheckId == mBeforeId && isRunning) {
                return;
            }
        } else {
            if (mCheckId == mBeforeId) {
                //下面就不要执行了
                return;
            }
        }
        checkAnim(tv, view);

        if (homeOrFellowLoading()) {
            return;
        }
        if (mOnHomeMenuClickListener != null) {
            mOnHomeMenuClickListener.onTabSelect(mCheckId);
        }
        //设置未点击状态
        if (mBeforeId != -1 && mBeforeId != mCheckId) {
            switch (mBeforeId) {
                case 0:
                    noChecked(mViewHome);
                    break;
                case 1:
                    noChecked(mViewGz);
                    break;
                case 2:
                    noChecked(mViewMessage);
                    break;
                case 3:
                    noChecked(mViewMe);
                    break;
            }
        }
    }

    private boolean homeOrFellowLoading() {
        if (mCheckId == 0 || mCheckId == 1) {
            if (mCheckId == mBeforeId || mBeforeId == -1) {
                if (mCheckId == 0) {
                    setImageAnim(mLlHome, mIvHome);
                } else if (mCheckId == 1) {
                    setImageAnim(mLlGz, mIvGz);
                }
                if (mOnHomeMenuClickListener != null) {
                    mOnHomeMenuClickListener.onTabReSelect(mCheckId);
                }
                return true;
            }
        }
        return false;
    }

    boolean isRunning = false;

    private void setImageAnim(View linearlayout, View imgview) {
        if (isRunning) {
            return;
        }
        if (mRotateAnimation == null) {
            mRotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            mRotateAnimation.setFillAfter(false); // 设置保持动画最后的状态
            mRotateAnimation.setDuration(600); // 设置动画时间
            mRotateAnimation.setRepeatCount(-1);
            mRotateAnimation.setInterpolator(new OvershootInterpolator()); // 设置插入器
            mRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    Log.e("setAnimationListener", "onAnimationStart");
                    isRunning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    isRunning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    Log.e("setAnimationListener", "onAnimationRepeat");
                }
            });
        }
        linearlayout.setVisibility(INVISIBLE);
        imgview.setVisibility(VISIBLE);
        imgview.startAnimation(mRotateAnimation);
    }

    /**
     * 关闭首页加载
     */
    public void setHomeLoadingFinish() {
        mLlHome.setVisibility(VISIBLE);
        mIvHome.setVisibility(INVISIBLE);
        if (mRotateAnimation != null) {
            mRotateAnimation.cancel();
        }
    }

    /**
     * 关闭关注页面加载
     */
    public void setFollowLoadingFinish() {
        mLlGz.setVisibility(VISIBLE);
        mIvGz.setVisibility(INVISIBLE);
        if (mRotateAnimation != null) {
            mRotateAnimation.cancel();
        }
    }

    public void noChecked(View view) {
        if (mViewNoChecked == null) {
            mViewNoChecked = new ScaleAnimation(1, 1, 1, 0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
            mViewNoChecked.setDuration(300);
            mViewNoChecked.setFillAfter(true);
        }
        view.startAnimation(mViewNoChecked);
    }

    OnHomeMenuItemClickListener mOnHomeMenuClickListener;

    public void setOnHomeMenuClickListener(OnHomeMenuItemClickListener onHomeMenuItemClickListener) {
        mOnHomeMenuClickListener = onHomeMenuItemClickListener;
    }

    interface OnHomeMenuItemClickListener {
        void onTabSelect(int postion);

        void onTabReSelect(int postion);

        void OnAddClick(View view);
    }

    boolean isOpen = true;

    public void setLoadingOpen(boolean isOpen) {
        this.isOpen = isOpen;
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
