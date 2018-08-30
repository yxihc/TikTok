package com.taopao.designpattern.strategy;

/**
 * @Author：淘跑
 * @Date: 2018/8/30 0030 17:16
 * @Use：
 */
public class Vip2 implements IVipCalculation {
    @Override
    public int money(int money) {
        int x = money / 1000 * 60;
        System.out.println("Vip2=" + x);
        return x;
    }
}