package com.zed.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hc on 17-1-6.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getlayoutID());

        initView();

        initEvent();

        initData();
    }

    /***
     * 初始化此界面的主布局
     *
     * @return
     */
    protected abstract int getlayoutID();

    /***
     * 可实现 可不实现
     */
    public void initData() {

    }


    /***
     * 可实现 可不实现
     */
    public void initEvent() {

    }

    /***
     * 必须实现的方法 在此处初始化ID
     */
    protected abstract void initView();
}
