package com.zed.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.zed.bean.HotBean;
import com.zed.recycler.adapter.BaseViewHolder;
import com.zed.recycler.adapter.SimpleAdapter;
import com.zed.trips.R;
import com.zed.view.GlideCircleTransform;

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
        viewHoder.getTextView(R.id.tv_name).setText(item.getName());
        viewHoder.getTextView(R.id.tv_time).setText(item.getFirst_day());
        viewHoder.getTextView(R.id.tv_count_day).setText(item.getDay_count() + "");
        viewHoder.getTextView(R.id.tv_count_view).setText(item.getView_count() + "");
        viewHoder.getTextView(R.id.tv_address).setText(item.getPopular_place_str());
        viewHoder.getTextView(R.id.tv_user_name).setText(item.getUser().getName());
        Glide.with(viewHoder.itemView.getContext())
                .load(item.getUser().getCover())
                .asBitmap().transform(new GlideCircleTransform(viewHoder.itemView.getContext()))
                .into(viewHoder.getImageView(R.id.img_avator));

    }
}
