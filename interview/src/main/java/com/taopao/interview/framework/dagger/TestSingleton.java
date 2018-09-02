package com.taopao.interview.framework.dagger;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 15:36
 * @Use：
 */
@Singleton
public class TestSingleton {

    @Inject
    public TestSingleton() {

    }

    @Override
    public String toString() {
        return "TestSingleton{}" + hashCode();
    }

    @Singleton
    @Component
    public interface ActivityComponent {
        void inject(DaggerActivity activity);
    }
}
