package com.yangtianyu.popularmovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yangtianyu.adapter.OnItemClickListener;
import com.yangtianyu.adapter.PosterAdapter;
import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.bean.PopularEntity;
import com.yangtianyu.net.ApiUtils;
import com.yangtianyu.utils.GridSpacingItemDecoration;
import com.yangtianyu.utils.JumpUtils;
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
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;
    @Bind(R.id.tv_setting)
    TextView mTvSetting;
    private List<MovieEntity> mList;
    private PosterAdapter mPosterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        ApiUtils.getPoster(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                PopularEntity popularEntity = new Gson().fromJson(response, PopularEntity.class);
                if (popularEntity != null && popularEntity.results != null) {
                    mPosterAdapter.update(popularEntity.results);
                }
            }
        });
    }

    private void initView() {
        mList = new ArrayList<>();
        mPosterAdapter = new PosterAdapter(this, mList);
        mRvMoviePoster.setLayoutManager(new GridLayoutManager(this, 2));
        mRvMoviePoster.addItemDecoration(new GridSpacingItemDecoration(50, 2));
        mRvMoviePoster.setAdapter(mPosterAdapter);
        mPosterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnClickListener(int position) {
                JumpUtils.goMovieDetails(MainActivity.this,mList.get(position).id);
            }
        });
    }

    @OnClick(R.id.tv_setting)
    public void onClick() {

    }
}
