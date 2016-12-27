package com.zed.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zed.activity.LoginActivity;
import com.zed.adapter.RecyclerAdapter;
import com.zed.bean.HotBean;
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
    private RecyclerView recy_hot;
    private RecyclerAdapter recyclerAdapter;
    private LinearLayoutManager recyclerViewLayoutManager;
    private ArrayList<String> mData;
    private String string;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        fab = (ImageButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        no_net_btn = (Button) view.findViewById(R.id.no_net_btn);
        no_net_iv = (ImageView) view.findViewById(R.id.no_net_iv);
        recy_hot = (RecyclerView) view.findViewById(R.id.recy_hot);


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
            recyclerAdapter = new RecyclerAdapter(mHotBean, getActivity());

            recy_hot.setAdapter(recyclerAdapter);


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

    private void parseJson(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.getString("status").equals("0")) {
                JSONObject data = jsonObject.getJSONObject("data");
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

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), Constans.UPDATE);

        }
    }


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
}
