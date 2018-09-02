package com.taopao.interview.framework.dagger;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 11:57
 * @Use：
 */
@Module
public class UserModule {
    @Provides
    public Sex provideSex() {
        return new Sex("男");
    }

    @Provides
    public Name provideName() {
        return new Name("沈");
    }

    @Named("provideString1")
    @Provides
    public String provideString1() {
        return "第一个String变量";
    }

    @Named("provideString2")
    @Provides
    public String provideString2() {
        return "第二个String变量";
    }

    @Named("TestLazy")
    @Provides
    public String provideTestLazy() {
        return "测试懒加载,只有再用刀的时候才会去初始化";
    }

    @Provides
    public int provideInt() {
        return 100;
    }

}
