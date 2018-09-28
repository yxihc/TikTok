package com.taopao.tiktok.http.cookie;

import com.taopao.tiktok.http.cookie.store.CookieStore;
import com.taopao.tiktok.http.cookie.store.PersistentCookieStore;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * @Author： 淘跑
 * @Date: 2018/7/5 11:43
 * @Use：
 */
public class CookieJarImpl implements CookieJar {

    private CookieStore cookieStore;

    public CookieJarImpl(PersistentCookieStore cookieStore) {
        if (cookieStore == null) {
            throw new IllegalArgumentException("cookieStore can not be null!");
        }
        this.cookieStore = cookieStore;
    }

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.saveCookie(url, cookies);
    }

    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        return cookieStore.loadCookie(url);
    }

    public CookieStore getCookieStore() {
        return cookieStore;
    }
}