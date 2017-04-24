package com.jackchan.focustoday.http.okhttputils.callback;

import okhttp3.Response;

import java.io.IOException;

public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response) throws IOException
    {
        return response.body().string();
    }

}
