package com.taopao.designpattern.strategy;

/**
 * @Author：淘跑
 * @Date: 2018/8/30 0030 17:12
 * @Use： 策略模式
 */
public class Main {
    public static void main(String[] strings) {
        Calc calc = new Calc();
        calc.setStrategy(new Add());
        double calc1 = calc.calc(10, 5);
        System.out.println(calc1);

    }


    //针对操作进行抽象
    public interface Strategy {
        public double calc(double a, double b);
    }


    public static class Add implements Strategy {
        @Override
        public double calc(double a, double b) {
            return b + a;
        }
    }

    public class Sub implements Strategy {
        @Override
        public double calc(double a, double b) {
            return a - b;
        }
    }

    public class Mul implements Strategy {
        @Override
        public double calc(double a, double b) {
            return a * b;
        }
    }

    public class Div implements Strategy {
        @Override
        public double calc(double a, double b) {
            return a / b;
        }
    }

    public static class Calc {
        private Strategy mStrategy;

        public void setStrategy(Strategy strategy) {
            mStrategy = strategy;
        }

        public double calc(double a, double b) {
            if (this.mStrategy == null) {
                throw new IllegalStateException("你还没有设置计算的策略");
            }
            return this.mStrategy.calc(a, b);
        }
    }

}
