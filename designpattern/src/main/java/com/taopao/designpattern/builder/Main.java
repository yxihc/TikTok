package com.taopao.designpattern.builder;

/**
 * @Author：淘跑
 * @Date: 2018/8/30 20:54
 * @Use： 建造者设计模式
 */
public class Main {
    public static void main(String[] strings) {
//
        String s = new Computer.Builder()
                .setCpu("i7-7700k")
                .setXk("GTX-2080")
                .setName("外星人")
                .setKeybord("黑寡妇")
                .create()
                .toString();

        System.out.println(s);
    }
}
