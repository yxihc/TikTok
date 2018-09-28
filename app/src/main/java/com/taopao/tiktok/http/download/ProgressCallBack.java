package com.taopao.tiktok.http.download;

import android.os.Handler;
import android.util.Log;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @Author： 淘跑
 * @Date: 2018/7/5 11:43
 * @Use：
 */

public abstract class ProgressCallBack<T> {

    private String destFileDir; // 本地文件存放路径
    private String destFileName; // 文件名
    private Disposable mSubscription;
    private final Handler handler;

    public ProgressCallBack(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        handler = new Handler();
    }

    public abstract void onSuccess(T t);

    public abstract void progress(long progress, long total);

    public void onStart() {
    }

    public void onCompleted() {
    }

    public abstract void onError(Throwable e);

    public void saveFile(ResponseBody body) {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = body.byteStream();
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            //onCompleted();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                Log.e("saveFile", e.getMessage());
            }
        }
    }

}