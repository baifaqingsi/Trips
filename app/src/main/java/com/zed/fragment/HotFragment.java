package com.zed.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
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
import com.zed.activity.ClickRecyclerItemActivity;
import com.zed.activity.ClickViewPagerItemActivity;
import com.zed.activity.LoginActivity;
import com.zed.adapter.HotAdapter;
import com.zed.adapter.ViewPagerAdapter;
import com.zed.bean.HotBean;
import com.zed.bean.ViewPagerBean;
import com.zed.recycler.adapter.LRecyclerViewAdapter;
import com.zed.recycler.interfaces.OnItemClickListener;
import com.zed.recycler.interfaces.OnLoadMoreListener;
import com.zed.recycler.interfaces.OnRefreshListener;
import com.zed.recycler.utils.RecyclerViewStateUtils;
import com.zed.recycler.view.LRecyclerView;
import com.zed.recycler.view.LoadingFooter;
import com.zed.trips.R;
import com.zed.utils.Constans;
import com.zed.utils.LogUtils;
import com.zed.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class HotFragment extends BaseFragment implements View.OnClickListener, OnRefreshListener, OnLoadMoreListener {

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
    private String next_start;

    // 自动轮询的实现
    boolean flag;
    private AuToRunTask runTask = new AuToRunTask();
    ;
    private SharedPreferences trips;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hot, container, false);
        fab = (ImageButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        no_net_btn = (Button) view.findViewById(R.id.no_net_btn);
        no_net_iv = (ImageView) view.findViewById(R.id.no_net_iv);
        recy_hot = (LRecyclerView) view.findViewById(R.id.recy_hot);
        trips = getActivity().getSharedPreferences("Trips", Context.MODE_PRIVATE);

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

            recy_hot.setOnRefreshListener(this);

            recy_hot.setOnLoadMoreListener(this);

            //recy_hot.setRefreshing(true);
            lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getActivity(), ClickRecyclerItemActivity.class);
                    Bundle bundle = new Bundle();
                    //  LogUtils.d("share_url " + mHotBean.get(position).getShare_url());
                    bundle.putString("share_url", mHotBean.get(position).getShare_url());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


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


                //添加头部viewpager

                View headView = initHeaderView(mViewPagerBean);
                if (!isAddHeaderView) {
                    isAddHeaderView = true;
                    lRecyclerViewAdapter.addHeaderView(headView);
                }
                viewPagerAdapter.setDatas(mViewPagerBean);
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
                next_start = data.getString("next_start");
                Log.d("hc", "next_start " + next_start);
                parseViewPagerData(data);

                JSONArray elements = data.getJSONArray("elements");
                for (int i = 0; i < elements.length(); i++) {
                    JSONObject json = (JSONObject) elements.get(i);
                    if (json.getString("type").equals("4")) {
                        HotBean hotBean = new HotBean();
                        JSONArray hot_data = json.getJSONArray("data");
                        JSONObject detailObject = (JSONObject) hot_data.get(0);
                        // Log.d("hc", "detailObject " + detailObject.toString());
                        String cover_image_default = detailObject.getString("cover_image_default");
                        //  Log.d("hc", "cover_image_default " + cover_image_default);
                        hotBean.setCover_image_default(cover_image_default);
                        String cover_image = detailObject.getString("cover_image");
                        hotBean.setCover_image(cover_image);
                        String first_day = detailObject.getString("first_day");
                        //    Log.d("hc","first_day " + first_day);
                        hotBean.setFirst_day(first_day);
                        int day_count = detailObject.getInt("day_count");
                        //  Log.d("hc","day_count " + day_count);
                        hotBean.setDay_count(day_count);
                        int view_count = detailObject.getInt("view_count");
                        //  Log.d("hc","view_count " + view_count);
                        hotBean.setView_count(view_count);
                        String name = detailObject.getString("name");
                        //  Log.d("hc","name " + name);
                        hotBean.setName(name);
                        String popular_place_str = detailObject.getString("popular_place_str");
                        hotBean.setPopular_place_str(popular_place_str);

                        String share_url = detailObject.getString("share_url");
                        hotBean.setShare_url(share_url);
                        // Log.d("hc","popular_place_st " + popular_place_str);
                        // detail
                        JSONObject user = detailObject.getJSONObject("user");
                        HotBean.UserBean userBean = new HotBean.UserBean();
                        userBean.setName(user.getString("name"));
                        //  Log.d("hc","user name " + user.getString("name"));
                        userBean.setCover(user.getString("cover"));
                        //  Log.d("hc","user cover " + user.getString("cover") );
                        hotBean.setUser(userBean);
                        mHotBean.add(hotBean);
                    }
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 再次更新数据
        hotAdapter.setDatas(mHotBean);

    }


    /**
     * 解析详情数据 也就是轮播图的数据
     *
     * @param dataResults dataResults
     * @throws JSONException
     */
    private void parseViewPagerData(JSONObject dataResults) throws JSONException {
        JSONArray mElementsArray = dataResults.getJSONArray("elements");
        int length = mElementsArray.length();
        mViewPagerBean.clear();
        for (int i = 0; i < length; i++) {
            JSONObject mElementsObject = (JSONObject) mElementsArray.get(i);
            if (mElementsObject.getString("type").equals("1")) {  //轮播图
                JSONArray jsonArray = mElementsObject.getJSONArray("data");
                JSONArray bannerArrays = (JSONArray) jsonArray.get(0);
                for (int j = 0; j < bannerArrays.length(); j++) {
                    ViewPagerBean viewPagerBean = new ViewPagerBean();
                    JSONObject bannerObject = (JSONObject) bannerArrays.get(j);
                    viewPagerBean.html_url = bannerObject.getString("html_url");
                    viewPagerBean.image_url = bannerObject.getString("image_url");
                    viewPagerBean.platform = bannerObject.getString("platform");
                    mViewPagerBean.add(viewPagerBean);
                }

            }
        }

    }

    //给recycleview 添加头部 此处是viewpager
    private View initHeaderView(final List<ViewPagerBean> mViewPagerBean) {
        View headerView = LayoutInflater.from(getActivity())
                .inflate(R.layout.viewpager_layout, (ViewGroup) view.findViewById(android.R.id.content), false);
        mViewPager = (ViewPager) headerView.findViewById(R.id.view_pager);


        llHottestIndicator = (LinearLayout) headerView.findViewById(R.id.ll_hottest_indicator);

        viewPagerAdapter = new ViewPagerAdapter(getActivity(), mViewPagerBean) {
            @Override
            protected void goActivity(int position) {
                Intent intent = new Intent(getActivity(), ClickViewPagerItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("html_url", mViewPagerBean.get(position).getHtml_url());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        };

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
        runTask.start();
        // autoScorll();
        return headerView;
    }


    //创建底部指示位置的导航栏
    private void initdots() {
        // Log.d("hc", "dots");
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
                // Log.d("hc","onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                //  Log.d("hc","position " + position);
                //  Log.d("hc","position % mViewPagerBean.size() " + position % mViewPagerBean.size());
                // 一定几个图片，几个圆点，但注意是从0开始的
                int total = mBottomImages.length;
                for (int j = 0; j < total; j++) {
                    if (j == (position % mViewPagerBean.size())) {
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
                // Log.d("hc","onPageScrollStateChanged");
            }
        });

    }


   /* private Handler mHandler = new Handler();

    */

    /**
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

                // Log.d("hc", "NetReceiver广播");
            }
        }
    };


    @Override
    public void onRefresh() {
        RecyclerViewStateUtils.setFooterViewState(recy_hot, LoadingFooter.State.Normal);
        mHotBean.clear();
        hotAdapter.notifyDataSetChanged();
        isRefresh = true;
        runTask.stop();
        mViewPagerBean.clear();
        viewPagerAdapter.notifyDataSetChanged();
        requestData();
    }

    private void requestData() {
        OkGo.get(Constans.TRIPS_HOT_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // parseJson(s);
                        //DataStore.getInstance().saveHotListData(s);
                        //   Log.d("hc","isRefresh " + isRefresh);
                        parseJson(s);
                        isRefresh = false;
                        recy_hot.refreshComplete();
                        //initdots();

                        RecyclerViewStateUtils.setFooterViewState(recy_hot,
                                LoadingFooter.State.Normal);
                        hotAdapter.notifyDataSetChanged();
                        //   Log.d("hc","mViewPagerBean " + mViewPagerBean.size());

                        viewPagerAdapter.setDatas(mViewPagerBean);
                        runTask.start();
                        //viewPagerAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onLoadMore() {
        LoadingFooter.State state = RecyclerViewStateUtils
                .getFooterViewState(recy_hot);
        if (state == LoadingFooter.State.Loading) {
            return;
        }

        RecyclerViewStateUtils
                .setFooterViewState(getActivity(), recy_hot, 10, LoadingFooter.State.Loading, null);

        if (!TextUtils.isEmpty(next_start)) {
            OkGo.get(Constans.TRIPS_HOT_URL + "?next_start=" + next_start)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            parseDataJson(s);
                        }
                    });
        }

    }

    private void parseDataJson(String s) {
        List<HotBean> mDetailBeanDatasMore = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getString("status").equals("0")) {

                JSONObject dataResults = jsonObject.getJSONObject("data");  //data
                next_start = dataResults.getString("next_start");
                JSONArray eleArrays = dataResults.getJSONArray("elements");

                int length = eleArrays.length();
                for (int i = 0; i < length; i++) {
                    JSONObject js = eleArrays.getJSONObject(i);
                    JSONArray ja = js.getJSONArray("data");
                    JSONObject detailObject = (JSONObject) ja.get(0);

                    //   JSONArray jsonArray = mElementsObject.getJSONArray("data");
                    // JSONObject detailObject = (JSONObject) jsonArray.get(0);
                    HotBean bean = new HotBean();
                    // Type type = new TypeToken<DetailBean>(){}.getType();
                    //  bean = new Gson().fromJson(detailObject.toString(),type);
                    bean.setCover_image(detailObject.getString("cover_image_default"));
                    bean.setName(detailObject.getString("name"));
                    bean.setFirst_day(detailObject.getString("first_day"));
                    bean.setDay_count(detailObject.getInt("day_count"));
                    bean.setView_count(detailObject.getInt("view_count"));
                    bean.setPopular_place_str(detailObject.getString("popular_place_str"));
                    JSONObject userObject = detailObject.getJSONObject("user");
                    HotBean.UserBean entity = new HotBean.UserBean();
                    entity.setName(userObject.getString("name"));
                    entity.setAvatar_m(userObject.getString("avatar_m"));
                    entity.setAvatar_l(userObject.getString("avatar_l"));
                    entity.setAvatar_s(userObject.getString("avatar_s"));
                    entity.setId(userObject.getInt("id"));
                    entity.setUser_desc(userObject.getString("user_desc"));
                    entity.setPoints(userObject.getInt("points"));
                    bean.setUser(entity);
                    bean.setShare_url(detailObject.getString("share_url"));
                    bean.setId(detailObject.getInt("id"));
                    mDetailBeanDatasMore.add(bean);
                    // Log.d("HotTripFragment", "result:" + result);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mHotBean.addAll(mDetailBeanDatasMore);
        hotAdapter.notifyDataSetChanged();
        recy_hot.refreshComplete();
        RecyclerViewStateUtils
                .setFooterViewState(recy_hot, LoadingFooter.State.Normal);
    }


    public class AuToRunTask implements Runnable {
        @Override
        public void run() {
            if (flag) {
                Util.cancel(this);  // 取消之前
                int currentItem = mViewPager.getCurrentItem();
                currentItem++;
                //Log.d("hc", "currentItem " + currentItem);
                mViewPager.setCurrentItem(currentItem);
                //  延迟执行当前的任务
                Util.postDelayed(this, 5000);// 递归调用
                //  Log.d("hc", "over currentItem");
            }
        }

        public void start() {
            //  Log.d("hc", "start");
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
