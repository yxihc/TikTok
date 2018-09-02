package com.taopao.interview;

import android.os.Bundle;
import android.view.View;

import com.taopao.interview.basics.BasicsActivity;
import com.taopao.interview.framework.FrameworkActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_basics, R.id.btn_jinjie, R.id.btn_framework})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_basics://基础知识
                startActivity(BasicsActivity.class);
                break;
            case R.id.btn_jinjie://进阶知识
                break;
            case R.id.btn_framework://第三方框架
                startActivity(FrameworkActivity.class);
                break;
        }
    }
}
