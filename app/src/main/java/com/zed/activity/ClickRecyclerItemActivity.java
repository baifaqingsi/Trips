package com.zed.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zed.adapter.HotAdapter;
import com.zed.adapter.HotItemAdapter;
import com.zed.bean.HotRecylcerItemBean;
import com.zed.recycler.adapter.LRecyclerViewAdapter;
import com.zed.recycler.view.LRecyclerView;
import com.zed.trips.R;
import com.zed.utils.Constans;
import com.zed.utils.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class ClickRecyclerItemActivity extends BaseActivity {

    private LRecyclerView recycler_item_reclcler;
    private String share_url;
    private TextView mid_name;
    private HotItemAdapter hotItemAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    @Override
    protected int getlayoutID() {
        return R.layout.activity_click_recycler_item;
    }

    @Override
    protected void initView() {
        //得到上一个界面穿过来的网址
        share_url = getIntent().getExtras().getString("share_url");
        recycler_item_reclcler = (LRecyclerView) findViewById(R.id.recycler_item_reclcler);

        LinearLayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recycler_item_reclcler.setLayoutManager(recyclerViewLayoutManager);
        //设置adapter
        hotItemAdapter = new HotItemAdapter(this, mHotRecylcerItemBean);

        lRecyclerViewAdapter = new LRecyclerViewAdapter(hotItemAdapter);

        recycler_item_reclcler.setAdapter(lRecyclerViewAdapter);

    }

    @Override
    public void initData() {
        super.initData();
        //  LogUtils.d("share_url "+ share_url);
        OkGo.get(Constans.TRIPS_BASE_URL + share_url).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtils.d("json " + s);
                parseJson(s);
            }
        });
    }

    // 存储数据的集合
    List<HotRecylcerItemBean> mHotRecylcerItemBean = new ArrayList<>();
    List<HotRecylcerItemBean.CoveredCountriesBean> covered_countries1 = new ArrayList<>();
    List<String> city = new ArrayList<>();

    /***
     * 解析json
     *
     * @param json
     */
    private void parseJson(String json) {
        try {
            HotRecylcerItemBean hotRecylcerItemBean = new HotRecylcerItemBean();
            JSONObject jsonObject = new JSONObject(json);
            hotRecylcerItemBean.setName(jsonObject.getString("name"));
            LogUtils.d("name " + jsonObject.getString("name"));
            hotRecylcerItemBean.setDay_count(jsonObject.getInt("day_count"));
            LogUtils.d("day_count " + jsonObject.getInt("day_count"));
            hotRecylcerItemBean.setFirst_day(jsonObject.getString("first_day"));
            LogUtils.d("first_day " + jsonObject.getString("first_day") + "");
            hotRecylcerItemBean.setMileage(((int) jsonObject.getDouble("mileage")));
            LogUtils.d("mileage " + (int) jsonObject.getDouble("mileage") + "km");
            hotRecylcerItemBean.setRecommendations(jsonObject.getInt("recommendations"));
            LogUtils.d("recommendations" + jsonObject.getInt("recommendations") + "");


            HotRecylcerItemBean.CoveredCountriesBean coveredCountriesBean = new HotRecylcerItemBean.CoveredCountriesBean();

            JSONArray covered_countries = jsonObject.getJSONArray("covered_countries");
            for (int i = 0; i < covered_countries.length(); i++) {
                JSONObject jsonObject1 = new JSONObject(covered_countries.getString(i));
                LogUtils.d("icon " + jsonObject1.getString("icon") + " name " + jsonObject1.getString("name"));
                coveredCountriesBean.setIcon(jsonObject1.getString("icon"));

                LogUtils.d(coveredCountriesBean.getIcon());
                coveredCountriesBean.setName(jsonObject1.getString("name"));
                covered_countries1.add(coveredCountriesBean);
            }

            String cities1 = jsonObject.getString("cities");
            city.add(cities1);
           /* String country1 = covered_countries.getString(0);
            JSONObject jsonObject1 = new JSONObject(country1);
            String icon = jsonObject1.getString("icon");
            LogUtils.d("string" + country1);*/
            // user name
            HotRecylcerItemBean.UserBean userBean = new HotRecylcerItemBean.UserBean();
            JSONObject user = jsonObject.getJSONObject("user");
            userBean.setName(user.getString("name"));
            LogUtils.d("user name " + user.getString("name"));
            userBean.setCover(user.getString("cover"));
            LogUtils.d("user cover " + user.getString("cover"));

            hotRecylcerItemBean.setCities(city);
            hotRecylcerItemBean.setCovered_countries(covered_countries1);
            hotRecylcerItemBean.setUser(userBean);
            mHotRecylcerItemBean.add(hotRecylcerItemBean);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 再次更新数据
        hotItemAdapter.setDatas(mHotRecylcerItemBean);
    }




}
