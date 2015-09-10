package com.carpediem.randy.shanbay;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.carpediem.randy.shanbay.common.ShanBayContext;
import com.carpediem.randy.shanbay.common.ShanBayInitializer;

/**
 * Created by randy on 15-9-10.
 */
public class ShanBayApplication extends Application{
    private final static String TAG = "ShanBayApplication";
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        ShanBayContext.setsApplication(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"initialize");
        ShanBayInitializer.initialize(this);
        Log.i(TAG,"init finished");
    }

    //TODO: 处理低内存的状况 优先级 3
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
