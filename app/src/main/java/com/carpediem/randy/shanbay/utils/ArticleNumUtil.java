package com.carpediem.randy.shanbay.utils;

/**
 * Created by randy on 15-9-11.
 *数据库保存的1-1 类型的数据转换为 unit 1 lesson 1
 */
public class ArticleNumUtil {
    public static String dataStrToTextStr(String data) {
        StringBuilder sb = new StringBuilder("Unit ");
        String[] strings = data.split("_");
        if (strings.length == 1) { //没有-分割,为了匹配测试数据，默认作为unit
            sb.append(data);
            return sb.toString();
        } else if (strings.length == 2) { // 正常情况
            sb.append(strings[0]);
            sb.append(" lesson ");
            sb.append(strings[1]);
            return sb.toString();
        } else {  // exception
            throw new IllegalArgumentException("ArticleNumUtil is wrong with "+data);
        }

    }
}
