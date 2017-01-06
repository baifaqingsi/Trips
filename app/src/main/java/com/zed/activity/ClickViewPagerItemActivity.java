package com.zed.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.zed.trips.R;
import com.zed.utils.LogUtils;

public class ClickViewPagerItemActivity extends BaseActivity {

    private WebView wv;
    private Toolbar pager_toolbar;
    private TextView pager_toolbar_title;

    @Override
    protected int getlayoutID() {
        return R.layout.activity_click_view_pager_item;
    }

    @Override
    protected void initView() {
        wv = (WebView) findViewById(R.id.wv);
        pager_toolbar = (Toolbar) findViewById(R.id.pager_toolbar);
        pager_toolbar_title = (TextView) findViewById(R.id.pager_toolbar_title);
    }

    @Override
    public void initEvent() {
        super.initEvent();

        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //得到上一个界面穿过来的网址
        String html_url = extras.getString("html_url");
        //设置title
        WebChromeClient wvcc = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, final String title) {
                super.onReceivedTitle(view, title);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        pager_toolbar.setBackground(getDrawable(R.drawable.actionbar_bg));
                    }
                }
                LogUtils.d("title " + title);
                if (title.length() <= 10) {
                    pager_toolbar.setTitle("");
                    pager_toolbar_title.setText(title);
                } else {
                    pager_toolbar.setTitle("");
                    pager_toolbar_title.setText(title.substring(0, 10) + "...");
                }
                setSupportActionBar(pager_toolbar);
                //打开返回箭头
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                pager_toolbar.inflateMenu(R.menu.pager_share_menu);
                pager_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(ClickViewPagerItemActivity.this, "share", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                // txtTitle.setText("ReceivedTitle:" +title);
            }

        };
        // 设置setWebChromeClient对象
        wv.setWebChromeClient(wvcc);

        // 创建WebViewClient对象
        WebViewClient wvc = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
                wv.loadUrl(url);
                // 消耗掉这个事件。Android中返回True的即到此为止吧,事件就会不会冒泡传递了，我们称之为消耗掉
                return true;
            }
        };
        wv.setWebViewClient(wvc);
        wv.loadUrl(html_url);
    }

    /***
     * 复写此方法 才会显示出
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pager_share_menu, menu);
        return true;
    }
}
