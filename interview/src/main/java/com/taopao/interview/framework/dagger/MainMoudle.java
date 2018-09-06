package com.taopao.interview.framework.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * @Author：淘跑
 * @Date: 2018/9/6 21:11
 * @Use：
 */
@Module
public class MainMoudle {
    @Provides
    View providesView() {
        return new View();
    }

    @Provides
    BaseModel providesModel(Model model) {
        return model;
    }


//    @Provides
//    Model providesModel() {
//        return new Model();
//    }
}
