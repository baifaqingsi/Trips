package com.zed.trips;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import fragment.HotFragment;
import fragment.OrderFragment;
import fragment.SettingFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initEnevt();



    }

    private void initEnevt() {
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);

     /*   mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_one:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_content, new HotFragment()).commit();
                        mToolbar.setTitle(getString(R.string.drawer_item_one));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mToolbar.setBackground(getDrawable(R.drawable.actionbar_bg));
                            }
                        }
                        break;
                    case R.id.item_two:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_content, new HotFragment()).commit();
                        mToolbar.setTitle(getString(R.string.drawer_item_two));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mToolbar.setBackground(getDrawable(R.drawable.actionbar_bg));
                            }
                        }
                        break;
                    case R.id.item_three:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_content, new HotFragment()).commit();
                        mToolbar.setTitle(getString(R.string.drawer_item_three));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                mToolbar.setBackground(getDrawable(R.drawable.actionbar_bg));
                            }
                        }
                        break;
                }
                menuItem.setChecked(true);//点击了把它设为选中状态
                mDrawerLayout.closeDrawers();//关闭抽屉
                return true;
            }
        });*/
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        drawer_left = findViewById(R.id.drawer_left);
        //左菜单登录模块
        login_left = drawer_left.findViewById(R.id.login_left);
        login_tv = (TextView) login_left.findViewById(R.id.login_tv);
        login_tv.setOnClickListener(this);
        login_iv = (ImageView) login_left.findViewById(R.id.login_iv);
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
        menu_left_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_content, new HotFragment()).commit();
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
            case R.id.login_iv:
            case R.id.login_tv:
               startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
        }
    }

}
