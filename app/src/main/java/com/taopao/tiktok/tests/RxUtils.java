package com.taopao.tiktok.tests;

import android.content.Context;

import com.taopao.tiktok.http.ExceptionHandle;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author：淘跑
 * @Date: 2018/9/28 0028 14:30
 * @Use：
 */
public class RxUtils {


    /**
     * 线程调度器
     */
    public static <T> ObservableTransformer<T, T> schedulersTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .doOnNext(new Consumer<T>() {
                            @Override
                            public void accept(T t) throws Exception {

                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
