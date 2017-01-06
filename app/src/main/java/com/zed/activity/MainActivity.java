package com.zed.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zed.trips.R;
import com.zed.utils.Constans;
import com.zed.utils.Util;
import com.zed.fragment.HotFragment;
import com.zed.fragment.OrderFragment;
import com.zed.fragment.SettingFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ImageView login_iv;
    private TextView login_tv;
    private View headerView;
    private View drawer_left;
    private View login_left;
    private View menu_left;
    private TextView drawer_item_three;
    private TextView drawer_item_two;
    private TextView drawer_item_one;
    private ListView menu_left_lv;

    @Override
    protected int getlayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void initEvent() {
        super.initEvent();
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mToolbar.setTitleTextAppearance(MainActivity.this,R.style.ToolbarTitleSize);
        drawer_left = findViewById(R.id.drawer_left);
        //左菜单登录模块
        login_left = drawer_left.findViewById(R.id.login_left);
        login_tv = (TextView) login_left.findViewById(R.id.login_tv);
        login_tv.setOnClickListener(this);

        login_iv = (ImageView) login_left.findViewById(R.id.cancel_login);
        login_iv.setOnClickListener(this);
        //左菜单菜单模块
        menu_left = drawer_left.findViewById(R.id.menu_left);
        menu_left_lv = (ListView) menu_left.findViewById(R.id.menu_left_lv);
        menu_left_lv.setDividerHeight(0);
        String[] items = getResources().getStringArray(R.array.menu_letf_lv);
        //创建ArrayAdapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,R.layout.menu_left_lv_item,items);
        //获取ListView对象，通过调用setAdapter方法为ListView设置Adapter设置适配器
        menu_left_lv.setAdapter(adapter);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_content, new HotFragment()).commit();
        menu_left_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_content, new HotFragment()).commit();
                        mToolbar.setTitle("");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mToolbar.setBackground(getDrawable(R.drawable.actionbar_bg_featured));
                            }
                        }
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_content, new OrderFragment()).commit();
                        mToolbar.setTitle(getString(R.string.drawer_item_two));

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mToolbar.setBackground(getDrawable(R.drawable.actionbar_bg));
                            }
                        }
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_content, new SettingFragment()).commit();
                        mToolbar.setTitle(getString(R.string.drawer_item_three));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mToolbar.setBackground(getDrawable(R.drawable.actionbar_bg));
                            }
                        }
                        break;
                }
                mDrawerLayout.closeDrawers();//关闭抽屉
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancel_login:
            case R.id.login_tv:
                startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), Constans.UPDATE);
                //startActivity();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            String nickname = data.getStringExtra("nickname");
            final String figureurl = data.getStringExtra("figureurl");
            Log.d("hc", "nickname " + nickname);
            login_tv.setText(nickname);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Bitmap bitmap = Util.getbitmap(figureurl);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            login_iv.setImageBitmap(bitmap);
                        }
                    });
                }
            }).start();
        }
    }
}
