package com.taopao.tiktok.tests;

import android.net.ParseException;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.taopao.tiktok.tests.response.BaseResponse;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

/**
 * @Author：淘跑
 * @Date: 018/9/19 0019 10:26
 * @Use：
 */
public abstract class RxErrorSubscriber<T> extends DisposableObserver<T> {
    @Override
    public void onComplete() {
        Log.e("RxErrorSubscriber", "=================================onComplete");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("RxErrorSubscriber", "=================================onStart");
//        if (!NetworkUtil.isNetworkAvailable(AppLifecyclesImpl.getContext())) {
//            TipsUtils.showShortTips("无网络，请检查网络设置");
//            onComplete();
//        }
    }

    @Override
    public void onNext(T t) {
        Log.e("RxErrorSubscriber", "=================================onNext");
        if (t instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) t;
            // 判断是否请求错误，出错直接转到onError()
            if (!baseResponse.isSuccess()) {
                if (baseResponse.getErrorCode() == Api.RequestFailure_login) {
                    onLoginFailure(baseResponse);
                }
                onShowTips(baseResponse);
                return;
            }
        }
        //显示加载成功的界面
        onResult(t);
    }

    /**
     * 提示错误信息
     * 如果不想提示就重写此方法
     *
     * @param baseResponse
     */
    public void onShowTips(BaseResponse baseResponse) {
//        MaterialDialogUtils.showLoginDialog(AppLifecyclesImpl.getContext(), "撒大声地");
        //普通错误只需显示Toast即可
        switch (baseResponse.getErrorCode()) {
            case 400://Bad Request 请求出现语法错误, 一般是请求参数不对
            case 404://Not Found 无法找到指定位置的资源
            case 403://Forbidden 资源不可用
            case 500://服务器内部错误, 请联系Java后台开发人员 !!!
            case 401://Unauthorized 访问被拒绝
            case 10000://服务器忙
//                TipsUtils.showShortTips(baseResponse.getErrorMsg());
                break;
            case Api.RequestFailure_login://登录失效 不处理
//                onLoginFailure(baseResponse);
                break;
            default:
//                TipsUtils.showShortTips(baseResponse.getErrorMsg());
        }
    }

    /**
     * 登录失效的回调
     * 不想弹出登录失效的dialog 想自己处理就重写此方法
     *
     * @param baseResponse
     */
    public void onLoginFailure(BaseResponse baseResponse) {

//        MaterialDialogUtils.showLogin(baseResponse.getErrorMsg());
    }

    public abstract void onResult(T t);

    @Override
    public void onError(Throwable t) {
        //这里不光只能打印错误, 还可以根据不同的错误做出不同的逻辑处理


        //这里只是对几个常用错误进行简单的处理, 展示这个类的用法, 在实际开发中请您自行对更多错误进行更严谨的处理
        String msg = "未知错误";
        if (t instanceof UnknownHostException) {
            msg = "网络不可用";
        } else if (t instanceof SocketTimeoutException) {
            msg = "请求网络超时";
        } else if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            msg = convertStatusCode(httpException);
        } else if (t instanceof JsonParseException || t instanceof ParseException || t instanceof JSONException || t instanceof JsonIOException) {
            msg = "数据解析错误";
        }
//        ArmsUtils.snackbarText(msg);
        Log.e("TAG", msg);
        Log.e("============", t.getMessage());
    }

    private String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() == 500) {
            msg = "服务器发生错误";
        } else if (httpException.code() == 404) {
            msg = "请求地址不存在";
        } else if (httpException.code() == 403) {
            msg = "请求被服务器拒绝";
        } else if (httpException.code() == 307) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}
