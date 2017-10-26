package com.yangtianyu.popularmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangtianyu.bean.MovieDetailsEntity;
import com.yangtianyu.net.Api;
import com.yangtianyu.net.ApiUtils;
import com.yangtianyu.net.Constant;
import com.yangtianyu.utils.ImageUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by yangtianyu on 2017/10/26.
 */

public class MovieDetailsActivity extends AppCompatActivity {

    @Bind(R.id.tv_title)
    TextView mTvTitle;
    @Bind(R.id.tv_overview)
    TextView mTvOverview;
    @Bind(R.id.tv_vote_average)
    TextView mTvVoteAverage;
    @Bind(R.id.tv_release_time)
    TextView mTvReleaseTime;
    @Bind(R.id.iv_poster)
    ImageView mIvPoster;
    private String mMovie_id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        if (getIntent()!=null){
            mMovie_id = getIntent().getStringExtra(Constant.MOVIE_ID);
        }
        initData();
    }

    private void initData() {
        ApiUtils.getMovieDetails(mMovie_id, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                MovieDetailsEntity movieDetailsEntity = new Gson().fromJson(response, MovieDetailsEntity.class);
                mTvTitle.setText(getResources().getString(R.string.movie_title) + ":" + movieDetailsEntity.title);
                mTvVoteAverage.setText(getResources().getString(R.string.movie_vote_average) + ":" + movieDetailsEntity.vote_average);
                mTvReleaseTime.setText(getResources().getString(R.string.movie_release_time) + ":" + movieDetailsEntity.release_date);
                mTvOverview.setText(getResources().getString(R.string.movie_overview) + ":" + movieDetailsEntity.overview);
                ImageUtils.loadImgage(movieDetailsEntity.poster_path,mIvPoster);
            }
        });
    }
}
