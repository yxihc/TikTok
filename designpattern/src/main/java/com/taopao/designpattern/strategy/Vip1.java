package com.taopao.designpattern.strategy;

/**
 * @Author：淘跑
 * @Date: 2018/8/30 0030 17:15
 * @Use：
 */
public class Vip1 implements IVipCalculation {
    @Override
    public int money(int money) {

        int x = money / 1000 * 80;
        System.out.println("Vip1=" + x);
        return x;
    }
}
