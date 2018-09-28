package com.taopao.tiktok.tests;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * @Author：淘跑
 * @Date: 2018/9/20 0020 13:57
 * @Use： 分页加载工具类
 */
public class PagingUtils {
    public final static int LIMIT = 20;

    /**
     * 检查当前的页数计算出下次应该请求的页数(开启上拉加载和判断当前是否是最后一页)
     * 理论上讲,如果失败的话页数应该是不变的(所以不考虑请求时失败的情况,在请求成功的时候检查一下即可)
     *
     * @param adapter     基于 BaseQuickAdapter
     * @param page        当前页数
     * @param isRefresh   是否是刷新
     * @param allList     当前所有的list
     * @param currentList 请求到的list数据
     */
    @Deprecated
    public static int CheckUpPageOrAdapter(BaseQuickAdapter adapter, int page, boolean isRefresh, List allList, List currentList) {
        if (adapter != null) {
            if (page == 1) {
                if (currentList == null || currentList.size() < LIMIT) {
                    //关闭上拉加载
                    adapter.setEnableLoadMore(false);
                }
            } else {
                //开启上拉加载
                adapter.setEnableLoadMore(true);
                if (currentList == null || currentList.size() < LIMIT) {
                    //加载完成没有更多数据了
                    adapter.loadMoreEnd();
                } else {
                    //加载完成还有更多数据
                    adapter.loadMoreComplete();
                }
            }

            //如果不是刷新
            if (currentList == null || currentList.size() < 1) {
                //如果请求到的数据是空的,或者请求到数据长度为0,那么页数就不应该再改变
                //这里提示没有更多数据了
//            Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
            } else {
                //如果list非空,并且长度大于0
                page++;
            }
            if (isRefresh) {
                allList.clear();
                allList.addAll(currentList);
                adapter.notifyDataSetChanged();
            } else {
                int preEndIndex = allList.size();
                allList.addAll(currentList);
                adapter.notifyItemRangeInserted(preEndIndex, currentList.size());
            }
        }
        return page;
    }


    /**
     * 检查当前的页数计算出下次应该请求的页数(开启上拉加载和判断当前是否是最后一页)
     * 理论上讲,如果失败的话页数应该是不变的(所以不考虑请求时失败的情况,在请求成功的时候检查一下即可)
     *
     * @param adapter     基于 BaseQuickAdapter
     * @param page        当前页数
     * @param allList     当前所有的list
     * @param currentList 请求到的list数据
     */
    public static int CheckUpPageOrAdapter(BaseQuickAdapter adapter, int page, List allList, List currentList) {
        if (adapter != null) {
            if (page == 1) {
                if (currentList == null || currentList.size() < LIMIT) {
                    //关闭上拉加载
                    adapter.setEnableLoadMore(false);
                }
            } else {
                //开启上拉加载
                adapter.setEnableLoadMore(true);
                if (currentList == null || currentList.size() < LIMIT) {
                    //加载完成没有更多数据了
                    adapter.loadMoreEnd();
                } else {
                    //加载完成还有更多数据
                    adapter.loadMoreComplete();
                }
            }
            if (page == 1) {
                allList.clear();
                allList.addAll(currentList);
                adapter.notifyDataSetChanged();
            } else {
                int preEndIndex = allList.size();
                allList.addAll(currentList);
                adapter.notifyItemRangeInserted(preEndIndex, currentList.size());
            }

            //如果不是刷新
            if (currentList == null || currentList.size() < 1) {
                //如果请求到的数据是空的,或者请求到数据长度为0,那么页数就不应该再改变
                //这里提示没有更多数据了
//            Toast.makeText(mContext, "没有更多数据了", Toast.LENGTH_SHORT).show();
            } else {
                //如果list非空,并且长度大于0
                page++;
            }
        }
        return page;
    }

    private static void notifyItemRangeInserted(BaseQuickAdapter adapter, List alllist, List nowlist) {
        int preEndIndex = alllist.size();
        alllist.addAll(nowlist);
        adapter.notifyItemRangeInserted(preEndIndex, nowlist.size());
    }

}
