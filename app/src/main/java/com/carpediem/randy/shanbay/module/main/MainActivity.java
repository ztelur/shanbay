package com.carpediem.randy.shanbay.module.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.carpediem.randy.shanbay.R;
import com.carpediem.randy.shanbay.common.BaseActivity;
import com.carpediem.randy.shanbay.common.database.entry.ArticleData;
import com.carpediem.randy.shanbay.widget.banner.BannerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    /**
     * 界面ＵＩ
     */

    private BannerView mBanner;
    private ListView mListView;

    /**
     * 数据
     */
    private List<ArticleData> dataList = new ArrayList<ArticleData>();


    /**
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // ============================== test function ==================================

    private List<ArticleData> getTestData() {
        List<ArticleData> datas = new ArrayList<ArticleData>();
        for(int i=0;i<10;i++) {
            ArticleData data = new ArticleData();
            String any = String.valueOf(i);
            data.setId(any);
            data.setUrl(any);
            data.setPath(any);
            data.setTitle(any);
        }
        return datas;
    }

    private class ArticleAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            return null;
        }
    }
    private class ViewHolder {
        TextView mTitle;
    }
}
