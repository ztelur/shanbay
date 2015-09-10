package com.carpediem.randy.shanbay.common.database.entry;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by randy on 15-9-10.
 */
public interface DbCacheData {
    /**
     * 写入数据库
     * @param contentValues
     */
    public void writeTo(ContentValues contentValues);

    /**
     * 提供一个ｐｕｂｌｉｃ的 creator
     * @param <T>
     */
    public static interface DbCreator<T extends DbCacheData> {
        /**
         * 返回数据库结构
         * @return
         */
        public Structure[] structure();

        /**
         * 排序
         * @return
         */
        public String sortOrder();

        /**
         * 从数据库查询数据中获取数据
         * @param cursor
         * @return
         */
        public T createFromCursor(Cursor cursor);

        /**
         * 返回版本号
         * @return
         */
        public int version();
    }

    /**
     *  db data structure
     */
    public static class Structure {
        private String mName;
        private String mType;

        public Structure(String name,String type) {
            mName = name;
            mType = type;
        }
        public String getName() {
            return this.mName;
        }
        public String getType() {
            return mType;
        }

    }
}
