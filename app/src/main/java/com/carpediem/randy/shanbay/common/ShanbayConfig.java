package com.carpediem.randy.shanbay.common;

import android.content.Context;

import com.carpediem.randy.shanbay.utils.LogUtil;

import java.security.Key;
import java.security.KeyRep;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by randy on 15-9-10.
 */
public class ShanbayConfig {
    private static final String TAG = "ShanbayConfig";

    public static final String DETAILACTIVITY_EXTRA = "extra";
    public static final String DETAILACTIVITY_BUNDLE_DATA = "articledata";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shanbay";

    public static final int ISREAD = 1;
    public static final int UNREAD = 2;
    public static int getDataBaseVersion() {
        return DATABASE_VERSION;
    }
    public static String getDataBaseName() {
        return DATABASE_NAME;
    }

    public static final String ROOT_PATH = ""; //网络下载文件保存的位置

    // ============================ 加密 =============================================
    /**
     * 文件加密的秘钥
     */
    public static  Key KEY;
    public static  Cipher DECRYPTCIPHER; //解密的秘钥
    public static  Cipher ENCRYPTCIPHER; // 加密的秘钥
    private static final String KEYRULE = "shanbayDEMO";
    /**
     * 初始化文件加密数据
     */
    static  {
        byte[] keyTemp = KEYRULE.getBytes();
        byte[] key = new byte[8];
        System.arraycopy(keyTemp, 0, key, 0, 8);
        KEY = new SecretKeySpec(key,"DES");
        try {
            ENCRYPTCIPHER = Cipher.getInstance("DES");
            ENCRYPTCIPHER.init(Cipher.ENCRYPT_MODE,KEY);

            DECRYPTCIPHER = Cipher.getInstance("DES");
            DECRYPTCIPHER.init(Cipher.DECRYPT_MODE,KEY);
        } catch (Exception e) {
            LogUtil.e(TAG,"Cipher.getInstance wrong");
            e.printStackTrace();
        }

    }

    //  =====================第一次运行初始化 ====================================
    public static final String PREFS_NAME = "prefs_data";
    public static final String FIRST_RUN = "first_run";
    public static final int FIRST_RUN_NUM = 1;

    // ========================= level ============================================
    public static final int LEVEL_MAX = 5;
}
