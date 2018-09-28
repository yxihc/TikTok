package com.taopao.interview.framework;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.taopao.interview.BaseActivity;
import com.taopao.interview.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxJava2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
 
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        int x = 3;
                        emitter.onNext(x);
                        Log.e(TAG, "After observeOn(mainThread)，Current thread is create" + Thread.currentThread().getName());
                    }
                }).subscribeOn(Schedulers.io())//指定的就是发射事件的线程
                        .observeOn(AndroidSchedulers.mainThread())//指定的就是订阅者接收事件的线程。
                        .doOnNext(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.e(TAG, "After observeOn(mainThread)，Current thread is  doOnNext" + Thread.currentThread().getName());
                            }
                        })
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.e(TAG, "After observeOn(mainThread)，Current thread is subscribe" + Thread.currentThread().getName());
                            }
                        });


                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
        }
    }
}
