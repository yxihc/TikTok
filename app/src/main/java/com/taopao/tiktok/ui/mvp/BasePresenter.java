package com.taopao.tiktok.ui.mvp;

import dagger.internal.Preconditions;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 17:11
 * @Use：
 */
public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {

    private IModel mModel;
    private IView mView;


    public BasePresenter(V view) {
        Preconditions.checkNotNull(view, "%s cannot be null", IView.class.getName());
        mView = view;
    }

    public BasePresenter(M model, V view) {
        Preconditions.checkNotNull(model, "%s cannot be null", IModel.class.getName());
        Preconditions.checkNotNull(view, "%s cannot be null", IView.class.getName());
        mModel = model;
        mView = view;
    }
}
