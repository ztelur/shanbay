package com.carpediem.randy.shanbay.common.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.carpediem.randy.shanbay.common.ShanbayConfig;
import com.carpediem.randy.shanbay.common.database.entry.DbCacheData;

/**
 * Created by randy on 15-9-10.
 */
public class DbCacheDataBase extends SQLiteOpenHelper {
    public static Object lock = new Object();
    public DbCacheDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbCacheDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    private static DbCacheDataBase mInstance ;
    public static DbCacheDataBase getInstance(Context context) {
        DbCacheDataBase instance = mInstance;
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    int version = ShanbayConfig.getDataBaseVersion();
                    String name = ShanbayConfig.getDataBaseName();
                    mInstance = new DbCacheDataBase(context,name,null,version);
                }
            }
        }
        return mInstance;
    }
}
