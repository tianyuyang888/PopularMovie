package com.yangtianyu.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yangtianyu.net.Api;

/**
 * Created by yangtianyu on 2017/10/25.
 */

public class ImageUtils {
    /**
     * picasso加载图片
     * @param url
     * @param iv
     */
    public static void loadImage(String size,String url, ImageView iv){
        if (TextUtils.isEmpty(url) || iv == null) return;
        Picasso.with(iv.getContext()).load(size + url).into(iv);
    }
}