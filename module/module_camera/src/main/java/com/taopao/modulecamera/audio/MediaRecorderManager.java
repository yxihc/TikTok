package com.taopao.modulecamera.audio;

import android.media.MediaRecorder;
import android.util.Log;

import com.blankj.utilcode.util.FileUtils;
import com.taopao.rxtoast.RxToast;

import java.io.File;
import java.io.IOException;

/**
 * @Author：淘跑
 * @Date: 2019/1/17 14:13
 * @Use：
 */
public class MediaRecorderManager {
    private MediaRecorder mMediaRecorder;

    private boolean isStart = false;

    public String mFilePath = "";

    private MediaRecorderManager() {
    }

    private static class SingletInstance {
        private static MediaRecorderManager instance = new MediaRecorderManager();
    }

    public static MediaRecorderManager getInstance() {
        return SingletInstance.instance;
    }

    public void start(String filePath, String fileName) {
        // 开始录音
        /* ①Initial：实例化MediaRecorder对象 */
        if (mMediaRecorder == null)
            mMediaRecorder = new MediaRecorder();
        try {
            /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
            /*
             * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
             * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

            FileUtils.createOrExistsDir(filePath);


            File file = new File(filePath);
            if (file.exists()) {
                if (!file.isDirectory()) {
                    file.mkdirs();
                }
            } else {
                file.mkdirs();
            }

            mFilePath = filePath + "/" + fileName;
            /* ③准备 */
            mMediaRecorder.setOutputFile(mFilePath);
            mMediaRecorder.prepare();
            /* ④开始 */
            mMediaRecorder.start();
            isStart = true;

        } catch (IllegalStateException e) {
            Log.d("-----------", "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        } catch (IOException e) {
            Log.d("-----------", "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        }
    }

    public void stop() {
        if (!isStart) {
            RxToast.show("还未开始录制");
        }
        try {
            mMediaRecorder.stop();
            mMediaRecorder.release();
            mMediaRecorder = null;
            mFilePath = "";
        } catch (RuntimeException e) {
            Log.d("-----------", e.toString());
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            File file = new File(mFilePath);
            if (file.exists())
                file.delete();
            mFilePath = "";
        } finally {
            isStart = false;
        }
    }


}
