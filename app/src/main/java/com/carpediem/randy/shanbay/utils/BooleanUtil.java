package com.carpediem.randy.shanbay.utils;

import com.carpediem.randy.shanbay.common.ShanbayConfig;

/**
 * Created by randy on 15-9-11.
 */
public class BooleanUtil {

    public static boolean isReadInt2Bool(int type) {
        if ( type != ShanbayConfig.ISREAD && type != ShanbayConfig.UNREAD) {
            throw new IllegalArgumentException("BooleanUtil isReadInt2Bool type is wrong "+ type);
        }
        return type == ShanbayConfig.ISREAD ? true : false;
    }

    public static int isReadBool2Int(boolean isRead) {
        return isRead ? ShanbayConfig.ISREAD : ShanbayConfig.UNREAD;
    }
}
