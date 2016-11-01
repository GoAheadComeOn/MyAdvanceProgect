package com.lsh.advance.myadvanceprogect.app;

import android.app.Application;

/**
 * Created by DELL on 2016/8/14.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    public static final long ONE_KB = 1024L;
    public static final long ONE_MB = ONE_KB * 1024L;
    public static final long CACHE_DATA_MAX_SIZE = ONE_MB * 3L;
    private String token;
    public static String currentGirl = "http://ww2.sinaimg.cn/large/610dc034jw1f5k1k4azguj20u00u0421.jpg";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static MyApplication getIntstance() {
        return instance;
    }
}
