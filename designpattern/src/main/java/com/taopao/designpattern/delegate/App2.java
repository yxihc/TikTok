package com.taopao.designpattern.delegate;

import java.util.ArrayList;

/**
 * @Author：淘跑
 * @Date: 2018/9/28 0028 15:16
 * @Use：
 */
public class App2 {
    public void inject(ArrayList<String> arrayList) {
//        System.out.println("App2 arrayList"+arrayList.hashCode());
        arrayList.add("222222");
    }


    public void Add(Integer x) {
        x=5;
//        System.out.println(x);

        System.out.println("App2 x"+x.hashCode());
    }
}
