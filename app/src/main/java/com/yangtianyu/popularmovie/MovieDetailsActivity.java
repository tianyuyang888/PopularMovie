package com.yangtianyu.popularmovie;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangtianyu.bean.MovieDetailsEntity;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @Bind(R.id.tool_bar)
    Toolbar mToolBar;
    @Bind(R.id.pb_loading)
    ProgressBar mPbLoading;
    @Bind(R.id.tv_loading)
    TextView mTvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    private String mMovie_id = "";
    private URL mUrl;

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
        }
        getData();
//        initData();
    }

    private void getData() {
        URL url = buildUrl(Api.BASE_URL + mMovie_id);
        new MovieDetailTask().execute(url);
    }

    private URL buildUrl(String uri) {
        URL url = null;
        Uri build = Uri.parse(uri).buildUpon()
                .appendQueryParameter(Api.API_KEY, Constant.API_KEY)
                .appendQueryParameter(Api.LANGUAGE, Constant.LANGUAGE)
                .build();
        try {
            url = new URL(build.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    @OnClick(R.id.ll_loading)
    public void onClick() {
        getData();
    }

    public class MovieDetailTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            UiUtils.showLoading(mLlLoading, mTvLoading, mPbLoading);
        }

        @Override
        protected String doInBackground(URL... params) {
            String result = "";
            try {
                result = ApiUtils.getResponseFromHttpUrl(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            UiUtils.hideLoading(mLlLoading);
            if (!TextUtils.isEmpty(s)) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String title = jsonObject.getString("title");
                    double vote_average = jsonObject.optDouble("vote_average");
                    String release_date = jsonObject.optString("release_date");
                    String overview = jsonObject.optString("overview");
                    String poster_path = jsonObject.optString("poster_path");
                    mTvTitle.setText(getResources().getString(R.string.movie_title) + ":" + title);
                    mTvVoteAverage.setText(getResources().getString(R.string.movie_vote_average) + ":" + vote_average);
                    mTvReleaseTime.setText(getResources().getString(R.string.movie_release_time) + ":" + release_date);
                    mTvOverview.setText(getResources().getString(R.string.movie_overview) + ":" + overview);
                    ImageUtils.loadImage(Api.API_IMAGE_W500, poster_path, mIvPoster);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                UiUtils.showNetError(mLlLoading, mTvLoading, mPbLoading);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return true;
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
                ImageUtils.loadImage(Api.API_IMAGE_W500, movieDetailsEntity.poster_path, mIvPoster);
            }
        });
    }
}
