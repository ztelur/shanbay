package com.carpediem.randy.shanbay.utils;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by randy on 15-9-11.
 */
public class JsonUtil {
    private static final String TAG = "GsonUtil";
    /**
     * 解析 json数据格式
     * @param data 从文件中读取的string
     * @return article的内容
     */
    public static String parseJson(String data,String num) {
        if (TextUtils.isEmpty(data) || TextUtils.isEmpty(num)) {
            return null;
        }
        try {
            JSONObject allData = new JSONObject(data);
            String number = allData.getString("number");
            String res = allData.getString("content");

            if (num.equalsIgnoreCase(number)) { //key相同
                LogUtil.i(TAG,"parse successful");
                return res;
            } else {
                LogUtil.e(TAG,"parseJson key num is not equal");
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtil.e(TAG,"default return null");
        return null;
    }
}
