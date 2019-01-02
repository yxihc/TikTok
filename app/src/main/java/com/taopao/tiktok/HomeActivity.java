package com.taopao.tiktok;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.homemenu)
    HomeMenuLayout mHomemenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0:
                    mHomemenu.setHomeLoadingFinish();
                    break;
                case 1:
                    mHomemenu.setFollowLoadingFinish();
                    break;
            }
        }
    };

    private void initView() {
        mHomemenu.setOnHomeMenuClickListener(new HomeMenuLayout.OnHomeMenuItemClickListener() {
            @Override
            public void onTabSelect(int postion) {
                Log.d("mHomemenu", "onTabSelect: " + postion);
            }

            @Override
            public void onTabReSelect(int postion) {
                Log.d("mHomemenu", "onTabReSelect: " + postion);
                mHandler.sendEmptyMessageDelayed(postion, 3000);
            }
        });

    }
}
