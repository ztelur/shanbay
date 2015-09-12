package com.carpediem.randy.shanbay.utils;

import com.carpediem.randy.shanbay.common.ShanbayConfig;

/**
 * Created by randy on 15-9-10.
 */
public class LevelUtil {
    public static boolean isBeyondLevelRange(int level) {
        return level < 1 || level > ShanbayConfig.LEVEL_MAX;
    }
}
