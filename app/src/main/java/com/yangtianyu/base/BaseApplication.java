package com.yangtianyu.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by yangtianyu on 2018/1/17.
 */

public class BaseApplication extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}
