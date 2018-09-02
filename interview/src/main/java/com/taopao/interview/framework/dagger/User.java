package com.taopao.interview.framework.dagger;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import dagger.Lazy;
import dagger.Provides;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 10:30
 * @Use：
 */
public class User {

    @Inject
    public Sex mSex;

    @Inject
    public Name mName;

    @Inject
    @Named("provideString1")
    public String mString;

    @Inject
    @Named("provideString2")
    public String mString2;

    //测试懒加载,只有再用刀的时候才会去初始化
    @Inject
    @Named("TestLazy")
    Lazy<String> name;

    //应用 @Singleton 的时候，我们希望每次都是获取同一个对象，
    // 但有的时候，我们希望每次都创建一个新的实例，这种情况显然与 @Singleton 完全相反。
    // Dagger2 通过 Provider 就可以实现。它的使用方法和 Lazy 很类似。
    @Inject
    Provider<Integer> randomValue;


    @Inject
    public User() {
    }

    public void TestLazy() {
        Logger.d(name.get());
    }

    public void waiteString() {
        String s;
        if (mSex != null) {
            s = mSex.toString() + mString + "    :" + mString2;
        } else {
            s = mName.toString() + mString + "    :" + mString2;
        }
        Logger.d(s);
    }

}
