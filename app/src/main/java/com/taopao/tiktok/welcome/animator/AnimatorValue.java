package com.taopao.tiktok.welcome.animator;

import android.animation.ObjectAnimator;

public interface AnimatorValue {
	public abstract ObjectAnimator getAnimator();
	public abstract ObjectAnimator getBeforeAnimator();
	public abstract void before(AnimatorValue beforeAnimatorValue);
}
