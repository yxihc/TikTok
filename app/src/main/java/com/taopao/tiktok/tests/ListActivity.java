package com.taopao.tiktok.tests;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.taopao.tiktok.R;
import com.taopao.tiktok.http.BaseRetrofitProvider;
import com.taopao.tiktok.tests.response.BaseResponse;
import com.taopao.tiktok.tests.response.WanAndroidResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ListActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    public int mPage = 1;
    WanAndroidAdapter mWanAndroidAdapter;
    private List<WanAndroidResponse.DatasBean> mBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        mBeans = new ArrayList<>();
        mWanAndroidAdapter = new WanAndroidAdapter(mBeans);
        getWanAndroid(true);
        mRv.setAdapter(mWanAndroidAdapter);
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWanAndroid(true);
            }
        });

        mWanAndroidAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getWanAndroid(false);
            }
        }, mRv);

        mRv.setLayoutManager(new LinearLayoutManager(this));


        mWanAndroidAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(ListActivity.this, mPage + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    boolean is = true;
    String page = "";

    public void getWanAndroid(boolean isRerresh) {
        if (mPage == 2) {
            if (is) {
                page = mPage + "ss";//模拟请求失败

            } else {
                page = mPage + "";
            }
            is = !is;
        } else if (mPage == 5) {
            if (is) {
                page = mPage + "ss";//模拟请求失败

            } else {
                page = mPage + "";
            }
            is = !is;
        } else {
            page = mPage + "";
        }

        if (isRerresh) {
            mPage = 1;
        }

        BaseRetrofitProvider.getInstance(Api.API_WAN_ANDROID)
                .create(Api.class)
                .getHomeListError(mPage + "")
                .compose(RxUtils.<BaseResponse<WanAndroidResponse>>schedulersTransformer())
                .subscribe(new RxErrorSubscriber<BaseResponse<WanAndroidResponse>>() {
                    @Override
                    public void onResult(BaseResponse<WanAndroidResponse> wanAndroidResponseBaseResponse) {
                        mPage = PagingUtils.CheckUpPageOrAdapter(mWanAndroidAdapter, mPage, mBeans, wanAndroidResponseBaseResponse.getData().getDatas());

                        Log.e("--------------", "页数:  " + mPage);
                        mSrl.setRefreshing(false);
                        if (mPage == 3) {
                            mWanAndroidAdapter.loadMoreFail();
//                            mWanAndroidAdapter.loadMoreEnd();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        mWanAndroidAdapter.loadMoreFail();
                    }
                });

    }

}
