package com.taopao.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * @Author：淘跑
 * @Date: 2018/8/25 13:53
 * @Use：
 */
public class BaseActivity extends AppCompatActivity {

    public String TAG = this.getClass().getName();


    public void startActivity(Class clz) {
        startActivity(new Intent(this, clz));
    }

}
