package com.carpediem.randy.shanbay.utils;

import android.util.Log;

/**
 * Created by randy on 15-9-10.
 * 打印日志的log，release版本可以在日志文件中打印日志
 */
public class LogUtil {
    private static boolean isStoreInFile = false;
    public static void i(String tag,String msg) {
        if (isStoreInFile) {
            storeInFile();
        }
        Log.i(tag,msg);
    }
    public static void e(String tag,String msg) {
        if (isStoreInFile) {
            storeInFile();
        }
        Log.e(tag,msg);
    }
    public static void d(String tag,String msg) {
        if (isStoreInFile) {
            storeInFile();
        }
        Log.d(tag,msg);
    }
    public static void storeInFile() {

    }
}
