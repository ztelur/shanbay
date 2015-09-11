package com.carpediem.randy.shanbay.utils;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;

import com.carpediem.randy.shanbay.common.ShanBayContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by randy on 15-9-10.
 * 文件的加密，读取，和删除等操作
 */
public class FileUtil {
    private static final String TAG = "FileUtil";
    /**
     *  读取加密文件，解密，解析json然后返回content
     * @param num key num 和要读取的文件名相同
     * @return
     */
    public static String readStringFromPath(String num) {
        //TODO: 如果是正常应用，网络下载的保存的sd卡里，现在只是保存的assets中，可以利用
        // 反射读取
        if (TextUtils.isEmpty(num)) {
            throw new IllegalArgumentException(TAG+": readStringFromPath num is null");
        }

        String res = "";
        try {

            AssetManager am = ShanBayContext.getContext().getAssets();
            InputStream inputStream = am.open(num);
            // 解码
            String content = SecurityUtil.doDecryptFile1(inputStream);
            if (content == null) {
                throw new RuntimeException(TAG+" :"+"decrypt failed");
            }
            res = JsonUtil.parseJson(content, num);
            if (res == null) {
                throw new RuntimeException(TAG+" :"+"parse json failed");
            }
            LogUtil.i(TAG,"readString success");
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        LogUtil.e(TAG,"read failed");
        return res;
    }

}
