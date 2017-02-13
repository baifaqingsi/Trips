package com.zed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.bumptech.glide.Glide;
import com.zed.activity.ClickRecyclerItemActivity;
import com.zed.bean.HotBean;
import com.zed.bean.HotRecylcerItemBean;
import com.zed.recycler.adapter.BaseViewHolder;
import com.zed.recycler.adapter.SimpleAdapter;
import com.zed.trips.R;
import com.zed.utils.LogUtils;
import com.zed.view.GlideCircleTransform;

import java.lang.reflect.Array;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hc on 17-1-14.
 */
public class HotItemAdapter extends SimpleAdapter<HotRecylcerItemBean> {



    public HotItemAdapter(Context context, List<HotRecylcerItemBean> datas) {
        super(context, R.layout.recycler_item_item, datas);
        LogUtils.d("size " + datas.size());
    }


    @Override
    protected void convert(BaseViewHolder viewHoder, HotRecylcerItemBean item) {


        //  LogUtils.d("item" + item.getDays().get(1).getWaypoints().get(0).getPhoto());

        List<HotRecylcerItemBean.DaysBean> days = item.getDays();

        viewHoder.getTextView(R.id.day).setText(days.get(0).getDay() + "");
        viewHoder.getTextView(R.id.time).setText(days.get(0).getDate());
            Glide.with(viewHoder.itemView.getContext())
                    .load(item.getDays().get(1).getWaypoints().get(0).getPhoto())
                    .asBitmap()
                    .into(viewHoder.getImageView(R.id.photo));


    }
}
