package com.zed.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zed.activity.LoginActivity;
import com.zed.adapter.HotAdapter;
import com.zed.adapter.ViewPagerAdapter;
import com.zed.bean.HotBean;
import com.zed.bean.ViewPagerBean;
import com.zed.recycler.adapter.LRecyclerViewAdapter;
import com.zed.recycler.view.LRecyclerView;
import com.zed.trips.R;
import com.zed.utils.Constans;
import com.zed.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class HotFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton fab;
    private Button no_net_btn;
    private ImageView no_net_iv;
    private LRecyclerView recy_hot;
    private LinearLayoutManager recyclerViewLayoutManager;
    private ArrayList<String> mData;
    private String string;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private HotAdapter hotAdapter;
    private View view;
    private ViewPager mViewPager;
    private ImageView[] mBottomImages;//底部只是当前页面的小圆点
    //设置当前 第几个图片 被选中
    private int autoCurrIndex = 0;

    private boolean isRefresh, isAddHeaderView;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout llHottestIndicator;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        fab = (ImageButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        no_net_btn = (Button) view.findViewById(R.id.no_net_btn);
        no_net_iv = (ImageView) view.findViewById(R.id.no_net_iv);
        recy_hot = (LRecyclerView) view.findViewById(R.id.recy_hot);


        return view;
    }

    @Override
    protected void initEnevt() {
        Log.d("hc", "NetWorkState " + Util.NetWorkState());
        if (Util.NetWorkState()) {
            //有网络
            initData();
            //线性布局管理器
            recyclerViewLayoutManager = new LinearLayoutManager(getActivity());
            //设置布局管理器
            recy_hot.setLayoutManager(recyclerViewLayoutManager);
            //设置adapter
            // recyclerAdapter = new RecyclerAdapter(mHotBean, getActivity());
            hotAdapter = new HotAdapter(getActivity(), mHotBean);

            lRecyclerViewAdapter = new LRecyclerViewAdapter(hotAdapter);

            recy_hot.setAdapter(lRecyclerViewAdapter);


        } else {
            //无网络
            no_net_btn.setVisibility(View.VISIBLE);
            no_net_iv.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        OkGo.get(Constans.TRIPS_HOT_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                //     Log.e("c","json "+ s);
                parseJson(s);
            }
        });
    }

    //列表数据
    List<HotBean> mHotBean = new ArrayList<>();

    //轮播数据
    List<ViewPagerBean> mViewPagerBean = new ArrayList<>();

    /***
     * 解析主界面的item数据 就是recycle的item数据
     *
     * @param s
     */
    private void parseJson(String s) {


        try {
            JSONObject jsonObject = new JSONObject(s);

            if (jsonObject.getString("status").equals("0")) {
                JSONObject data = jsonObject.getJSONObject("data");

                parseDetailData(data);

                JSONArray elements = data.getJSONArray("elements");
                for (int i = 0; i < elements.length(); i++) {
                    JSONObject json = (JSONObject) elements.get(i);
                    if (json.getString("type").equals("4")) {
                        HotBean hotBean = new HotBean();
                        JSONArray hot_data = json.getJSONArray("data");
                        JSONObject detailObject = (JSONObject) hot_data.get(0);
                        Log.d("hc", "detailObject " + detailObject.toString());
                        String cover_image_default = detailObject.getString("cover_image_default");
                        Log.d("hc", "cover_image_default " + cover_image_default);
                        hotBean.setCover_image_default(cover_image_default);
                        String cover_image = detailObject.getString("cover_image");
                        hotBean.setCover_image(cover_image);

                        mHotBean.add(hotBean);
                    }
                }
                //添加头部viewpager
                View headView = initHeaderView(mViewPagerBean);

                if (!isAddHeaderView) {
                    isAddHeaderView = true;
                    lRecyclerViewAdapter.addHeaderView(headView);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 再次更新数据
        hotAdapter.setDatas(mHotBean);
        viewPagerAdapter.setDatas(mViewPagerBean);
    }


    /**
     * 解析详情数据 也就是轮播图的数据
     *
     * @param dataResults dataResults
     * @throws JSONException
     */
    private void parseDetailData(JSONObject dataResults) throws JSONException {
        JSONArray mElementsArray = dataResults.getJSONArray("elements");
        int length = mElementsArray.length();
        mViewPagerBean.clear();
        for (int i = 0; i < length; i++) {
            JSONObject mElementsObject = (JSONObject) mElementsArray.get(i);
            if (mElementsObject.getString("type").equals("1")) {  //轮播图
                JSONArray jsonArray = mElementsObject.getJSONArray("data");
                JSONArray bannerArrays = (JSONArray) jsonArray.get(0);
                for (int i1 = 0; i1 < bannerArrays.length(); i1++) {
                    ViewPagerBean viewPagerBean = new ViewPagerBean();
                    JSONObject bannerObject = (JSONObject) bannerArrays.get(i1);
                    viewPagerBean.html_url = bannerObject.getString("html_url");
                    viewPagerBean.image_url = bannerObject.getString("image_url");
                    viewPagerBean.platform = bannerObject.getString("platform");
                    mViewPagerBean.add(viewPagerBean);
                }

            }

        }

    }

    //给recycleview 添加头部 此处是viewpager
    private View initHeaderView(List<ViewPagerBean> mViewPagerBean) {
        View headerView = LayoutInflater.from(getActivity())
                .inflate(R.layout.viewpager_layout, (ViewGroup) view.findViewById(android.R.id.content), false);
        mViewPager = (ViewPager) headerView.findViewById(R.id.view_pager);
        llHottestIndicator = (LinearLayout) headerView.findViewById(R.id.ll_hottest_indicator);

        viewPagerAdapter = new ViewPagerAdapter(getActivity(), mViewPagerBean);

        mViewPager.setAdapter(viewPagerAdapter);
        // 增加当手动滑动时 与自动轮播产生的冲突逻辑解决
        mViewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        runTask.stop();
                        break;
                    case MotionEvent.ACTION_CANCEL:  // 事件的取消
                    case MotionEvent.ACTION_UP:
                        runTask.start();
                        break;
                }
                return false; // viewPager 触摸事件 返回值要是false
            }
        });
        // 增加底部指示器
        initdots();
        // 自动轮播
        runTask = new AuToRunTask();
        runTask.start();
        // autoScorll();
        return headerView;
    }


    //创建底部指示位置的导航栏
    private void initdots() {

        mBottomImages = new ImageView[mViewPagerBean.size()];

        for (int i = 0; i < mBottomImages.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            params.setMargins(5, 0, 5, 0);
            imageView.setLayoutParams(params);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.indicator_select);
            } else {
                imageView.setBackgroundResource(R.drawable.indicator_not_select);
            }

            mBottomImages[i] = imageView;
            //把指示作用的原点图片加入底部的视图中
            llHottestIndicator.addView(mBottomImages[i % mViewPagerBean.size()]);

        }
        // 点与页面相匹配
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                // 一定几个图片，几个圆点，但注意是从0开始的
                int total = mBottomImages.length;
                for (int j = 0; j < total; j++) {
                    if (j == position % mViewPagerBean.size()) {
                        mBottomImages[j % mViewPagerBean.size()].setBackgroundResource(R.drawable.indicator_select);
                    } else {
                        mBottomImages[j % mViewPagerBean.size()].setBackgroundResource(R.drawable.indicator_not_select);
                    }
                }
                //设置全局变量，currentIndex为选中图标的 index
                autoCurrIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


   /* private Handler mHandler = new Handler();

    *//**
     * 自动滚动
     *//*
    private void autoScorll() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                int currentItem = mViewPager.getCurrentItem();
                mViewPager.setCurrentItem(currentItem + 1);
                mHandler.postDelayed(this, 3000);
            }
        }, 3000);
    }
*/
    // 右下角的+号的点击事件响应
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), Constans.UPDATE);

        }
    }

    // 根据网络显示不同界面 未实现
    public BroadcastReceiver NetReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                if (Util.NetWorkState()) {

                } else {

                }

                Log.d("hc", "NetReceiver广播");
            }
        }
    };
    // 自动轮询的实现
    boolean flag;
    private AuToRunTask runTask;

    public class AuToRunTask implements Runnable {

        @Override
        public void run() {
            if (flag) {
                Util.cancel(this);  // 取消之前
                int currentItem = mViewPager.getCurrentItem();
                currentItem++;
                mViewPager.setCurrentItem(currentItem);
                //  延迟执行当前的任务
                Util.postDelayed(this, 5000);// 递归调用
            }
        }

        public void start() {
            if (!flag) {
                Util.cancel(this);  // 取消之前
                flag = true;
                Util.postDelayed(this, 5000);// 递归调用
            }
        }

        public void stop() {
            if (flag) {
                flag = false;
                Util.cancel(this);
            }
        }

    }


}
