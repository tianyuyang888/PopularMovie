package com.yangtianyu.popularmovie;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangtianyu.bean.MovieDetailsEntity;
import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.net.Api;
import com.yangtianyu.net.ApiUtils;
import com.yangtianyu.net.Constant;
import com.yangtianyu.utils.ImageUtils;
import com.yangtianyu.utils.UiUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by yangtianyu on 2017/10/26.
 */

public class MovieDetailsActivity extends AppCompatActivity {


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.iv_poster)
    ImageView mIvPoster;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_vote_average)
    TextView mTvVoteAverage;
    @BindView(R.id.tv_release_time)
    TextView mTvReleaseTime;
    @BindView(R.id.tv_overview)
    TextView mTvOverview;
    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;
    @BindView(R.id.tv_loading)
    TextView mTvLoading;
    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;
    private String mMovie_id = "";
    private URL mUrl;
    private MovieEntity mMovieEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setTitle(getResources().getString(R.string.movie_detail));
        setSupportActionBar(mToolBar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent() != null) {
            mMovie_id = getIntent().getStringExtra(Constant.MOVIE_ID);
            mMovieEntity = getIntent().getParcelableExtra(Constant.MOVIE_ID);
        }
        setData();
    }

    private void setData() {
        mTvTitle.setText(getResources().getString(R.string.movie_title) + ":" + mMovieEntity.title);
        mTvVoteAverage.setText(getResources().getString(R.string.movie_vote_average) + ":" + mMovieEntity.vote_average);
        mTvReleaseTime.setText(getResources().getString(R.string.movie_release_time) + ":" + mMovieEntity.release_date);
        mTvOverview.setText(getResources().getString(R.string.movie_overview) + ":" + mMovieEntity.overview);
        ImageUtils.loadImage(Api.API_IMAGE_W500, mMovieEntity.poster_path, mIvPoster);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return true;
    }
}
