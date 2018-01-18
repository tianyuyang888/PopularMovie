package com.yangtianyu.popularmovie;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangtianyu.adapter.OnItemClickListener;
import com.yangtianyu.adapter.PosterAdapter;
import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.bean.PopularEntity;
import com.yangtianyu.data.MovieContract;
import com.yangtianyu.data.MovieDbHelper;
import com.yangtianyu.net.Api;
import com.yangtianyu.net.ApiUtils;
import com.yangtianyu.utils.JumpUtils;
import com.yangtianyu.utils.LogUtils;
import com.yangtianyu.utils.SPUtils;
import com.yangtianyu.utils.UiUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final int MOVIE_LOADER = 0;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.rv_movie_poster)
    RecyclerView mRvMoviePoster;
    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;
    @BindView(R.id.tv_loading)
    TextView mTvLoading;
    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    private List<MovieEntity> mList;
    private PosterAdapter mPosterAdapter;
    private int page = 1;
    private String localUrl = Api.API_POPULAR;
    private boolean isError = false;
    private boolean isCollection;

    @Override
    protected void onResume() {
        super.onResume();
        if (isCollection) {
            getSupportLoaderManager().restartLoader(MOVIE_LOADER, null, this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(mToolBar);
        initView();
        int type = (int) SPUtils.get(R.string.type, 1);
        switch (type){
            case 1:
                getPopular();
                break;
            case 2:
                getTopRated();
                break;
            case 3:
                getCollection();
                break;
        }
    }



    private void initData() {
        mList.clear();
        mPosterAdapter.clearData();
        mLlLoading.setVisibility(View.VISIBLE);
        mPbLoading.setVisibility(View.VISIBLE);
        mTvLoading.setText(getResources().getString(R.string.net_loading));
        page = 1;
        getMovieList(page);
    }

    private void getMovieList(int i) {
        Log.d("aaaaa", "getMovieList:" + i);
        ApiUtils.getPoster(localUrl, i, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                isError = true;
                UiUtils.showNetError(mLlLoading, mTvLoading, mPbLoading);
            }

            @Override
            public void onResponse(String response, int id) {
                UiUtils.hideLoading(mLlLoading);
                LogUtils.d(response);
                PopularEntity popularEntity = new Gson().fromJson(response, PopularEntity.class);
                if (popularEntity != null && popularEntity.results != null) {
                    mList.addAll(popularEntity.results);
                    mPosterAdapter.update(popularEntity.results);

                }
            }
        });
    }

    private void initView() {
        mList = new ArrayList<>();
        mPosterAdapter = new PosterAdapter(this);
        mRvMoviePoster.setLayoutManager(new GridLayoutManager(this, 2));
//        mRvMoviePoster.addItemDecoration(new GridSpacingItemDecoration(50, 2));
        mRvMoviePoster.setAdapter(mPosterAdapter);
        mRvMoviePoster.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int lastPosition = -1;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager) {
                        lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    }
                    if (lastPosition == recyclerView.getLayoutManager().getItemCount() - 1) {
                        loadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mPosterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnClickListener(int position) {
                JumpUtils.goMovieDetails(MainActivity.this, mList.get(position));
            }
        });
    }

    private void loadMore() {
        if (isCollection) return;
        getMovieList(++page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.movie_popular:
                SPUtils.put(R.string.type,1);
                getPopular();
                break;
            case R.id.movie_vote:
                SPUtils.put(R.string.type,2);
                getTopRated();
                break;
            case R.id.movie_collection:
                SPUtils.put(R.string.type,3);
                getCollection();
                break;
        }
        return true;
    }

    private void getCollection() {
        isCollection = true;
        if (!getSupportLoaderManager().hasRunningLoaders()) getSupportLoaderManager().initLoader(MOVIE_LOADER, null, this);
        getSupportLoaderManager().restartLoader(MOVIE_LOADER, null, this);
    }

    private void getTopRated() {
        isCollection = false;
        localUrl = Api.API_TOP_RATED;
        initData();
    }

    private void getPopular() {
        isCollection = false;
        localUrl = Api.API_POPULAR;
        initData();
    }

    @OnClick({R.id.ll_loading})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_loading:
                if (isError) {
                    initData();
                }
                break;
        }
    }

    public void getCollections(Cursor cursor) {
        mList.clear();
        mPosterAdapter.clearData();
        mLlLoading.setVisibility(View.VISIBLE);
        mPbLoading.setVisibility(View.VISIBLE);
        mTvLoading.setText(getResources().getString(R.string.net_loading));
        while (cursor.moveToNext()) {
            MovieEntity entity = new MovieEntity();
            entity.id = cursor.getInt(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_ID));
            entity.title = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_TITLE));
            entity.release_date = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_RELEASE_DATE));
            entity.overview = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_OVERVIEW));
            entity.vote_average = cursor.getDouble(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE));
            entity.poster_path = cursor.getString(cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_POSTER_PATH));
            mList.add(entity);
        }
        if (mList.size() > 0) {
            UiUtils.hideLoading(mLlLoading);
            mPosterAdapter.update(mList);
        } else {
            UiUtils.showNoData(mLlLoading, mTvLoading, mPbLoading, "你还没有收藏哦！");
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case MOVIE_LOADER:
                return new CursorLoader(this,
                        MovieContract.MovieEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loader Not Implemented: " + id);
        }
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        getCollections(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPosterAdapter.notifyDataSetChanged();
    }

}
