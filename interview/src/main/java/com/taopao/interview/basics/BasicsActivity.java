package com.taopao.interview.basics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.taopao.interview.BaseActivity;
import com.taopao.interview.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
//安卓基础知识学习
public class BasicsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics);
        ButterKnife.bind(this);

        Log.d("", "onCreate");

        Toast.makeText(BasicsActivity.this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("", "onNewIntent");
        Toast.makeText(BasicsActivity.this, "onNewIntent", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_basics, R.id.btn_jinjie})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_basics:
                startActivity(BasicsActivity.class);
                break;
            case R.id.btn_jinjie:
                break;
        }
    }
}
