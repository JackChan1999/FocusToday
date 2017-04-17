package com.jackchen.focustoday.http.okhttputils.builder;

import com.jackchen.focustoday.http.okhttputils.OkHttpUtils;
import com.jackchen.focustoday.http.okhttputils.request.OtherRequest;
import com.jackchen.focustoday.http.okhttputils.request.RequestCall;


public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers).build();
    }
}
