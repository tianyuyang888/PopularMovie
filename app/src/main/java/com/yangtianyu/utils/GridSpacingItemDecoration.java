package com.yangtianyu.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yangtianyu on 2017/10/25.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int line;

    public GridSpacingItemDecoration(int space,int line){
        this.space = space;
        this.line = line;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        //设置距离左右的间隔
        if (position%line == 0){
            outRect.right = space/2;
        }else if (position%line == line-1){
            outRect.left = space/2;
        }else {
            outRect.right = space/2;
            outRect.left = space/2;
        }
    }
}
