package com.jackchan.focustoday.http.okhttputils.builder;

import com.jackchan.focustoday.http.okhttputils.OkHttpUtils;
import com.jackchan.focustoday.http.okhttputils.request.OtherRequest;
import com.jackchan.focustoday.http.okhttputils.request.RequestCall;


public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
