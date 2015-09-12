package com.carpediem.randy.shanbay.module.detail.business;

import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.ArrayMap;

import com.carpediem.randy.shanbay.common.ShanBayContext;
import com.carpediem.randy.shanbay.common.database.entry.WordData;
import com.carpediem.randy.shanbay.utils.LevelUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by randy on 15-9-12.
 */
public class ContentModel {
    private final static String TAG = "ContentModel";
    private final static String REGEX = "\\p{P}"; //有符号
    /**
     * 获得高亮文字
     * @param data
     * @param level  需要高亮的等级
     * @return
     */
    public static Spannable getSpannableString(String data,int level) {
        if (TextUtils.isEmpty(data) || LevelUtil.isBeyondLevelRange(level)) {
            throw new IllegalArgumentException(TAG+"getSpannableString argument is null");
        }
        String[] list = data.split(" "); //将文章按照" " 截断　//TODO:暂时不考虑段落的问题
        // 获得等于特定等级的单词
        List<WordData> wordList =
        ShanBayContext.getWordDbService().getDataBelowLevel(level);

        //转换列表为ｍａｐ
        Map<String,Integer> wordmap = getWordMap(wordList);

        if (wordmap == null || wordmap.isEmpty()) {
            throw new RuntimeException(TAG+"wordmap is null or empty");
        }
        // 匹配英语单词的正则表达式
        Pattern pattern = Pattern.compile(REGEX);

        // spannable builder
        SpannableStringBuilder sb = new SpannableStringBuilder(""); //开头的空格
        for( String str: list) {
            String realStr = str;
            boolean hasDot = false;
            if (pattern.matcher(str).find()) { //如果找到了符号
                realStr = str.substring(0,str.length() -1);
                hasDot = true;
            }
            SpannableString spannableString = new SpannableString(realStr);
            if (wordmap.containsKey(realStr)) {  //没有找到，所以就不用高亮

                spannableString.setSpan(new ForegroundColorSpan(Color.BLUE)
                                    ,0,realStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }

            sb.append(spannableString);
            if (hasDot) {
                sb.append(str.charAt(str.length()-1)); //最后一位的标点
            }
            sb.append(" ");
        }

        return sb;
    }

    private static Map<String,Integer> getWordMap(List<WordData> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Map<String,Integer> res ;
        // 版本适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
             res = new ArrayMap<String, Integer>();
        } else {
             res = new HashMap<String, Integer>();
        }
        for( WordData data: list) {
            res.put(data.getWord(),data.getLevel());
        }
        return res;
    }
}
