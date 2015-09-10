package com.carpediem.randy.shanbay.common.database;

import android.content.Context;

import com.carpediem.randy.shanbay.common.database.entry.WordData;
import com.carpediem.randy.shanbay.utils.LevelUtil;

import java.util.List;

/**
 * Created by randy on 15-9-10.
 */
public class WordDbService extends DbService{
    private final static String TAG = "WordDbService";
    private DbManager<WordData> mWordManager;


    public WordDbService(Context context) {
        super(context);
    }

    /**
     * 获得所有的单词
     * @return
     */
    public List<WordData> getDatas() {
        mWordManager = ensureManager(WordData.class,WordData.TABLE_NAME);
        if (mWordManager == null) {
            throw new RuntimeException(TAG+" getDatas when manager is null");
        }
        synchronized (this) {
            return mWordManager.getDatas();
        }
    }

    /**
     * 获得某一个等级上的单词
     * @param level
     * @return
     */
    public List<WordData> getDatasAtLevel(int level) {
        mWordManager = ensureManager(WordData.class,WordData.TABLE_NAME);
        if (mWordManager == null) {
            throw new RuntimeException(TAG+" getDatas when manager is null");
        }
        if (LevelUtil.isBeyondLevelRange(level)) {
            throw new IllegalArgumentException(TAG+" getDataAtLevel level beyond range");
        }
        synchronized (this) {
            //TODO: limit ???
            return mWordManager.getDatas(WordData.LEVEL + " = "+level,null,-1);
        }
    }

    /**
     * 获得　ｌｅｖｅｌ以下的单词
     * @param level
     * @return
     */
    public List<WordData> getDataBelowLevel(int level) {
        mWordManager = ensureManager(WordData.class,WordData.TABLE_NAME);
        if (mWordManager == null) {
            throw new RuntimeException(TAG+" getDatas when manager is null");
        }
        if (LevelUtil.isBeyondLevelRange(level)) {
            throw new IllegalArgumentException(TAG+" getDataAtLevel level beyond range");
        }
        synchronized (this) {
            //TODO: limit ???  limit

            return mWordManager.getDatas(WordData.LEVEL + " <= "+level,null,1000);
        }
    }

    public void saveDatas(List<WordData> dataList) {
        mWordManager = ensureManager(WordData.class,WordData.TABLE_NAME);
        if (mWordManager == null) {
            throw new RuntimeException(TAG+" getDatas when manager is null");
        }
        if (dataList == null || dataList.isEmpty()) {
            return ;
        }
        synchronized (this) {
            mWordManager.saveData(dataList);
        }
    }
}
