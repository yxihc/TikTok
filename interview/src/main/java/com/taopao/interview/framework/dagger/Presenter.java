package com.taopao.interview.framework.dagger;

import javax.inject.Inject;

/**
 * @Author：淘跑
 * @Date: 2018/9/6 21:09
 * @Use：
 */
public class Presenter {
    public View mView;
    public Model mModel;

    @Inject
    public Presenter(View view, Model model) {
        mView = view;
        mModel = model;
    }

    public void ete() {
        mView.se();
        mModel.set();
    }

}
