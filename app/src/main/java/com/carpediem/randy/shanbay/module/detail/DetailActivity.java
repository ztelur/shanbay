package com.carpediem.randy.shanbay.module.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.carpediem.randy.shanbay.R;
import com.carpediem.randy.shanbay.common.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by randy on 15-9-10.
 */
public class DetailActivity extends BaseActivity{
    private TextView mTitle;
    private SimpleDraweeView mImage;
    private TextView mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 初始化界面组件
     */
    private void initView() {
        mTitle = (TextView)findViewById(R.id.article_title);
        mImage = (SimpleDraweeView)findViewById(R.id.simpleView);
       mContent = (TextView)findViewById(R.id.content);

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

        // 高亮筛选
        if (id == R.id.action_select) {

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
