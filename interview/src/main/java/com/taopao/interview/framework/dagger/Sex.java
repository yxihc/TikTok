package com.taopao.interview.framework.dagger;

import javax.inject.Inject;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 10:30
 * @Use： 性别
 */
public class Sex {
    public Sex(String sex) {
        mSex = sex;
    }

    private String mSex;

//    @Inject
//    public Sex() {
//        this.mSex = "nv";
//    }

    @Override
    public String toString() {
        return "Sex{" +
                "mSex='" + mSex + '\'' +
                '}';
    }
}
