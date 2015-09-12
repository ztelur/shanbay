package com.carpediem.randy.shanbay.common;

import android.app.Application;

import com.carpediem.randy.shanbay.common.initalizer.TestInitalizer;

/**
 * Created by randy on 15-9-10.
 * 初始化构造器
 */
public class ShanBayInitializer {
    private final static String TAG = "ShanBayInitializer";

    /**
     * 初始化函数
     * @param application
     */
    public static void initialize(Application application) {
        TestInitalizer.initalize();
    }
}
