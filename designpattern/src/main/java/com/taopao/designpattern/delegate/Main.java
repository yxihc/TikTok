package com.taopao.designpattern.delegate;

import com.taopao.designpattern.builder.Computer;

import java.util.ArrayList;

/**
 * @Author：淘跑
 * @Date: 2018/9/8 11:24
 * @Use：
 */
public class Main {

    public static void main(String[] strings) {
        ArrayList<String> strings1 = new ArrayList<>();

//        AppImpl app = new AppImpl();
//        app.inject(strings1);

        APP1 app1 = new APP1();
        app1.inject(strings1);

        for (String s : strings1) {
            System.out.println(s);
        }
        //输出666
    }

    public interface App {
        void inject(ArrayList<String> arrayList);
    }

    public static class AppImpl implements App {
        @Override
        public void inject(ArrayList<String> arrayList) {
            arrayList.add("666");
        }
    }

    public static class APP1 {
        public void inject(ArrayList<String> arrayList) {
            arrayList.add("666");
        }
    }
}
