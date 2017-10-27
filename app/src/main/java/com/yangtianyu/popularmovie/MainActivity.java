package com.yangtianyu.popularmovie;

import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangtianyu.adapter.OnItemClickListener;
import com.yangtianyu.adapter.PosterAdapter;
import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.bean.PopularEntity;
import com.yangtianyu.net.Api;
import com.yangtianyu.net.ApiUtils;
import com.yangtianyu.utils.GridSpacingItemDecoration;
import com.yangtianyu.utils.JumpUtils;
import com.yangtianyu.utils.LogUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rv_movie_poster)
    RecyclerView mRvMoviePoster;
    @Bind(R.id.tv_setting)
    TextView mTvSetting;
    @Bind(R.id.pb_loading)
    ProgressBar mPbLoading;
    @Bind(R.id.tv_loading)
    TextView mTvLoading;
    @Bind(R.id.ll_loading)
    LinearLayout mLlLoading;
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;
    private List<MovieEntity> mList;
    private PosterAdapter mPosterAdapter;
    private int page = 1;
    private String localUrl = Api.API_TOP_RATED;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initPopupWindow();
        initData();
    }

    private void initPopupWindow() {
        mPopupWindow = new PopupWindow(this);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View inflate = LayoutInflater.from(this).inflate(R.layout.setting_sort_popupwindow, null);
        TextView tvPopular = (TextView) inflate.findViewById(R.id.tv_popular);
        TextView tvVote = (TextView) inflate.findViewById(R.id.tv_vote);
        View.OnClickListener localListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.tv_popular:
                        localUrl = Api.API_POPULAR;
                        initData();
                        break;
                    case R.id.tv_vote:
                        localUrl = Api.API_TOP_RATED;
                        initData();
                        break;
                }
            }
        };
        tvPopular.setOnClickListener(localListener);
        tvVote.setOnClickListener(localListener);
        mPopupWindow.setContentView(inflate);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
    }

    private void initData() {
        mList.clear();
        mPosterAdapter.clearData();
        mLlLoading.setVisibility(View.VISIBLE);
        mPbLoading.setVisibility(View.VISIBLE);
        mTvLoading.setText(getResources().getString(R.string.net_loading));
       getMovieList(1);
    }

    private void getMovieList(int i) {
        ApiUtils.getPoster(localUrl,i,new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                mPbLoading.setVisibility(View.INVISIBLE);
                mTvLoading.setText(getResources().getString(R.string.net_error));
            }

            @Override
            public void onResponse(String response, int id) {
                mLlLoading.setVisibility(View.GONE);
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
        mRvMoviePoster.addItemDecoration(new GridSpacingItemDecoration(50, 2));
        mRvMoviePoster.setAdapter(mPosterAdapter);
        mRvMoviePoster.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int lastPostion = -1;
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager){
                        lastPostion = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                    }else if (layoutManager instanceof LinearLayoutManager){
                        lastPostion = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                    }else if (layoutManager instanceof StaggeredGridLayoutManager){
                        int[] positions = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                        ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(positions);
                        lastPostion = findMax(positions);
                    }
                    if (lastPostion == recyclerView.getLayoutManager().getItemCount() - 1){
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
                JumpUtils.goMovieDetails(MainActivity.this, mList.get(position).id);
            }
        });
    }

    private void loadMore() {
        getMovieList(++page);
    }

    private int findMax(int[] positions) {
        int max = positions[0];
        for (int value: positions) {
            if (value > max){
                max = value;
            }
        }
        return max;
    }

    @OnClick({R.id.tv_setting, R.id.ll_loading})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_setting:
                mPopupWindow.showAsDropDown(mTvSetting);
                break;
            case R.id.ll_loading:
                initData();
                break;
        }
    }
}
