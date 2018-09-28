package com.taopao.tiktok.tests;

import com.taopao.tiktok.tests.response.BaseResponse;
import com.taopao.tiktok.tests.response.ImgListInfo;
import com.taopao.tiktok.tests.response.WanAndroidResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Author：淘跑
 * @Date: 2018/9/28 0028 14:12
 * @Use：
 */
public interface Api {

    public static final String BASE_URL = "http://47.97.214.237/yjy/";

    public final static String API_WAN_ANDROID = "http://www.wanandroid.com/";
    int RequestSuccess = 0;
    int RequestFailure_login = 201;

    /**
     * 玩安卓，文章列表、知识体系下的文章
     *
     * @param page 页码，从0开始
     * @param cid  体系id 可为null
     */
    @GET("article/list/{page}/json")
    Observable<BaseResponse<WanAndroidResponse>> getHomeList(@Path("page") int page, @Query("cid") Integer cid);


    @GET("article/list/{page}/json")
    Observable<BaseResponse<WanAndroidResponse>> getHomeListError(@Path("page") String page);

/////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String IMGLIST = "http://gank.io/api/data/福利/10/1";


    public static final String IMGLIST_PAGE = "http://gank.io/api/data/福利/10/";

    //直接请求一个完整后面带占位符
    @GET(IMGLIST_PAGE + "{page}")
    Observable<ImgListInfo> getImgListPage(@Path("page") String page);
}
