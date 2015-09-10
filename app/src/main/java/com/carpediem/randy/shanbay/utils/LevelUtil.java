package com.carpediem.randy.shanbay.utils;

/**
 * Created by randy on 15-9-10.
 */
public class LevelUtil {
    public static boolean isBeyondLevelRange(int level) {
        return level < 1 || level > 3 ;
    }
}
