package com.yangtianyu.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.yangtianyu.popularmovie.R;

/**
 * Created by yangtianyu on 2017/12/11.
 */

public class CommentViewHolder extends ViewHolder {
    public TextView mTvName;
    public TextView mTvComment;

    public CommentViewHolder(View itemView) {
        super(itemView);
        mTvName = (TextView) itemView.findViewById(R.id.tv_name);
        mTvComment = (TextView) itemView.findViewById(R.id.tv_comment);
    }
}
