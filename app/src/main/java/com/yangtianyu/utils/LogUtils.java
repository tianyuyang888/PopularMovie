package com.yangtianyu.utils;

import android.text.TextUtils;

import com.yangtianyu.popularmovie.BuildConfig;

/**
 * Created by yangtianyu on 2017/10/25.
 */

public class LogUtils {
    public static final String customTagPrefix = "xg";
    public static boolean isDebug = BuildConfig.DEBUG;


    private LogUtils() {
    }


    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }


    public static void d(String content) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.d(tag, content);
    }

    public static void d(String tag, String content) {
        if (!isDebug) return;
        android.util.Log.d(tag, content);
    }


    public static void d(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.d(tag, content, tr);
    }


    public static void e(String content) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.e(tag, content);
    }


    public static void e(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.e(tag, content, tr);
    }


    public static void i(String content) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.i(tag, content);
    }


    public static void i(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.i(tag, content, tr);
    }


    public static void v(String content) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.v(tag, content);
    }


    public static void v(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.v(tag, content, tr);
    }


    public static void w(String content) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.w(tag, content);
    }


    public static void w(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.w(tag, content, tr);
    }


    public static void w(Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.w(tag, tr);
    }




    public static void wtf(String content) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.wtf(tag, content);
    }


    public static void wtf(String content, Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.wtf(tag, content, tr);
    }


    public static void wtf(Throwable tr) {
        if (!isDebug) return;
        String tag = generateTag();


        android.util.Log.wtf(tag, tr);
    }
}
