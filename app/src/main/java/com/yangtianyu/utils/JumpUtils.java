package com.yangtianyu.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.net.Constant;
import com.yangtianyu.popularmovie.MovieDetailsActivity;

/**
 * Created by yangtianyu on 2017/10/26.
 */

public class JumpUtils {

    /**
     * 跳转电影详情界面
     * @param context
     * @param movieId
     */
    public static void goMovieDetails(Context context, MovieEntity movieId){
        Intent intent = new Intent(context,MovieDetailsActivity.class);
        intent.putExtra(Constant.MOVIE_ID,movieId);
        context.startActivity(intent);
    }

    /**
     * 跳转YouTube播放视频
     * @param context
     * @param id
     */
    public static void watchYoutubeVideo(Context context,String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        }catch (ActivityNotFoundException ex){
            context.startActivity(webIntent);
        }
    }

}
