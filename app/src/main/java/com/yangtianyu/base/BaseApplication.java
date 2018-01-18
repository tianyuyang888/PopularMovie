package com.yangtianyu.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by yangtianyu on 2018/1/17.
 */

public class BaseApplication extends Application {
    static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }

    public static Context getContext(){
        return sContext;
    }
}
