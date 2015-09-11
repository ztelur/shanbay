package com.carpediem.randy.shanbay.common.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import android.util.Log;

import com.carpediem.randy.shanbay.common.database.entry.DbCacheData;
import com.carpediem.randy.shanbay.utils.LogUtil;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by randy on 15-9-10.
 */
public class DbManager<T extends DbCacheData> {
    private final static String TAG = "DbManager";
    private final static String ID = "id";

    private final String mTable;
    private final DbCacheDataBase mDataBase;
    private  DbCacheData.DbCreator<T> mDataCreator;
    private final List<DbCacheData.Structure> mStrutureList = new ArrayList<DbCacheData.Structure>();

    /**
     * 默认查询数据
     */
    private String mSelection = null;
    private String mSortOrder = null;
    private int offset = 0;
    private int countLimit = 1000;

    /**
     * 数据库状态
     */
    private  boolean mTableCreated = false;

    /**
     *
     * @param context
     * @param clazz
     */
    public DbManager(Context context,Class<T> clazz) {

        mDataBase = DbCacheDataBase.getInstance(context);
        mTable = clazz.getSimpleName();
        initialize(clazz);
        createTableIfNeed();
    }

    /**
     * 建立数据库表
     */
    private void createTableIfNeed() {
        if (!mTableCreated) {
            SQLiteDatabase db = getDataBase();
            try {
                String sql = generateSqlForCreate();
                db.execSQL(sql);
                mTableCreated = true;
            } catch (Throwable e) {
                LogUtil.e(TAG,"fail to create the table");

            }
        }

    }

    /**
     * 获得ｓｑｌ语句
     * @return
     */
    private String generateSqlForCreate() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS " + mTable+" (");
        sb.append(ID+" INTEGER PRIMARY KEY");

        for(DbCacheData.Structure structure : mStrutureList) {
            String name = structure.getName();
            String type = structure.getType();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(type)) {
                continue;
            }
            sb.append(',');
            sb.append(name);
            sb.append(" ");
            sb.append(type);
        }
        sb.append(")");
        LogUtil.i(TAG,"the create sql is "+sb.toString());
        return sb.toString();
    }


    /**
     * 初始化数据库
     * @param clazz
     */
    private void initialize(Class<T> clazz) {
        DbCacheData.DbCreator<T> creator = null;
        String name  = clazz.getName();
        try {
            Field f = clazz.getField("DB_CREATOR");
            creator = (DbCacheData.DbCreator<T>) f.get(null);
        } catch (IllegalAccessException e) {
            LogUtil.e(TAG, "class not found when access "+name+",e :"+e);
        } catch (ClassCastException e) {
            LogUtil.e(TAG, "class cast   "+name+",e :"+e);
        } catch (NoSuchFieldException e) {
            LogUtil.e(TAG, "class have no creator "+name+",e :"+e);
        }
        if (creator == null) {
            throw new RuntimeException(TAG+"init "+name+"creator is null");
        }
        mDataCreator = creator;
        DbCacheData.Structure[] structures = creator.structure();
        if (structures != null) {
            for( DbCacheData.Structure structure : structures) {
                mStrutureList.add(structure);
            }
        }

        if (mStrutureList.isEmpty()) {
            throw new RuntimeException("DbCacheData structure is null"+name);
        }

    }

    /**
     * 获得数据库
     * @return
     */
    protected final SQLiteDatabase getDataBase() {
        if (mDataBase == null) {
            throw  new RuntimeException("getDatabase when mDataBase is null");
        }
        return mDataBase.getWritableDatabase();
    }

    // ================================= database insert update query delete  ===============
    protected Cursor queryCursor(String selection,String sortOrder,String limit) {
        SQLiteDatabase db = getDataBase();
        if (db == null) {
            return null;
        }
        Cursor cursor = null;
        try {
            cursor = query(db,selection,sortOrder,limit);
        } catch (Throwable e) {
            LogUtil.e(TAG,"fail to obtain cursor for "+selection+" "+e);
        }
        return cursor;
    }


    // ========================== base operation ==========================================
    private final Cursor query(SQLiteDatabase db,String selection,String sortOrder,String limit) {
        if (sortOrder == null) {
            sortOrder = mDataCreator.sortOrder();
        }
        return db.query(mTable,null,selection,null,null,null,sortOrder,limit);
    }

    private synchronized long  insert(SQLiteDatabase db,DbCacheData data,ContentValues contentValues) {
        final ContentValues values;
        if (contentValues != null) {
            contentValues.clear();
            values = contentValues;
        } else {
            values = new ContentValues();
        }
        data.writeTo(values);

        try {
            SQLiteStatement stmt = getStatement();
            stmt.clearBindings();
            for (int i = 0; i < mStrutureList.size(); i++) {
                String name = mStrutureList.get(i).getName();
                if (TextUtils.isEmpty(name)) {
                    continue;
                }
                DatabaseUtils.bindObjectToProgram(stmt, i + 1, values.get(name));
            }
            return stmt.executeInsert();
        }
        catch (Exception e) {
            LogUtil.e(TAG,"insert fail"+e);
            e.printStackTrace();
            return -1;
        }

    }

    private SQLiteStatement getStatement () {
        String sql = buildSQL();
        SQLiteStatement sqLiteStatement = getDataBase().compileStatement(sql);
        return sqLiteStatement;
    }
    private String buildSQL() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(mTable);
        sql.append(" (");

        int i = 0;
        for (DbCacheData.Structure structure : mStrutureList) {
            String name = structure.getName();
            if (TextUtils.isEmpty(name)) {
                continue;
            }
            sql.append((i > 0) ? "," : "");
            sql.append(name);
            i++;
        }
        sql.append(")");
        sql.append(" VALUES (");

        for(i=0;i< mStrutureList.size();i++) {
            sql.append((i >0) ? ",?" : "?");
        }
        sql.append(")");
        return sql.toString();
    }

    /**
     * 获得所有数据
     * @return
     */
    public List<T> getDatas() {
        return getDatas(mSelection,mSortOrder,countLimit);
    }

    public int saveData(List<T> data) {
        if (data == null || data.isEmpty()) {
            LogUtil.e(TAG,"saveData data is null or empty");
            return 0;
        }
        SQLiteDatabase db = getDataBase();
        if (db == null) {
            LogUtil.e(TAG,"saveData db is null");
            return 0;
        }
        int count = 0;
        try {
            db.beginTransaction();
            ContentValues container = new ContentValues();
            for (DbCacheData oneData : data) {
                long rowId = insert(db,oneData,container);
                if (rowId != -1) {
                    count ++;
                }
            }
            db.setTransactionSuccessful();
        } catch (Throwable e) {
            LogUtil.e(TAG,"fail to save data");
        } finally {
            try {
                db.endTransaction();
            } catch (Throwable e) {
                LogUtil.e(TAG,"fail to save data");
            }
        }
//        notifyDataChanged();
        return count;
    }

    /**
     * 获得符合条件的所有数据
     * @param filter
     * @param sortOrder
     * @param limit
     * @return
     */
    public List<T> getDatas(String filter,String sortOrder,int limit) {
        synchronized (this) {
            Cursor cursor = queryCursor(filter,sortOrder,String.valueOf(limit));
            if (cursor == null) {
                return null;
            }

            try {
                int count = cursor.getCount();
                List<T> list = new ArrayList<T>();
                for(int i=0; i<count;i++) {
                    T data = get(cursor,i);
                    if (data != null) {
                        list.add(data);
                    }
                }
                return list;
            } finally {
                if (cursor != null && ! cursor.isClosed()){
                    try {
                        cursor.close();
                    } catch (Throwable e) {

                    }
                }
            }
        }
    }

    /**
     * 获得ｃｕｒｓｏｒ中某个位置的数据
     * @param cursor
     * @param index
     * @return
     */
    protected T get(Cursor cursor,int index) {
        if (cursor == null || cursor.isClosed()) {
            return null;
        }
        if (index < 0 || index > cursor.getCount()) {
            return null;
        }
        if (!cursor.moveToPosition(index)) {
            return null;
        }
        return mDataCreator.createFromCursor(cursor);
    }
}
