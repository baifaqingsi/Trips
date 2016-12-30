package com.zed.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.zed.bean.HotBean;
import com.zed.recycler.adapter.BaseViewHolder;
import com.zed.recycler.adapter.SimpleAdapter;
import com.zed.trips.R;

import java.util.List;

/**
 * Created by hc on 16-12-28.
 */
public class HotAdapter extends SimpleAdapter<HotBean> {

    public HotAdapter(Context context, List<HotBean> datas) {
        super(context, R.layout.hot_recycler_item, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, HotBean item) {
        Glide.with(viewHoder.itemView.getContext())
                .load(item.getCover_image())
                .asBitmap()
                .into(viewHoder.getImageView(R.id.photo));

    }
}
