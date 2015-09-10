package com.carpediem.randy.shanbay.common.database.entry;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by randy on 15-9-10.
 */
public class ArticleData implements Parcelable,DbCacheData{
    /**
     * 数据库item
     */
    public static final String TABLE_NAME = "article";

    public static final String ID = "id",TYPE_ID = "TEXT";
    // 文章标题
    public static final String TITLE = "title",TYPE_TITLE = "TEXT";
    // 文章内容存放路径
    public static final String PATH = "path", TYPE_PATH = "TEXT";
    //文章封面url
    public static final String URL = "url",TYPE_URL = "TEXT";
    /**
     * bean filed
     */
    private String id; // unit1 lesson1
    private String title;
    private String path; //文章内容存储位置
    private String url; //图片url


    // ============================ getter and setter ====================================


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // ============================ parcelable ====================================
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    // ========================== dbcacheData =====================================


    @Override
    public void writeTo(ContentValues contentValues) {
        contentValues.put(ID,id);
        contentValues.put(TITLE,title);
        contentValues.put(PATH, path);
        contentValues.put(URL,url);
    }

    public static final DbCreator<ArticleData> DB_CREATOR = new DbCreator<ArticleData>() {
        @Override
        public Structure[] structure() {
            Structure id = new Structure(ID,TYPE_ID);
            Structure title = new Structure(TITLE,TYPE_TITLE);
            Structure path = new Structure(PATH,TYPE_PATH);
            Structure url = new Structure(URL,TYPE_URL);
            return new Structure[] {
                    id,
                    title,
                    path,
                    url
            };
        }

        @Override
        public String sortOrder() {
            return null;
        }

        @Override
        public ArticleData createFromCursor(Cursor cursor) {
            ArticleData data = new ArticleData();
            data.setId(cursor.getString(cursor.getColumnIndex(ID)));
            data.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
            data.setPath(cursor.getString(cursor.getColumnIndex(PATH)));
            data.setUrl(cursor.getString(cursor.getColumnIndex(URL)));
            return data;
        }

        @Override
        public int version() {
            return 1;
        }
    };

}
