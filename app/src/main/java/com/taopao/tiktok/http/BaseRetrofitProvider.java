package com.taopao.tiktok.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.taopao.tiktok.http.cookie.CookieJarImpl;
import com.taopao.tiktok.http.cookie.store.PersistentCookieStore;
import com.taopao.tiktok.http.interceptor.BaseInterceptor;
import com.taopao.tiktok.http.interceptor.CacheInterceptor;
import com.taopao.tiktok.http.interceptor.LoggingInterceptor;
import com.taopao.tiktok.ui.base.BaseApplication;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofitProvider {

    private static Retrofit retrofit;
    private Cache cache = null;
    private static OkHttpClient okHttpClient;
    //超时时间
    private static final int DEFAULT_TIMEOUT = 40;
    //缓存时间
    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024;


    //服务端根路径
    public static String baseUrl = "http://www.oschina.net/";

    private File httpCacheDirectory;

    private static BaseRetrofitProvider INSTANCE;


    public static BaseRetrofitProvider getInstance() {
        //单利模式获取BaseRetrofitProvider
        if (INSTANCE == null) {
            synchronized (BaseRetrofitProvider.class) {
                INSTANCE = new BaseRetrofitProvider();
            }
        }
        return INSTANCE;
    }


    public static BaseRetrofitProvider getInstance(String url) {
        //单利模式获取BaseRetrofitProvider
        if (INSTANCE == null) {
            synchronized (BaseRetrofitProvider.class) {
                INSTANCE = new BaseRetrofitProvider(url);
            }
        }
        return INSTANCE;
    }

    public static BaseRetrofitProvider getInstance(String url, Map<String, String> headers) {
        //单利模式获取BaseRetrofitProvider
        if (INSTANCE == null) {
            synchronized (BaseRetrofitProvider.class) {
                INSTANCE = new BaseRetrofitProvider(url, headers);
            }
        }
        return INSTANCE;
    }


    protected BaseRetrofitProvider() {
        this(baseUrl, null);
    }

    protected BaseRetrofitProvider(String url) {
        this(url, null);
    }

    protected BaseRetrofitProvider(String url, Map<String, String> headers) {
        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(getContext().getCacheDir(), "net_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, CACHE_TIMEOUT);
            }
        } catch (Exception e) {
            Log.e(this.getClass().getName(), "Could not create http cache");
        }
        okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new CookieJarImpl(new PersistentCookieStore(getContext())))
                .cache(cache)
                .addInterceptor(new BaseInterceptor(headers))
                .addInterceptor(new CacheInterceptor(getContext()))
                .addInterceptor(new LoggingInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();

    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    /**
     * /**
     * execute your customer API
     * For example:
     * MyApiService service =
     * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
     * <p>
     * RetrofitClient.getInstance(MainActivity.this)
     * .execute(service.lgon("name", "password"), subscriber)
     * * @param subscriber
     */

    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        return null;
    }

    public Context getContext() {
        return BaseApplication.getContext();
    }


}
