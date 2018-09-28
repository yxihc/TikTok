package com.taopao.tiktok.http;

/**
 * @Author： 淘跑
 * @Date: 2018/7/5 11:43
 * @Use：
 */
public class ResponseThrowable extends Exception {
    public int code;
    public String message;

    public ResponseThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
