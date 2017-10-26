package com.yangtianyu.utils;

import android.content.Context;
import android.content.Intent;

import com.yangtianyu.net.Constant;
import com.yangtianyu.popularmovie.MovieDetailsActivity;

/**
 * Created by yangtianyu on 2017/10/26.
 */

public class JumpUtils {

    public static void goMovieDetails(Context context,int movieId){
        Intent intent = new Intent(context,MovieDetailsActivity.class);
        intent.putExtra(Constant.MOVIE_ID,movieId+"");
        context.startActivity(intent);
    }
}
