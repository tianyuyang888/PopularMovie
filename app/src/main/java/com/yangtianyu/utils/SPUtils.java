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
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (object instanceof Integer){
            edit.putInt(BaseApplication.getContext().getString(key), (Integer) object);
        }else if (object instanceof String){
            edit.putString(BaseApplication.getContext().getString(key), (String) object);
        }else if (object instanceof Boolean){
            edit.putBoolean(BaseApplication.getContext().getString(key), (Boolean) object);
        }else if (object instanceof Float){
            edit.putFloat(BaseApplication.getContext().getString(key), (Float) object);
        }else if (object instanceof Long){
            edit.putLong(BaseApplication.getContext().getString(key), (Long) object);
        }else {
            edit.putString(BaseApplication.getContext().getString(key), object.toString());
        }
        edit.apply();
    }

    public static Object get(int key,Object defaultObject){
        SharedPreferences sharedPreferences = BaseApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        if (defaultObject instanceof Integer){
            return sharedPreferences.getInt(BaseApplication.getContext().getString(key), (Integer) defaultObject);
        }else if (defaultObject instanceof String){
            return sharedPreferences.getString(BaseApplication.getContext().getString(key), (String) defaultObject);
        }else if (defaultObject instanceof Boolean){
            return sharedPreferences.getBoolean(BaseApplication.getContext().getString(key), (Boolean) defaultObject);
        }else if (defaultObject instanceof Float){
            return sharedPreferences.getFloat(BaseApplication.getContext().getString(key), (Float) defaultObject);
        }else if (defaultObject instanceof Long){
            return sharedPreferences.getLong(BaseApplication.getContext().getString(key), (Long) defaultObject);
        }
        return null;
    }
}
