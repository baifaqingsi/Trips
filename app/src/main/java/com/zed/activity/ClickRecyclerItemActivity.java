package com.zed.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zed.adapter.HotItemAdapter;
import com.zed.bean.HotRecylcerItemBean;
import com.zed.recycler.adapter.LRecyclerViewAdapter;
import com.zed.recycler.view.LRecyclerView;
import com.zed.trips.R;
import com.zed.utils.Constans;
import com.zed.utils.LogUtils;
import com.zed.view.GlideCircleTransform;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Response;

public class ClickRecyclerItemActivity extends BaseActivity {

    private LRecyclerView recycler_item_reclcler;
    private String share_url;
    private TextView mid_name;
    private HotItemAdapter hotItemAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private boolean isRefresh, isAddHeaderView;
    private HotRecylcerItemBean hotRecylcerItemBean;
    private static final String[] strs = new String[]{
            "first", "second", "third", "fourth", "fifth"
    };

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
    List<HotRecylcerItemBean.CoveredCountriesBean> covered_country = new ArrayList<>();
    List<HotRecylcerItemBean.DaysBean> myDayBean = new ArrayList<>();
    List<HotRecylcerItemBean.DaysBean.WaypointsBean> mywaypoints = new ArrayList<>();
    List<String> city = new ArrayList<>();

    /***
     * 解析json
     *
     * @param json
     */
    private void parseJson(String json) {
        try {
            hotRecylcerItemBean = new HotRecylcerItemBean();
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


            JSONArray covered_countries = jsonObject.getJSONArray("covered_countries");
            for (int i = 0; i < covered_countries.length(); i++) {
                HotRecylcerItemBean.CoveredCountriesBean coveredCountriesBean = new HotRecylcerItemBean.CoveredCountriesBean();
                JSONObject jsonObject1 = new JSONObject(covered_countries.getString(i));
                // LogUtils.d("icon " + jsonObject1.getString("icon") + " name " + jsonObject1.getString("name"));
                coveredCountriesBean.setIcon(jsonObject1.getString("icon"));

                //  LogUtils.d(coveredCountriesBean.getIcon());
                coveredCountriesBean.setName(jsonObject1.getString("name"));
                covered_country.add(coveredCountriesBean);
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
            // LogUtils.d("user name " + user.getString("name"));
            userBean.setCover(user.getString("cover"));
            // LogUtils.d("user cover " + user.getString("cover"));


            JSONArray days = jsonObject.getJSONArray("days");
            for (int i = 0; i < days.length(); i++) {
                HotRecylcerItemBean.DaysBean daysBean = new HotRecylcerItemBean.DaysBean();
                HotRecylcerItemBean.DaysBean.WaypointsBean waypointsBean = new HotRecylcerItemBean.DaysBean.WaypointsBean();
                JSONObject jsonObject1 = days.getJSONObject(i);
                String date = jsonObject1.getString("date");
                daysBean.setDate(date);
                int day = jsonObject1.getInt("day");
                daysBean.setDay(day);
                myDayBean.add(daysBean);
                JSONArray waypoints = jsonObject1.getJSONArray("waypoints");
                for (int j = 0; j < waypoints.length(); j++) {
                    String string = waypoints.getString(j);
                    //  LogUtils.d("string " + string);
                    JSONObject jsonObject2 = new JSONObject(string);
                    String photo = jsonObject2.getString("photo");
                    waypointsBean.setPhoto(photo);
                    String text = jsonObject2.getString("text");
                    waypointsBean.setText(text);
                    String local_time = jsonObject2.getString("local_time");
                    waypointsBean.setLocal_time(local_time);
                    //   LogUtils.d("text " + text);

                    //  String poi = jsonObject2.getString("poi");

                    //  JSONObject jsonObject3 = new JSONObject(poi);
                    //  String name = jsonObject3.getString("name");
                    //waypointsBean.set

                    //  LogUtils.d("name22 " + name);
                    mywaypoints.add(waypointsBean);
                    daysBean.setWaypoints(mywaypoints);
                }
                // LogUtils.d("day " + day + "date " + date + "waypoints " + waypoints);

                hotRecylcerItemBean.setDays(myDayBean);
                hotRecylcerItemBean.setCities(city);
                hotRecylcerItemBean.setCovered_countries(covered_country);
                hotRecylcerItemBean.setUser(userBean);

            }


            mHotRecylcerItemBean.add(hotRecylcerItemBean);


            View headView = initHeaderView();

            if (!isAddHeaderView) {
                isAddHeaderView = true;
                lRecyclerViewAdapter.addHeaderView(headView);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // LogUtils.d("huang chao " + mHotRecylcerItemBean.size());
        // 再次更新数据
        hotItemAdapter.setDatas(mHotRecylcerItemBean);
    }

    private View initHeaderView() {
        View headerView = LayoutInflater.from(this)
                .inflate(R.layout.recycler_item_mid_view, (ViewGroup) findViewById(android.R.id.content), false);
        ((TextView) (headerView.findViewById(R.id.mid_name))).setText(hotRecylcerItemBean.getName());
        ((TextView) (headerView.findViewById(R.id.recommendations))).setText("喜欢" + "\n" + hotRecylcerItemBean.getRecommendations());
        ((TextView) (headerView.findViewById(R.id.mileage))).setText("里程" + "\n" + hotRecylcerItemBean.getMileage() + "km");
        ((TextView) (headerView.findViewById(R.id.user_name))).setText("by " + hotRecylcerItemBean.getUser().getName());
        ((TextView) (headerView.findViewById(R.id.mid_time))).setText(hotRecylcerItemBean.getFirst_day() + "\n" + hotRecylcerItemBean.getDay_count() + "天");
        Glide.with(this)
                .load(hotRecylcerItemBean.getUser().getCover())
                .asBitmap().transform(new GlideCircleTransform(this))
                .into((ImageView) headerView.findViewById(R.id.mid_iv));


        List<HotRecylcerItemBean.CoveredCountriesBean> covered_countries = hotRecylcerItemBean.getCovered_countries();
        int size = covered_countries.size();
        //LogUtils.d("size " + size);
        // int[] id = {R.id.city0,R.id.city1,R.id.city2,R.id.city3};
        switch (size) {
            case 1:
                Glide.with(this)
                        .load(covered_countries.get(0).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city0));
                ((ImageView) headerView.findViewById(R.id.city1)).setVisibility(View.GONE);
                ((ImageView) headerView.findViewById(R.id.city2)).setVisibility(View.GONE);
                ((ImageView) headerView.findViewById(R.id.city3)).setVisibility(View.GONE);
                break;
            case 2:
                Glide.with(this)
                        .load(covered_countries.get(0).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city0));
                Glide.with(this)
                        .load(covered_countries.get(1).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city1));
                ((ImageView) headerView.findViewById(R.id.city2)).setVisibility(View.GONE);
                ((ImageView) headerView.findViewById(R.id.city3)).setVisibility(View.GONE);
                break;
            case 3:
                Glide.with(this)
                        .load(covered_countries.get(0).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city0));
                Glide.with(this)
                        .load(covered_countries.get(1).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city1));
                Glide.with(this)
                        .load(covered_countries.get(2).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city2));
                ((ImageView) headerView.findViewById(R.id.city3)).setVisibility(View.GONE);
                break;
            case 4:
                Glide.with(this)
                        .load(covered_countries.get(0).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city0));
                Glide.with(this)
                        .load(covered_countries.get(1).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city1));
                Glide.with(this)
                        .load(covered_countries.get(2).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city2));
                Glide.with(this)
                        .load(covered_countries.get(3).getIcon())
                        .asBitmap().transform(new GlideCircleTransform(this))
                        .into((ImageView) headerView.findViewById(R.id.city3));
                break;
        }


        List<String> cities = hotRecylcerItemBean.getCities();
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : cities) {
            Pattern p = Pattern.compile("\"(.*?)\"");
            Matcher matcher = p.matcher(s);
            while (matcher.find()) {
                //***matcher.group()打印的是这样的字符串
                // "Kolam"
                // LogUtils.d(matcher.group().substring(1,matcher.group().length()-1));  "曼谷" "帕塔亚" "泰国" "Kolam"
                stringBuffer.append(matcher.group().substring(1, matcher.group().length() - 1) + "·");
            }
            //  LogUtils.d(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
            // ["曼谷","帕塔亚","泰国","Kolam"]
        }
        ((TextView) (headerView.findViewById(R.id.address))).setText(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
        return headerView;
    }


}
