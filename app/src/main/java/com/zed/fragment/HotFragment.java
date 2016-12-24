package com.zed.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.zed.trips.LoginActivity;
import com.zed.trips.R;

import com.zed.utils.Constans;
import com.zed.utils.Util;

public class HotFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton fab;
    private Button no_net_btn;
    private ImageView no_net_iv;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        fab = (ImageButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        no_net_btn = (Button) view.findViewById(R.id.no_net_btn);
        no_net_iv = (ImageView) view.findViewById(R.id.no_net_iv);


        return view;
    }

    @Override
    protected void initEnevt() {
        Log.d("hc", "NetWorkState " + Util.NetWorkState());
        if (Util.NetWorkState()) {
            //有网络
            no_net_btn.setVisibility(View.GONE);
            no_net_iv.setVisibility(View.GONE);
        } else {
            //无网络

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivityForResult(new Intent(getActivity(), LoginActivity.class), Constans.UPDATE);

        }
    }
}
