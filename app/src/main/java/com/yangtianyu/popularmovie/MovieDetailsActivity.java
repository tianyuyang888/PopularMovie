package com.yangtianyu.popularmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.net.Api;
import com.yangtianyu.net.Constant;
import com.yangtianyu.utils.ImageUtils;
import com.yangtianyu.utils.JumpUtils;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.iv_trailer)
    ImageView mIvTrailer;
    private String mMovie_id = "";
    private URL mUrl;
    private MovieEntity mMovieEntity;
    private String trailer_id = "";

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
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    finish();
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.iv_poster, R.id.iv_trailer})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_poster:
                break;
            case R.id.iv_trailer:
                trailer_id = "xKJmEC5ieOk";
                JumpUtils.watchYoutubeVideo(this,trailer_id);
                break;
        }
    }
}
