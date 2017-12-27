package com.yangtianyu.popularmovie;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangtianyu.bean.MovieCommentEntity;
import com.yangtianyu.bean.MovieCommentListEntity;
import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.bean.MovieTrailerEntity;
import com.yangtianyu.bean.MovieTrailerListEntity;
import com.yangtianyu.data.MovieContract;
import com.yangtianyu.data.MovieDbHelper;
import com.yangtianyu.net.Api;
import com.yangtianyu.net.ApiUtils;
import com.yangtianyu.net.Constant;
import com.yangtianyu.utils.ImageUtils;
import com.yangtianyu.utils.JumpUtils;
import com.yangtianyu.utils.LogUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.ll_trailer)
    LinearLayout mLlTrailer;
    @BindView(R.id.tv_comment)
    TextView mTvComment;
    @BindView(R.id.ll_comment)
    LinearLayout mLlComment;
    private String mMovie_id = "";
    private MovieEntity mMovieEntity;
    private List<MovieTrailerEntity> mResults = new ArrayList<>();
    private List<MovieCommentEntity> mCommentEntityList = new ArrayList<>();
    private SQLiteDatabase mDb;
    private MenuItem menu_collection;
    private boolean isCollection;

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
            mMovieEntity = getIntent().getParcelableExtra(Constant.MOVIE_ID);
            mMovie_id = mMovieEntity.id + "";
        }
        initDbHelper();
        isCollection = isCollection(mMovieEntity.id);
        initData();
        setData();
    }

    private void initDbHelper() {
        MovieDbHelper movieDbHelper = new MovieDbHelper(this);
        mDb = movieDbHelper.getWritableDatabase();
    }

    private void initData() {
        ApiUtils.getMovieVideos(mMovie_id, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d(response);
                MovieTrailerListEntity movieTrailerListEntity = new Gson().fromJson(response, MovieTrailerListEntity.class);
                if (movieTrailerListEntity != null && movieTrailerListEntity.results != null && movieTrailerListEntity.results.size() > 0) {
                    mResults = movieTrailerListEntity.results;
                    addTrailer();
                } else {
                    mLlTrailer.addView(createNoDataView("暂无预告"));
                }
            }
        });

        ApiUtils.getMovieReviews(mMovie_id, 1, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d(response);
                MovieCommentListEntity movieCommentListEntity = new Gson().fromJson(response, MovieCommentListEntity.class);
                if (movieCommentListEntity != null) {
                    int total_results = movieCommentListEntity.total_results;
                    if (total_results <= 5) {
                        mTvComment.setVisibility(View.GONE);
                    } else {
                        mTvComment.setVisibility(View.VISIBLE);
                        mTvComment.setText("全部评论" + total_results + "个");
                    }
                    if (movieCommentListEntity.results != null && movieCommentListEntity.results.size() > 0) {
                        mCommentEntityList = movieCommentListEntity.results;
                        addComment();
                    } else {
                        mLlComment.addView(createNoDataView("暂无评论"));
                    }
                }
            }
        });
    }

    private View createNoDataView(String noData) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_no_data, null);
        TextView tv = (TextView) inflate.findViewById(R.id.tv_no_data);
        tv.setText(noData);
        return inflate;
    }

    private void addComment() {
        for (int i = 0; i < (mCommentEntityList.size() < 5 ? mCommentEntityList.size() : 5); i++) {
            mLlComment.addView(createCommentView(mCommentEntityList.get(i)));
        }
    }

    private View createCommentView(MovieCommentEntity movieCommentEntity) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_comment, null);
        TextView tvName = (TextView) inflate.findViewById(R.id.tv_name);
        TextView tvComment = (TextView) inflate.findViewById(R.id.tv_comment);
        tvName.setText(movieCommentEntity.author + ":");
        tvComment.setText(movieCommentEntity.content);
        return inflate;
    }

    private void setData() {
        mTvTitle.setText(getResources().getString(R.string.movie_title) + ":" + mMovieEntity.title);
        mTvVoteAverage.setText(getResources().getString(R.string.movie_vote_average) + ":" + mMovieEntity.vote_average);
        mTvReleaseTime.setText(getResources().getString(R.string.movie_release_time) + ":" + mMovieEntity.release_date);
        mTvOverview.setText(getResources().getString(R.string.movie_overview) + ":" + mMovieEntity.overview);
        ImageUtils.loadImage(Api.API_IMAGE_W500, mMovieEntity.poster_path, mIvPoster);
    }

    private void addTrailer() {
        for (int i = 0; i < mResults.size(); i++) {
            mLlTrailer.addView(createTrailerView(mResults.get(i)));
        }
    }

    private View createTrailerView(final MovieTrailerEntity movieTrailerEntity) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_trailer, null);
        TextView tvName = (TextView) inflate.findViewById(R.id.tv_name);
        ImageView ivPlay = (ImageView) inflate.findViewById(R.id.iv_play);
        tvName.setText(movieTrailerEntity.name);
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.watchYoutubeVideo(MovieDetailsActivity.this, movieTrailerEntity.key);
            }
        });
        return inflate;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_collection, menu);
        menu_collection = menu.getItem(0);
        if (isCollection) {
            menu_collection.setIcon(R.drawable.collection);
        } else {
            menu_collection.setIcon(R.drawable.not_collection);
        }
        return true;
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
            case R.id.tb_collection:
                if (isCollection) {
                    cancelCollection(mMovieEntity.id);
                } else {
                    collection(mMovieEntity);
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.iv_poster, R.id.tv_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_poster:
                break;
            case R.id.tv_comment:
                JumpUtils.goMovieAllComments(this, mMovie_id);
                break;
        }
    }


    public void collection(MovieEntity movieEntity) {
        ContentValues cv = new ContentValues();
        cv.put(MovieContract.MovieEntry.COLUMN_ID, movieEntity.id);
        cv.put(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE, movieEntity.vote_average);
        cv.put(MovieContract.MovieEntry.COLUMN_TITLE, movieEntity.title);
        cv.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, movieEntity.release_date);
        cv.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, movieEntity.overview);
        cv.put(MovieContract.MovieEntry.COLUMN_POSTER_PATH, movieEntity.poster_path);
        Uri insert = getContentResolver().insert(MovieContract.MovieEntry.CONTENT_URI, cv);
        if (insert != null) {
            isCollection = true;
            menu_collection.setIcon(R.drawable.collection);
        }
    }

    public void cancelCollection(int movie_id) {
        String id = String.valueOf(movie_id);
        Uri uri = MovieContract.MovieEntry.CONTENT_URI;
        uri = uri.buildUpon().appendPath(id).build();
        int delete = getContentResolver().delete(uri, null, null);
        if (delete != 0) {
            isCollection = false;
            menu_collection.setIcon(R.drawable.not_collection);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public boolean isCollection(int movie_id) {
        mDb.query(MovieContract.MovieEntry.TABLE_NAME,
                new String[]{MovieContract.MovieEntry.COLUMN_ID},
                "movie_id=?",
                new String[]{String.valueOf(movie_id)},
                null,
                null,
                null);
        Uri uri = MovieContract.MovieEntry.CONTENT_URI;
        uri = uri.buildUpon().appendPath(movie_id + "").build();
        Cursor cursor = getContentResolver().query(uri,
                null,
                null,
                null,
                null,
                null);
        if (cursor != null) {
            isCollection = cursor.moveToNext();
            cursor.close();
        }
        return isCollection;
    }
}
