package com.carpediem.randy.shanbay.common.initalizer;

import com.carpediem.randy.shanbay.common.ShanBayContext;
import com.carpediem.randy.shanbay.common.database.entry.ArticleData;
import com.carpediem.randy.shanbay.common.database.entry.WordData;
import com.carpediem.randy.shanbay.utils.FileUtil;
import com.carpediem.randy.shanbay.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by randy on 15-9-11.
 */
public class TestInitalizer {
    private final static String TAG = "TestInitalizer";
    public static void initalize() {
//        testDatabase();
        //测试加密和json
        FileUtil.readStringFromPath("1_1");
        FileUtil.readStringFromPath("1_2");
        FileUtil.readStringFromPath("1_2");
        FileUtil.readStringFromPath("1_2");

    }
    private static void testDatabase() {
            LogUtil.d(TAG, "save article");
            ShanBayContext.getArticleDbService().saveArticleList(getTestData());
            List<ArticleData> datas = ShanBayContext.getArticleDbService().getArticleList();
            if (datas == null) {
                return;
            }
            for(ArticleData data : datas) {
                LogUtil.d(TAG,"the data is "+data.getId()+data.getPath()+data.getTitle()+data.getUrl());
            }

            LogUtil.d(TAG,"save word");

            ShanBayContext.getWordDbService().saveDatas(getWordData());

            List<WordData> datas2 = ShanBayContext.getWordDbService().getDatas();
            if (datas2 == null) {
                return;
            }
            for(WordData data : datas2) {
                LogUtil.d(TAG,"the word is "+data.getWord()+" "+data.getLevel());
            }

        }
        private static List<WordData> getWordData() {
            List<WordData> datas = new ArrayList<WordData>();
            for (int i=0;i<10;i++) {
                WordData data = new WordData();
                data.setLevel(i % 3);
                data.setWord(String.valueOf(i));
                datas.add(data);
            }
            return datas;
        }
        private static List<ArticleData> getTestData() {
            List<ArticleData> datas = new ArrayList<ArticleData>();
            for(int i=0;i<10;i++) {
                ArticleData data = new ArticleData();
                String any = String.valueOf(i);
                data.setId(any);
                data.setUrl("http://7xjsjy.com1.z0.glb.clouddn.com/2009720142913830.jpg");
                data.setPath(any);
                data.setTitle(any);
                data.setRead(false);
                datas.add(data);
            }
            ArticleData data = new ArticleData();
            data.setId("test");
            data.setUrl("http://7xjsjy.com1.z0.glb.clouddn.com/2009720142913830.jpg");
            data.setPath("1");
            data.setTitle("Why are legends handed down by storytellers useful?");
            data.setRead(false);
            return datas;
        }
}
