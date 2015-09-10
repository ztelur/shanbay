package com.carpediem.randy.shanbay.common.database;

import android.content.Context;
import android.text.TextUtils;

import com.carpediem.randy.shanbay.common.database.entry.ArticleData;
import com.carpediem.randy.shanbay.common.database.entry.DbCacheData;

import java.util.List;

/**
 * Created by randy on 15-9-10.
 *
 * 文章数据库的服务类
 */
public class ArticleDbService extends DbService{
    private final static String TAG = "ArticleDbService";
    /**
     * 文章存放的数据库管理器
     */
    private DbManager<ArticleData> mArticleManager;


    public ArticleDbService(Context context) {
        super(context);
    }

    /**
     * 获得数据库数据
     * @return
     */
    public List<ArticleData> getArticleList() {
        mArticleManager = ensureManager(ArticleData.class,ArticleData.TABLE_NAME);
        if (mArticleManager == null) {
            return null;
        }
        synchronized (this) {
            return mArticleManager.getDatas();
        }
    }

    public void saveArticleList(List<ArticleData> dataList) {
        mArticleManager = ensureManager(ArticleData.class,ArticleData.TABLE_NAME);

        synchronized (this) {
            mArticleManager.saveData(dataList);
        }
    }

}
