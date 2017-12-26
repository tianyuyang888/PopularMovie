package com.yangtianyu.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yangtianyu.popularmovie.R;

/**
 * Created by yangtianyu on 2017/10/25.
 */

public class ImageUtils {
    /**
     * picasso加载图片
     *
     * @param url
     * @param iv
     */
    public static void loadImage(String size, String url, ImageView iv) {
        if (TextUtils.isEmpty(url) || iv == null) return;
        Picasso p = Picasso.with(iv.getContext());
//        p.setIndicatorsEnabled(true); 左上角显示缓存状态
        p.load(size + url)
                .placeholder(R.drawable.picasso_placeholder)
                .centerCrop()
                .resize(UiUtils.getScreenWidth(iv.getContext()) / 2, UiUtils.getScreenWidth(iv.getContext()) * 7 / 10)
                .error(R.drawable.picasso_error)
                .into(iv);
    }
}
