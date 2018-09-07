package com.taopao.tiktok.test;

import com.taopao.tiktok.ui.mvp.IModel;
import com.taopao.tiktok.ui.mvp.IView;

/**
 * @Author：淘跑
 * @Date: 2018/9/7  16:09
 * @Use：
 */
public class MainContacts {

    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {


    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
    interface Model extends IModel {


    }
}
