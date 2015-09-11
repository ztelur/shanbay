package com.carpediem.randy.shanbay.utils;

import com.carpediem.randy.shanbay.common.ShanBayContext;
import com.carpediem.randy.shanbay.common.ShanbayConfig;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.Buffer;

import javax.crypto.CipherInputStream;

/**
 * Created by randy on 15-9-10.
 */
public class SecurityUtil {
    private static final String TAG = "SecurityUtil";
    //TODO:
    public static String encrypt(String str) {
      return str;
    };

    /**
     * 保存文件，并且加密
     * @param in 输入流
     */
    public static String  doEncryptFile(InputStream in) {
        if (in == null) {
            LogUtil.e(TAG,"doEncryptFile in is null");
            throw new IllegalArgumentException("Inputstream is null");
        }
        CipherInputStream cin = null;
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[2014];
        int len;
        try {
            cin = new CipherInputStream(in,ShanbayConfig.ENCRYPTCIPHER);
            while((len = cin.read(bytes)) >0 ) {
                sb.append(bytes);
            }
            cin.close();
            LogUtil.i(TAG,"加密成功");
        } catch ( Exception e) {
            LogUtil.e(TAG,"加密失败");

        } finally {
            try {
                if (cin != null) {
                    cin.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 读取加密文件
     * @param in 输入流
     */
    public static String doDecryptFile(InputStream in) {
        if (in  == null) {
            LogUtil.e(TAG,"inputStream in is null");
            throw new IllegalArgumentException("InputStream in is null");
        }
        CipherInputStream cin = null;
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            cin = new CipherInputStream(in, ShanbayConfig.DECRYPTCIPHER);
            reader = new BufferedReader(new InputStreamReader(cin));
            String line = null;
            while ((line = reader.readLine() ) != null) {
                sb.append(line);
            }
            reader.close();
            cin.close();
            LogUtil.i(TAG,"解密成功");
        } catch (Exception e) {
            LogUtil.e(TAG,"解密失败");
        } finally {
            try {
                if (cin != null) {
                    cin.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return sb.toString();
    }
}
