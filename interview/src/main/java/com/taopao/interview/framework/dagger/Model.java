package com.taopao.interview.framework.dagger;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * @Author：淘跑
 * @Date: 2018/9/6 21:09
 * @Use：
 */
public class Model extends BaseModel {
    @Inject
    public Model() {

    }

    public void set() {
        Logger.d("Model");
    }
}
