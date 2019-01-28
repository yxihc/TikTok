package com.taopao.commonui.widget.music;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.taopao.commonui.DPPXUtil;
import com.taopao.commonui.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicalNoteLayout extends RelativeLayout implements WeakWrapperHandler.MessageHandler {

    private static final int DEFAULT_MUSIC_VIEW_SIZE = 45;//dp

    private static final int MSG_WHAT_ROTATION = 0;
    private static final int MSG_WHAT_ADD_NOTE = 1;
    private static final int DEFAULT_ROTATION_SPEED = 50;
    private static final int DEFAULT_ADD_NOTE_SPEED = 1500;

    // layout的宽高
    private int mWidth, mHeight;
    // 音符Drawable集合
    private List<Drawable> mDrawables;
    // 音符的宽高
    private int mDrawableWidth, mDrawableHeight;
    // 音符LayoutParams
    private LayoutParams mNoteParams;
    // 转圈的音乐view
    private CircleMusicView mMusicView;

    private int mIndex;
    private Random mRandom = new Random();

    private WeakWrapperHandler mAnimateHandler;

    public MusicalNoteLayout(Context context) {
        this(context, null);
    }

    public MusicalNoteLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MusicalNoteLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        // test code
        start(true);
    }

    private void init(Context context, AttributeSet attrs) {
        mAnimateHandler = new WeakWrapperHandler(this);
        int defaultSize = DPPXUtil.dip2px(getContext(), DEFAULT_MUSIC_VIEW_SIZE);
        int musicViewSize;
        Drawable musicViewRes;
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MusicalNoteLayout, 0, 0);
        try {
            musicViewSize = ta.getDimensionPixelOffset(R.styleable.MusicalNoteLayout_circle_music_view_size,
                    defaultSize);
            musicViewRes = ta.getDrawable(R.styleable.MusicalNoteLayout_circle_music_view_res);
        } finally {
            ta.recycle();
        }
        mMusicView = new CircleMusicView(getContext());
        if (musicViewRes != null) {
            mMusicView.setImageDrawable(musicViewRes);
        }
        mMusicView.setId(R.id.music_view_id);
        LayoutParams params = new LayoutParams(musicViewSize, musicViewSize);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(ALIGN_PARENT_RIGHT);
        params.rightMargin = DPPXUtil.dip2px(getContext(), 10);
        params.bottomMargin = DPPXUtil.dip2px(getContext(), 25);
        addView(mMusicView, params);

        Drawable note1 = getResources().getDrawable(R.drawable.note1);
        Drawable note2 = getResources().getDrawable(R.drawable.note2);
        mDrawables = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mDrawables.add(i == 0 ? note1 : note2);
        }

        mDrawableHeight = note1.getIntrinsicHeight() / 2;
        mDrawableWidth = note1.getIntrinsicWidth() / 2;

        mNoteParams = new LayoutParams(mDrawableWidth, mDrawableHeight);
        mNoteParams.addRule(ALIGN_PARENT_BOTTOM);
        mNoteParams.addRule(ALIGN_PARENT_RIGHT);
        mNoteParams.rightMargin = params.rightMargin + musicViewSize / 2 - mDrawableWidth / 2;
        mNoteParams.bottomMargin = params.bottomMargin - mDrawableHeight;
    }

    public void start(boolean start) {
        mAnimateHandler.removeMessages(MSG_WHAT_ROTATION);
        mAnimateHandler.removeMessages(MSG_WHAT_ADD_NOTE);
        if (start) {
            mAnimateHandler.sendEmptyMessage(MSG_WHAT_ROTATION);
            mAnimateHandler.sendEmptyMessage(MSG_WHAT_ADD_NOTE);
        }
    }

    public void setImageResource(int resId) {
        mMusicView.setImageResource(resId);
    }

    public void setImageBitmap(Bitmap bm) {
        mMusicView.setImageBitmap(bm);
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == MSG_WHAT_ROTATION) {
            mMusicView.startRotate();
            mAnimateHandler.sendEmptyMessageDelayed(MSG_WHAT_ROTATION,
                    DEFAULT_ROTATION_SPEED);
        } else if (msg.what == MSG_WHAT_ADD_NOTE) {
            ++mIndex;
            if (mIndex >= mDrawables.size()) {
                mIndex = 0;
            }
            addMusicNote(mIndex);
            mAnimateHandler.sendEmptyMessageDelayed(MSG_WHAT_ADD_NOTE,
                    DEFAULT_ADD_NOTE_SPEED);
        }
    }

    private void addMusicNote(int index) {
        ImageView noteView = new ImageView(getContext());
        noteView.setImageDrawable(mDrawables.get(index));
        addView(noteView, mNoteParams);
        Animator set = getFinalAnimator(noteView);
        set.addListener(new AnimEndListener(noteView));
        set.start();
    }

    private Animator getFinalAnimator(View target) {
        AnimatorSet set = getEnterAnimator(target);
        ValueAnimator bezierValueAnimator = getBezierValueAnimator(target);
        AnimatorSet finalSet = new AnimatorSet();
        finalSet.playTogether(set, bezierValueAnimator);
        finalSet.setInterpolator(new AccelerateInterpolator());
        finalSet.setTarget(target);
        return finalSet;
    }

    // 出场动画
    private AnimatorSet getEnterAnimator(final View target) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0.1f, 1f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.0f, 1f);
        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(1000);
        enter.setInterpolator(new AccelerateInterpolator());
        enter.playTogether(alpha, scaleX, scaleY);

        AnimatorSet before = new AnimatorSet();
        // 同时在正负25f的角度上做随机旋转
        ObjectAnimator rotate = ObjectAnimator.ofFloat(target, View.ROTATION, 0.0f, mRandom.nextInt(50) - 25.5f);
        rotate.setDuration(2000);
        before.playSequentially(enter, rotate);
        return before;
    }

    // 贝塞尔曲线轨迹动画
    private ValueAnimator getBezierValueAnimator(View target) {
        // 中点
        BezierEvaluator evaluator = new BezierEvaluator(new PointF(0f, mHeight - mHeight / 4),
                new PointF(0f, mHeight - mHeight / 2));
        // 终点在0
        // 起点定在音符的左上角坐标
        ValueAnimator animator = ValueAnimator.ofObject(evaluator,
                new PointF(mMusicView.getX() + mMusicView.getWidth() / 2 - mDrawableHeight / 2,
                        mMusicView.getBottom()),
                new PointF(mMusicView.getLeft() / 2, mMusicView.getTop() - mMusicView.getHeight() / 2));
        animator.addUpdateListener(new BezierListener(target));
        animator.setDuration(4000);
        return animator;
    }

    private class BezierListener implements ValueAnimator.AnimatorUpdateListener {

        private View target;

        public BezierListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator va) {
            PointF pointF = (PointF) va.getAnimatedValue();
            target.setX(pointF.x);
            target.setY(pointF.y);
            target.setAlpha(1 - va.getAnimatedFraction());
        }
    }

    private class AnimEndListener extends AnimatorListenerAdapter {

        private View target;

        public AnimEndListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeView((target));
        }
    }
}
