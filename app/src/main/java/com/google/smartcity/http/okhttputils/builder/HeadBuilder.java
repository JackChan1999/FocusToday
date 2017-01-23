package com.google.smartcity.http.okhttputils.builder;

import com.google.smartcity.http.okhttputils.OkHttpUtils;
import com.google.smartcity.http.okhttputils.request.OtherRequest;
import com.google.smartcity.http.okhttputils.request.RequestCall;


public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
