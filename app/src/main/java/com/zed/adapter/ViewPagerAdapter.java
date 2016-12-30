package com.zed.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.makeramen.RoundedImageView;
import com.zed.bean.ViewPagerBean;
import com.zed.trips.R;

import java.io.ByteArrayOutputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by hc on 16-12-28.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<ViewPagerBean> mData;
    private Context mContext;
    public RoundedImageView photo;

    public ViewPagerAdapter(List<ViewPagerBean> data, Context context) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.viewpager_layout_item, null);
        photo = (RoundedImageView) inflate.findViewById(R.id.photo);


        Glide.with(mContext).load(mData.get(position).getImage_url()).asBitmap().into(photo);
        container.addView(inflate);
        return inflate;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setDatas(List<ViewPagerBean> datas) {
        //  this.datas.clear();
        this.mData = datas;
        notifyDataSetChanged();
    }
}
