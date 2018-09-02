package com.taopao.interview.framework.dagger;

import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.taopao.interview.BaseActivity;
import com.taopao.interview.R;

import javax.inject.Inject;

public class DaggerActivity extends BaseActivity {
    @Inject
    public TestSingleton mTestSingleton;
    @Inject
    public TestSingleton mTestSingleton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        User user = DaggerUserComponent.builder()
                .build()
                .getUser();
        user.waiteString();
        user.TestLazy();

        DaggerTestSingleton_ActivityComponent.builder().build().inject(this);

        Logger.d(mTestSingleton.toString());
        Logger.d(mTestSingleton1.toString());
    }
}
