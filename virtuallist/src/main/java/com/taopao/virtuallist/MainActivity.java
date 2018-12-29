package com.taopao.virtuallist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleview;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecycleview = (RecyclerView) findViewById(R.id.recycleview);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        new PagerSnapHelper().attachToRecyclerView(mRecycleview);

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("");
        }
        mAdapter = new MyAdapter(strings);
        mRecycleview.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, mRecycleview);
    }

    class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public MyAdapter(@Nullable List<String> data) {
            super(R.layout.recycle_item_one, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }
}
