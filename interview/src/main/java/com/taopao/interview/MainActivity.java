package com.taopao.interview;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.taopao.interview.basics.BasicsActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.create();

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
