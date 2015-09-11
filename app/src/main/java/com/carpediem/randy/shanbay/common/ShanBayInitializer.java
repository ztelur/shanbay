package com.carpediem.randy.shanbay.common;

import android.app.Application;

import com.carpediem.randy.shanbay.common.initalizer.DbCacheInitalizer;
import com.carpediem.randy.shanbay.common.initalizer.TestInitalizer;
import com.carpediem.randy.shanbay.utils.ProcessUtil;

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
        boolean isMainProcess = ProcessUtil.isMainProcess(application);

        if (isMainProcess) {
//            DbCacheInitalizer
        }
        TestInitalizer.initalize();
    }
}
