package com.carpediem.randy.shanbay.common.database;

import android.content.Context;
import android.text.TextUtils;

import com.carpediem.randy.shanbay.common.database.entry.DbCacheData;
import com.carpediem.randy.shanbay.utils.LogUtil;

import java.util.HashMap;

/**
 * Created by randy on 15-9-10.
 */
public class DbService {
    private final static String TAG = "DbService";

    /**
     * 数据库ｍａｎａｇｅｒ
     */
    private final HashMap<String,DbManager> mActiveManagers =
            new HashMap<String, DbManager>();
    private Context mContext;

    public DbService(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 使用时在建立
     * @param clazz
     * @param name
     * @return
     */
    protected final DbManager ensureManager(Class clazz,String name) {
        return getDbManager(clazz,name);
    }

    private <T extends DbCacheData> DbManager<T> getDbManager(Class<T> clazz,String table) {
        if (clazz == null) {
            throw new RuntimeException("Invalid Dbcache class");
        }
        if (TextUtils.isEmpty(table)) {
            throw new RuntimeException("Invalid DbCache table name");
        }
        synchronized (mActiveManagers) {
            DbManager dbManager = mActiveManagers.get(table);
            if (dbManager == null ) {
                DbManager<T> cacheManager = new DbManager<T>(mContext,clazz);
                mActiveManagers.put(table,cacheManager);
                LogUtil.i(TAG,"tables: "+table+" build");
                return cacheManager;
            } else {
                return dbManager;
            }
         }
    }
}
