package com.zed.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.zed.recycler.view.LRecyclerView;
import com.zed.trips.R;
import com.zed.utils.LogUtils;

public class ClickRecyclerItemActivity extends BaseActivity {

    private LRecyclerView recycler_item_reclcler;
    private String share_url;

    @Override
    protected int getlayoutID() {
        return R.layout.activity_click_recycler_item;
    }

    @Override
    protected void initView() {
        //得到上一个界面穿过来的网址
        share_url = getIntent().getExtras().getString("share_url");
        recycler_item_reclcler = (LRecyclerView) findViewById(R.id.recycler_item_reclcler);

    }

    @Override
    public void initData() {
        super.initData();
        // LogUtils.d("share_url "+ share_url);
    }
}
