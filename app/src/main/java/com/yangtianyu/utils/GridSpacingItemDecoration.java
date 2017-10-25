package com.yangtianyu.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yangtianyu on 2017/10/25.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public GridSpacingItemDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

    }
}
