package com.taopao.interview.framework.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @Author：淘跑
 * @Date: 2018/9/2 10:53
 * @Use：
 */
@Component(modules = UserModule.class, dependencies = ManComponent.class)
public interface UserComponent {
    User getUser();
}
