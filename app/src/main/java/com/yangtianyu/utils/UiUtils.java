package com.yangtianyu.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by yangtianyu on 2017/10/30.
 */

public class UiUtils {

    /**
     * 获取手机屏幕的宽度
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
