package com.taopao.interview.framework.dagger;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 10:31
 * @Use：
 */
public class Name {

    public Name(String name) {
        this.name = name;
    }

    private String name;
//
//    @Inject
//    public Name() {
//        this.name = "shen" + ":我是名";
//    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
