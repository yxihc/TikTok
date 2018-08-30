package com.taopao.designpattern.builder;

/**
 * @Author：淘跑
 * @Date: 2018/8/30 20:56
 * @Use： 建造者设计模式
 */
public class Computer {
    public String name;
    public String cpu;
    public String xk;

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", cpu='" + cpu + '\'' +
                ", xk='" + xk + '\'' +
                ", keybord='" + keybord + '\'' +
                '}';
    }

    public String keybord;

    public Computer(ComputerParams params) {
        name = params.name;
        cpu = params.cpu;
        xk = params.xk;
        keybord = params.keybord;
    }

    public static class Builder {

        private ComputerParams mParams;

        public Builder() {
            mParams = new ComputerParams();
        }

        public Builder setName(String name) {
            mParams.name = name;
            return this;
        }

        public Builder setCpu(String cpu) {
            mParams.cpu = cpu;
            return this;
        }

        public Builder setXk(String xk) {
            mParams.xk = xk;
            return this;
        }

        public Builder setKeybord(String keybord) {
            mParams.keybord = keybord;
            return this;
        }

        public Computer create() {
            return new Computer(mParams);
        }
    }


    public static class ComputerParams {
        public String name;
        public String cpu;
        public String xk;
        public String keybord;
    }
}
