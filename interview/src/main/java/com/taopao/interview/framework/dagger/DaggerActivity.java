package com.taopao.interview.framework.dagger;

import android.os.Bundle;
import android.widget.TextView;

import com.taopao.interview.BaseActivity;
import com.taopao.interview.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaggerActivity extends BaseActivity {
    //    @Inject
//    public TestSingleton mTestSingleton;
//    @Inject
//    public TestSingleton mTestSingleton1;
    @Inject
    public Presenter mPresenter;
    @BindView(R.id.tv_test)
    TextView mTvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        ButterKnife.bind(this);
//        User user = DaggerUserComponent.builder()
//                .build()
//                .getUser();
//        user.waiteString();
//        user.TestLazy();
//
//        DaggerTestSingleton_ActivityComponent.builder().build().inject(this);
//
//        Logger.d(mTestSingleton.toString());
//        Logger.d(mTestSingleton1.toString());


        DaggerMainComponent.builder().build().inject(this);

    }

    @OnClick(R.id.tv_test)
    public void onViewClicked() {
        mPresenter.ete();
    }
}
