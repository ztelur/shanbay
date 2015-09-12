package com.carpediem.randy.shanbay.common.initalizer;

import android.content.SharedPreferences;
import android.content.res.AssetManager;

import com.carpediem.randy.shanbay.common.ShanBayContext;
import com.carpediem.randy.shanbay.common.ShanbayConfig;
import com.carpediem.randy.shanbay.common.database.entry.ArticleData;
import com.carpediem.randy.shanbay.common.database.entry.WordData;
import com.carpediem.randy.shanbay.utils.FileUtil;
import com.carpediem.randy.shanbay.utils.LogUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by randy on 15-9-11.
 */
public class TestInitalizer {
    private final static String TAG = "TestInitalizer";

    /**
     * 只有第一次会初始化
     */
    public static void initalize() {
        SharedPreferences preferences =
                            ShanBayContext.getContext().getSharedPreferences(ShanbayConfig.PREFS_NAME,
                                    0);

        //　第一次运行，默认获得true
        if (preferences.getBoolean(ShanbayConfig.FIRST_RUN,true)) {

            testDatabase();
            //测试加密和json
            FileUtil.readStringFromPath("1_1");
            FileUtil.readStringFromPath("1_2");
            FileUtil.readStringFromPath("1_3");
            FileUtil.readStringFromPath("1_4");
            FileUtil.readStringFromPath("1_5");
            FileUtil.readStringFromPath("1_6");
            FileUtil.readStringFromPath("1_7");
            FileUtil.readStringFromPath("1_8");
            FileUtil.readStringFromPath("1_9");
            FileUtil.readStringFromPath("2_1");
            FileUtil.readStringFromPath("2_3");
            FileUtil.readStringFromPath("2_4");
            FileUtil.readStringFromPath("2_5");
            FileUtil.readStringFromPath("2_6");
            FileUtil.readStringFromPath("2_7");
            FileUtil.readStringFromPath("2_8");
            FileUtil.readStringFromPath("2_9");

            //修改第一次运行
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(ShanbayConfig.FIRST_RUN,false);
            editor.commit();
        }
    }



    private static void testDatabase() {
            LogUtil.d(TAG, "save article");
            ShanBayContext.getArticleDbService().saveArticleList(getTestData());
            List<ArticleData> datas = ShanBayContext.getArticleDbService().getArticleList();
            LogUtil.d(TAG,"save word");
            ShanBayContext.getWordDbService().saveDatas(getWordData());
        }
        private static List<WordData> getWordData() {
            List<WordData> res = new ArrayList<WordData>();
            AssetManager assetManager = ShanBayContext.getContext().getAssets();
            try {
                InputStream inputStream = assetManager.open("words");
                //读取每一行的内容
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = "";
                do {
                    line = reader.readLine();
                    //解析这一行啊
                    String[] str = line.split("\t");  //任意空格
                    if (str.length != 2) {
                        throw new RuntimeException("getWordData length !=2");
                    }

                    WordData data = new WordData();

                    data.setWord(str[0]);
                    data.setLevel(Integer.parseInt(str[1]));
                    res.add(data);
                } while (line != null);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return res;
        }

        static String[] titles = {
                "Finding fossil man","Spare that spider","Matterhorn man","Youth","The sporting spirit",
                "Bats","Trading standards","Royal espionage","Silicon valley","How to grow old",
                "Banks and their customers","The search for oil","The Butterfly Effect","Secrecy in industry",
                "The modern city","A man-made disease","Porpoises","The stuff of dreams"
        };
    static String[] urls = {
      "http://7xjsjy.com1.z0.glb.clouddn.com/shanbaydemo3.jpg",
            "http://7xjsjy.com1.z0.glb.clouddn.com/shanbaydemo4.jpg",
            "http://7xjsjy.com1.z0.glb.clouddn.com/shanbaydemo5.jpg",
            "http://7xjsjy.com1.z0.glb.clouddn.com/shanbaydemo6.jpg",
            "http://7xjsjy.com1.z0.glb.clouddn.com/shanbaydemo7.jpg",
            "http://7xjsjy.com1.z0.glb.clouddn.com/shanbaydemo8.jpg"
    };
        private static List<ArticleData> getTestData() {
            List<ArticleData> datas = new ArrayList<ArticleData>();

            for(int i=1;i<=2;i++) {
                for(int j=1;j<10;j++) {
                    ArticleData data = new ArticleData();
                    String any = String.valueOf(i);
                    data.setId(i+"_"+j);
                    data.setUrl(urls[(i+j)%6]);
                    data.setPath(any);
                    data.setTitle(titles[(i-1)*9+j-1]);
                    data.setRead(false);
                    datas.add(data);
                }
            }
            return datas;
        }
}
