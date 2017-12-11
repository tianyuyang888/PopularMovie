package com.yangtianyu.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yangtianyu.bean.MovieCommentEntity;
import com.yangtianyu.popularmovie.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangtianyu on 2017/12/5.
 */

public class CommentAdapter extends RecyclerView.Adapter {

    private List<MovieCommentEntity> mCommentEntityList;

    public void update(List<MovieCommentEntity> list) {
        if (mCommentEntityList==null) mCommentEntityList = new ArrayList<>();
        mCommentEntityList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentViewHolder) {
            MovieCommentEntity movieCommentEntity = mCommentEntityList.get(position);
            ((CommentViewHolder) holder).mTvName.setText(movieCommentEntity.author + ":");
            ((CommentViewHolder) holder).mTvComment.setText(movieCommentEntity.content);
        }
    }

    @Override
    public int getItemCount() {
        return mCommentEntityList == null ? 0 : mCommentEntityList.size();
    }
}
