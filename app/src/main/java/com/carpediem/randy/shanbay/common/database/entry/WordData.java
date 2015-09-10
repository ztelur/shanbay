package com.carpediem.randy.shanbay.common.database.entry;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.UserDictionary;

/**
 * Created by randy on 15-9-10.
 */
public class WordData implements Parcelable,DbCacheData{
    /**
     * 数据库结构
     */
    //TODO:主键是？ｗｏｒｄ　ｏｒ id
    public final static String WORD = "word",TYPE_WORD = "TEXT";
    public final static String LEVEL = "level",TYPE_LEVEL = "INTEGER";


    public final static String TABLE_NAME = "word";
    /**
     * ｂｅａｎ
     */
    private String word;
    private int level;

    // =============================== getter and setter ==================================

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

     // ===================================  parcelable ==================================
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }


    // ================================== dbCacheData ===================================
    @Override
    public void writeTo(ContentValues contentValues) {
        contentValues.put(WORD,word);
        contentValues.put(LEVEL,level);

    }

    public static final DbCreator<WordData> DB_CREATOR = new DbCreator<WordData>() {
        @Override
        public Structure[] structure() {
            Structure word = new Structure(WORD,TYPE_WORD);
            Structure level = new Structure(LEVEL,TYPE_LEVEL);
            return new Structure[] {
                    word,
                   level
            };
        }

        @Override
        public String sortOrder() {
            return null;
        }

        @Override
        public WordData createFromCursor(Cursor cursor) {
            WordData data = new WordData();
            data.setWord(cursor.getString(cursor.getColumnIndex(WORD)));
            data.setLevel(cursor.getInt(cursor.getColumnIndex(LEVEL)));
            return data;
        }

        @Override
        public int version() {
            return 1;
        }
    };
}
