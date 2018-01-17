package com.yangtianyu.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yangtianyu.base.BaseApplication;

/**
 * Created by yangtianyu on 2018/1/17.
 */

public class SPUtils {

    private static final String SP_NAME = "yang_popular_sp";

    public static void put(int key, Object object){
        SharedPreferences sharedPreferences = BaseApplication.sContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (object instanceof Integer){
            edit.putInt(BaseApplication.sContext.getString(key), (Integer) object);
        }else if (object instanceof String){
            edit.putString(BaseApplication.sContext.getString(key), (String) object);
        }else if (object instanceof Boolean){
            edit.putBoolean(BaseApplication.sContext.getString(key), (Boolean) object);
        }else if (object instanceof Float){
            edit.putFloat(BaseApplication.sContext.getString(key), (Float) object);
        }else if (object instanceof Long){
            edit.putLong(BaseApplication.sContext.getString(key), (Long) object);
        }else {
            edit.putString(BaseApplication.sContext.getString(key), object.toString());
        }
        edit.apply();
    }

    public static Object get(int key,Object defaultObject){
        SharedPreferences sharedPreferences = BaseApplication.sContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        if (defaultObject instanceof Integer){
            return sharedPreferences.getInt(BaseApplication.sContext.getString(key), (Integer) defaultObject);
        }else if (defaultObject instanceof String){
            return sharedPreferences.getString(BaseApplication.sContext.getString(key), (String) defaultObject);
        }else if (defaultObject instanceof Boolean){
            return sharedPreferences.getBoolean(BaseApplication.sContext.getString(key), (Boolean) defaultObject);
        }else if (defaultObject instanceof Float){
            return sharedPreferences.getFloat(BaseApplication.sContext.getString(key), (Float) defaultObject);
        }else if (defaultObject instanceof Long){
            return sharedPreferences.getLong(BaseApplication.sContext.getString(key), (Long) defaultObject);
        }
        return null;
    }
}
