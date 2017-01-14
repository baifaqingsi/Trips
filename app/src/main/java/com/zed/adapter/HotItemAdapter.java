package com.zed.adapter;

import android.content.Context;
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
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, HotRecylcerItemBean item) {
        viewHoder.getTextView(R.id.mid_name).setText(item.getName());
        viewHoder.getTextView(R.id.mileage).setText("里程" + "\n" + item.getMileage() + "km");
        viewHoder.getTextView(R.id.recommendations).setText("喜欢" + "\n" + item.getRecommendations());
        viewHoder.getTextView(R.id.user_name).setText("by " + item.getUser().getName());
        viewHoder.getTextView(R.id.mid_time).setText(item.getFirst_day() + "\n" + item.getDay_count());
        Glide.with(viewHoder.itemView.getContext())
                .load(item.getUser().getCover())
                .asBitmap().transform(new GlideCircleTransform(viewHoder.itemView.getContext()))
                .into(viewHoder.getImageView(R.id.mid_iv));

        List<HotRecylcerItemBean.CoveredCountriesBean> covered_countries = item.getCovered_countries();
        for (int i = 0; i < covered_countries.size(); i++) {
            LogUtils.d(covered_countries.get(i).getIcon());
        }
        if (covered_countries.size() > 1) {
            Glide.with(viewHoder.itemView.getContext())
                    .load(covered_countries.get(0).getIcon())
                    .asBitmap().transform(new GlideCircleTransform(viewHoder.itemView.getContext()))
                    .into(viewHoder.getImageView(R.id.city));
            Glide.with(viewHoder.itemView.getContext())
                    .load(covered_countries.get(1).getIcon())
                    .asBitmap().transform(new GlideCircleTransform(viewHoder.itemView.getContext()))
                    .into(viewHoder.getImageView(R.id.city2));

        } else {
            Glide.with(viewHoder.itemView.getContext())
                    .load(covered_countries.get(0).getIcon())
                    .asBitmap().transform(new GlideCircleTransform(viewHoder.itemView.getContext()))
                    .into(viewHoder.getImageView(R.id.city));
            viewHoder.getImageView(R.id.city2).setVisibility(View.GONE);
        }
        List<String> cities = item.getCities();
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : cities) {
            Pattern p = Pattern.compile("\"(.*?)\"");
            Matcher matcher = p.matcher(s);
            while (matcher.find()) {
                /***matcher.group()打印的是这样的字符串
                 * "Kolam"
                 */
                // LogUtils.d(matcher.group().substring(1,matcher.group().length()-1));  "曼谷" "帕塔亚" "泰国" "Kolam"
                stringBuffer.append(matcher.group().substring(1, matcher.group().length() - 1) + "·");
            }
            LogUtils.d(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
            // ["曼谷","帕塔亚","泰国","Kolam"]
        }
        viewHoder.getTextView(R.id.address).setText(stringBuffer.toString().substring(0, stringBuffer.length() - 1));
    }
}
