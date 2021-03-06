package com.yangtianyu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yangtianyu.bean.MovieEntity;
import com.yangtianyu.net.Api;
import com.yangtianyu.popularmovie.R;
import com.yangtianyu.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangtianyu on 2017/10/25.
 */

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    private List<MovieEntity> mList = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public PosterAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public PosterAdapter.PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.movie_poster_item,parent,false));
    }

    @Override
    public void onBindViewHolder(PosterAdapter.PosterViewHolder holder, final int position) {
        holder.mIvPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.OnClickListener(position);
            }
        });
        holder.bind(mList.get(position).poster_path);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void update(List<MovieEntity> list) {
        if (list == null) return;
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public void clearData() {
        mList.clear();
        notifyDataSetChanged();
    }

    class PosterViewHolder extends RecyclerView.ViewHolder{

        ImageView mIvPoster;

        public PosterViewHolder(View itemView) {
            super(itemView);
            mIvPoster = (ImageView) itemView.findViewById(R.id.iv_poster);
        }

        public void bind(String url){
            ImageUtils.loadImage(Api.API_IMAGE_W185,url,mIvPoster);
        }
    }
}
