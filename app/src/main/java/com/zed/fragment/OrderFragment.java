package com.zed.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zed.trips.R;

/**
 * Created by hc on 16-12-16.
 */
public class OrderFragment extends BaseFragment {

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        return view;
    }

    @Override
    protected void initEnevt() {

    }
}
