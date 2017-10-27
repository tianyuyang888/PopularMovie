package com.yangtianyu.net;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created by yangtianyu on 2017/10/25.
 */

public class ApiUtils {
    /**
     * 获取热门电影列表
     * @param callback
     */
    public static void getPoster(String url,int page,Callback callback){
        OkHttpUtils.get().url(url)
                .addParams("api_key",Constant.API_KEY)
                .addParams("language",Constant.LANGUAGE)
                .addParams("region",Constant.REGION)
                .addParams("page",page+"")
                .build().execute(callback);
    }

    /**
     * 获取电影详情
     * @param callback
     * @param movieId
     */
    public static void getMovieDetails(String movieId,Callback callback){
        OkHttpUtils.get().url(Api.BASE_URL+movieId)
                .addParams("api_key",Constant.API_KEY)
                .addParams("language",Constant.LANGUAGE)
                .build().execute(callback);
    }
}
