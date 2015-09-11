package com.carpediem.randy.shanbay.module.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.carpediem.randy.shanbay.R;
import com.carpediem.randy.shanbay.common.BaseActivity;
import com.carpediem.randy.shanbay.common.ShanBayContext;
import com.carpediem.randy.shanbay.common.database.entry.ArticleData;
import com.carpediem.randy.shanbay.common.database.entry.WordData;
import com.carpediem.randy.shanbay.utils.FileUtil;
import com.carpediem.randy.shanbay.utils.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import static com.carpediem.randy.shanbay.common.ShanbayConfig.DETAILACTIVITY_BUNDLE_DATA;
import static com.carpediem.randy.shanbay.common.ShanbayConfig.DETAILACTIVITY_EXTRA;
/**
 * Created by randy on 15-9-10.
 */
public class DetailActivity extends BaseActivity{
    private final static String TAG = "DetailActivity";
    private String testUrl  = "http://7xjsjy.com1.z0.glb.clouddn.com/2009720142913830.jpg";

    /**
     * UI component
     */
    private TextView mTitle;
    private SimpleDraweeView mImage;
    private TextView mContent;


    /**
     * data
     */
    private ArticleData mData; //文章的属性等一堆数据
    private String mContentText; //文章的内容
    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findView();
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mData = intent.getParcelableExtra(DETAILACTIVITY_EXTRA);
            if (mData.getId().equalsIgnoreCase("1")) {
                mContentText = FileUtil.readStringFromPath("1_1");
            } else {
                mContentText = FileUtil.readStringFromPath("1_2");
            }
        }


        // init ImageView;

    }



    /**
     * 初始化界面组件
     */
    private void findView() {
        mTitle = (TextView)findViewById(R.id.article_title);
        mImage = (SimpleDraweeView)findViewById(R.id.simpleView);
       mContent = (TextView)findViewById(R.id.content);

    }

    private void initView() {
        if (mData == null) {
            return;
        }
        mTitle.setText(mData.getTitle());
        // 获得文本文件
        Uri uri = Uri.parse(mData.getUrl());
        if (mImage != null) {
            mImage.setImageURI(uri);
        }
        mContent.setText(mContentText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // 高亮筛选
        if (id == R.id.action_select) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    // ========================== data 相关  =======================================
    public void setData(ArticleData data) {
        if (data == null) {
            throw new IllegalArgumentException(TAG+" setData data is null");
        }
        this.mData = data;
    }
}
