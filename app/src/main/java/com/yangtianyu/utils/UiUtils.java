package com.yangtianyu.utils;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    /**
     * 显示加载界面
     * @param llRoot
     * @param tvLoading
     * @param pbLoading
     */
    public static void showLoading(LinearLayout llRoot, TextView tvLoading, ProgressBar pbLoading){
        if (llRoot!=null) llRoot.setVisibility(View.VISIBLE);
        if (pbLoading!=null) pbLoading.setVisibility(View.VISIBLE);
        if (tvLoading!=null) tvLoading.setText("正在加载中...");
    }

    /**
     * 显示网络错误界面
     * @param llRoot
     * @param tvLoading
     * @param pbLoading
     */
    public static void showNetError(LinearLayout llRoot, TextView tvLoading,ProgressBar pbLoading){
        if (llRoot!=null) llRoot.setVisibility(View.VISIBLE);
        if (pbLoading!=null) pbLoading.setVisibility(View.GONE);
        if (tvLoading!=null) tvLoading.setText("网络错误，请点击重试");
    }

    /**
     * 显示空布局
     * @param llRoot
     * @param tvLoading
     * @param pbLoading
     * @param content
     */
    public static void showNoData(LinearLayout llRoot, TextView tvLoading, ProgressBar pbLoading,String content){
        if (llRoot!=null) llRoot.setVisibility(View.VISIBLE);
        if (pbLoading!=null) pbLoading.setVisibility(View.GONE);
        if (tvLoading!=null) tvLoading.setText(content);
    }

    /**
     * 隐藏加载界面
     * @param llRoot
     */
    public static void hideLoading(LinearLayout llRoot){
        if (llRoot!=null) llRoot.setVisibility(View.GONE);
    }
}
