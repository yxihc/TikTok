package com.taopao.tiktok.tests;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.taopao.tiktok.R;
import com.taopao.tiktok.tests.response.WanAndroidResponse;

import java.util.List;

/**
 * @Author：淘跑
 * @Date: 2018/9/28 0028 14:23
 * @Use：
 */
public class WanAndroidAdapter extends BaseQuickAdapter<WanAndroidResponse.DatasBean, BaseViewHolder> {
    public WanAndroidAdapter(@Nullable List<WanAndroidResponse.DatasBean> data) {
        super(R.layout.recycle_item_wanandroid, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WanAndroidResponse.DatasBean item) {

    }
}
