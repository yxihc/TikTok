package com.taopao.interview.framework;

import android.os.Bundle;
import android.view.View;

import com.taopao.interview.BaseActivity;
import com.taopao.interview.R;
import com.taopao.interview.framework.dagger.DaggerActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

//开源框架学习
public class FrameworkActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_framework);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_dagger, R.id.btn_rxjava})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dagger:
                startActivity(DaggerActivity.class);
                break;
            case R.id.btn_rxjava:
                startActivity(RxJava2Activity.class);
                break;
        }
    }
}
