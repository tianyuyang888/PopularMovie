package com.yangtianyu.popularmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangtianyu.adapter.CommentAdapter;
import com.yangtianyu.bean.MovieCommentListEntity;
import com.yangtianyu.net.ApiUtils;
import com.yangtianyu.net.Constant;
import com.yangtianyu.utils.LogUtils;
import com.yangtianyu.utils.UiUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by yangtianyu on 2017/12/4.
 */

public class AllCommentsActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;
    @BindView(R.id.tv_loading)
    TextView mTvLoading;
    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;

    private CommentAdapter mCommentAdapter;
    private String mMovieId;
    private int page = 1;
    private boolean isError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_recyclerview);
        ButterKnife.bind(this);
        mToolBar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolBar.setTitle(getResources().getString(R.string.all_comments));
        setSupportActionBar(mToolBar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent() != null) mMovieId = getIntent().getStringExtra(Constant.MOVIE_ID);
        initView();
        initData();
    }


    private void initView() {
        mRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCommentAdapter = new CommentAdapter();
        mRv.setAdapter(mCommentAdapter);
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int lastPosition = -1;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof LinearLayoutManager)
                        lastPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    if (lastPosition == layoutManager.getItemCount() - 1) {
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        getCommentList(++page);
    }

    private void initData() {
        getCommentList(page);
    }

    private void getCommentList(int i) {
        Log.d("aaaaa", "getCommentList:" + i);
        ApiUtils.getMovieReviews(mMovieId, i, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                isError = true;
                UiUtils.showNetError(mLlLoading, mTvLoading, mPbLoading);
            }

            @Override
            public void onResponse(String response, int id) {
                UiUtils.hideLoading(mLlLoading);
                LogUtils.d(response);
                MovieCommentListEntity movieCommentListEntity = new Gson().fromJson(response, MovieCommentListEntity.class);
                if (movieCommentListEntity != null && movieCommentListEntity.results != null) {
                    mCommentAdapter.update(movieCommentListEntity.results);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
