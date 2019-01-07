package com.taopao.module_gank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.taopao.commonsdk.RouterHub;

@Route(path = RouterHub.GANK_HOMEACTIVITY)
public class GankHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_home);





    }
}
