package com.carpediem.randy.shanbay.module.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.carpediem.randy.shanbay.R;
import com.carpediem.randy.shanbay.common.BaseActivity;
import com.carpediem.randy.shanbay.common.ShanBayContext;
import com.carpediem.randy.shanbay.common.ShanbayConfig;
import com.carpediem.randy.shanbay.common.database.entry.ArticleData;
import com.carpediem.randy.shanbay.module.detail.DetailActivity;
import com.carpediem.randy.shanbay.utils.ArticleNumUtil;
import com.carpediem.randy.shanbay.utils.FileUtil;
import com.carpediem.randy.shanbay.widget.banner.BannerView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private final static String TAG = "MainActivity";
    /**
     * 界面ＵＩ
     */

    private BannerView mBanner;
    private ListView mListView;

    private ArticleAdapter mAdapter;
    /**
     * 数据
     */
    private List<ArticleData> mDataList = new ArrayList<ArticleData>();


    /**
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
        initView();
    }

    private void findView() {
        mListView = (ListView)findViewById(R.id.listview);
    }
    private void initData() {
        mDataList = ShanBayContext.getArticleDbService().getArticleList();
    }

    private void initView() {
        mAdapter = new ArticleAdapter(this,mDataList);
        if (mListView != null) {
            mListView.setAdapter(mAdapter);
            mListView.setOnItemClickListener(this);
        }
        mAdapter.notifyDataSetChanged();
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



    // =========================== adapter  ====================================
    private class ArticleAdapter extends BaseAdapter {
        private List<ArticleData> adapterDataList;
        private LayoutInflater mLayoutInflater;

        public ArticleAdapter (Context context , List<ArticleData> dataList1) {
            this.adapterDataList = dataList1;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return adapterDataList.size();
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
            ViewHolder viewHolder;

            if (view == null || ! (view.getTag() instanceof ViewHolder)) {
                viewHolder = new ViewHolder();
                view = mLayoutInflater.inflate(R.layout.listview_item,viewGroup,false);
                viewHolder.mTitle = (TextView)view.findViewById(R.id.course_title);
                viewHolder.mContent = (TextView)view.findViewById(R.id.content);
                viewHolder.mCardView = (CardView)view.findViewById(R.id.card_view);
                viewHolder.mDraweeView = (SimpleDraweeView)view.findViewById(R.id.simpleView);
            } else {
                viewHolder = (ViewHolder)view.getTag();
            }

            // 初始化ＵＩ
            ArticleData data = adapterDataList.get(i);
            if (viewHolder.mTitle != null) {
                //TODO: titleUtil转换
//                viewHolder.mTitle.setText(ArticleNumUtil.dataStrToTextStr(data.getId()));
                viewHolder.mTitle.setText(data.getTitle());
            }
            if (viewHolder.mContent != null) {
                String content = FileUtil.readStringFromPath(data.getId());
                viewHolder.mContent.setText(content);
            }
            if (viewHolder.mDraweeView != null) {
                Uri uri = Uri.parse(data.getUrl());
                viewHolder.mDraweeView.setImageURI(uri);
            }

            return view;
        }
    }
    private class ViewHolder {
        TextView mTitle;  //unit 1 lesson 1
        TextView mContent; //已经读过的标志
        CardView mCardView;
        SimpleDraweeView mDraweeView;
    }

    // ====================== onItemClickListener ==================================


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mAdapter == null || mDataList.size() < i) {
            return;
        }
        ArticleData data = mDataList.get(i);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(ShanbayConfig.DETAILACTIVITY_EXTRA,data);
        startActivity(intent);
    }
}

