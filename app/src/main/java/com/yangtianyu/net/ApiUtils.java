package com.yangtianyu.net;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    /**
     * 获取电影的视频
     * @param movieId
     * @param callback
     */
    public static void getMovieVideos(String movieId,Callback callback){
        OkHttpUtils.get().url(Api.BASE_URL+movieId+Api.VIDEOS)
                .addParams(Api.API_KEY,Constant.API_KEY)
                .build().execute(callback);
    }

    /**
     * 获取电影的评论
     * @param movieId
     * @param callback
     */
    public static void getMovieReviews(String movieId,Callback callback){
        OkHttpUtils.get().url(Api.BASE_URL+movieId+Api.REVIEWS)
                .addParams(Api.API_KEY,Constant.API_KEY)
                .build().execute(callback);
    }

    /**
     * 使用HttpURLConnection请求网络
     * @param url
     * @return
     * @throws IOException
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            return getStringFromInputStream(in);
//            Scanner scanner = new Scanner(in);
//            scanner.useDelimiter("\\A");
//            if (scanner.hasNext()){
//                return scanner.next();
//            }else {
//                return null;
//            }
        } finally {
            urlConnection.disconnect();
        }
    }


    private static String getStringFromInputStream(InputStream in) {
        String result = "";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        try {
            while ((len = in.read(buffer)) != -1){
                os.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                result = os.toString();
                in.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
