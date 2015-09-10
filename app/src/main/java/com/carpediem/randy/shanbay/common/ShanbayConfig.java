package com.carpediem.randy.shanbay.common;

import android.content.Context;

/**
 * Created by randy on 15-9-10.
 */
public class ShanbayConfig {
    public static final String DETAILACTIVITY_EXTRA = "extra";
    public static final String DETAILACTIVITY_BUNDLE_DATA = "articledata";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shanbay";
    public static int getDataBaseVersion() {
        return DATABASE_VERSION;
    }
    public static String getDataBaseName() {
        return DATABASE_NAME;
    }

}
