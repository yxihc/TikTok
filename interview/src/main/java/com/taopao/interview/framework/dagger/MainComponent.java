package com.taopao.interview.framework.dagger;

import dagger.Component;

/**
 * @Author：淘跑
 * @Date: 2018/9/6 21:08
 * @Use：
 */
@Component(modules = MainMoudle.class)
public interface MainComponent {
    void inject(DaggerActivity daggerActivity);
}
