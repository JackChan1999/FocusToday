package com.jackchan.focustoday.http.okhttputils.cookie;

import java.util.List;

import com.jackchan.focustoday.http.okhttputils.cookie.store.CookieStore;
import com.jackchan.focustoday.http.okhttputils.cookie.store.HasCookieStore;
import com.jackchan.focustoday.http.okhttputils.utils.Exceptions;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieJarImpl implements CookieJar, HasCookieStore
{
    private CookieStore cookieStore;

    public CookieJarImpl(CookieStore cookieStore)
    {
        if (cookieStore == null) Exceptions.illegalArgument("cookieStore can not be null.");
        this.cookieStore = cookieStore;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies)
    {
        cookieStore.add(url, cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url)
    {
        return cookieStore.get(url);
    }

    @Override
    public CookieStore getCookieStore()
    {
        return cookieStore;
    }
}
