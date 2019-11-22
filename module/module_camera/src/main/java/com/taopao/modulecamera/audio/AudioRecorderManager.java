package com.taopao.modulecamera.audio;

import android.media.AudioRecord;
import android.provider.MediaStore;

/**
 * @Author：淘跑
 * @Date: 2019/1/17 14:30
 * @Use：
 */
public class AudioRecorderManager {
    private final AudioRecord mAudioRecord;

    private AudioRecorderManager() {

        //初始化
        mAudioRecord = new AudioRecord(0, 0, 0, 0, 0);


    }

    private static class SingletInstance {
        private static AudioRecorderManager instance = new AudioRecorderManager();
    }

    public static AudioRecorderManager getInstance() {
        return SingletInstance.instance;
    }


}
