package com.taopao.tiktok.tests.response;

import com.taopao.tiktok.tests.Api;

import java.io.Serializable;

/**
 * @Author：淘跑
 * @Date: 2018/9/28 0028 14:15
 * @Use：
 */
public class BaseResponse<T> implements Serializable {

    private int errorCode;
    private String errorMsg;
    private T data;


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }


    /**
     * 请求是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        if (errorCode == Api.RequestSuccess) {
            return true;
        } else {
            return false;
        }
    }
}