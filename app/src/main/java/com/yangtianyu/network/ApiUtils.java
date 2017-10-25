package com.yangtianyu.network;

import android.content.Context;

import com.yangtianyu.bean.PopularEntity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Random;

/**
 * Created by yangtianyu on 2017/10/25.
 */

public class ApiUtils {
    public static void getPoster(Callback callback){
        OkHttpUtils.get().url(Api.API_POPULAR)
                .addParams("api_key",Constant.API_KEY)
                .addParams("language",Constant.LANGUAGE)
                .addParams("region",Constant.REGION)
                .addParams("page","1")
                .build().execute(callback);
    }
}
