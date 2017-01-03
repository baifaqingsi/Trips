package com.zed.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.makeramen.RoundedImageView;
import com.zed.bean.ViewPagerBean;
import com.zed.trips.R;
import com.zed.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hc on 16-12-28.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<ViewPagerBean> mData;
    private Activity context;
    private List<RoundedImageView> imageViews = new ArrayList<RoundedImageView>();
    private View view;

    public ViewPagerAdapter(Activity context, List<ViewPagerBean> datas) {
        this.context = context;
        if (mData == null || mData.size() == 0) {
            this.mData = new ArrayList<>();
        } else {
            this.mData = datas;
        }
        view = LayoutInflater.from(context).inflate(R.layout.viewpager_layout_item, null);

        for (int i = 0; i < datas.size(); i++) {
            //RoundedImageView image = (RoundedImageView) view.findViewById(R.id.photo);
            RoundedImageView image = new RoundedImageView(context);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            image.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            // Uri uri = Uri.parse("http://photos.breadtrip.com/covers_2016_02_18_46d0c9d680e28825b76f280f777bf080.png?imageView2/2/w/750/format/jpg/interlace/1/");
            Log.d("hc", "getImage_url " + datas.get(i).getImage_url());
            Glide.with(context).load(datas.get(i).getImage_url()).asBitmap().into(image);
            //  image.setImageURI(uri);
            imageViews.add(image);
        }
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position % mData.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Util.removeParent(imageViews.get(position % mData.size()));

        container.addView(imageViews.get(position % mData.size()));
        return imageViews.get(position % mData.size());
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (View) object;
    }

    public void setDatas(List<ViewPagerBean> datas) {
        //  this.datas.clear();
        this.mData = datas;
        notifyDataSetChanged();
    }
}
