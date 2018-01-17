package com.yangtianyu.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.net.Constant;
import com.yangtianyu.popularmovie.AllCommentsActivity;
import com.yangtianyu.popularmovie.MovieDetailsActivity;
import com.yangtianyu.popularmovie.R;

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
        if (appIntent.resolveActivity(context.getPackageManager())!=null){
            context.startActivity(appIntent);
        }else if (webIntent.resolveActivity(context.getPackageManager())!=null){
            context.startActivity(webIntent);
        }else {
            Toast.makeText(context,context.getString(R.string.no_app),Toast.LENGTH_SHORT).show();
        }
    }

    public static void goMovieAllComments(Context context, String movie_id) {
        Intent intent = new Intent(context, AllCommentsActivity.class);
        intent.putExtra(Constant.MOVIE_ID, movie_id);
        context.startActivity(intent);
    }
}
