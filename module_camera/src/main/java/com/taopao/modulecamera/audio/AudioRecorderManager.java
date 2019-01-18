package com.taopao.modulecamera.audio;

/**
 * @Author：淘跑
 * @Date: 2019/1/17 14:30
 * @Use：
 */
public class AudioRecorderManager {

    private AudioRecorderManager() {
    }

    private static class SingletInstance {
        private static AudioRecorderManager instance = new AudioRecorderManager();
    }

    public static AudioRecorderManager getInstance() {
        return SingletInstance.instance;
    }

}
