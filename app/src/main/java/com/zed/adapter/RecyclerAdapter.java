package com.zed.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.makeramen.RoundedImageView;
import com.zed.bean.HotBean;
import com.zed.trips.R;

import java.util.List;

/**
 * Created by hc on 16-12-27.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderHelper> {

    private List<HotBean> mData;
    private Context mContext;

    public RecyclerAdapter(List<HotBean> data, Context context) {
        mData = data;
        mContext = context;
    }

    @Override
    public ViewHolderHelper onCreateViewHolder(ViewGroup parent, int viewType) {
        //onCreateViewHolder方法就是将布局文件转化为View并传递给RecyclerView封装好的ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_recycler_item, parent, false);
        return new ViewHolderHelper(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderHelper holder, int position) {
        Glide.with(mContext)
                .load(mData.get(position).getCover_image())
                .asBitmap()
                .into(holder.mRoundedImageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ViewHolderHelper extends RecyclerView.ViewHolder {

        RoundedImageView mRoundedImageView;

        public ViewHolderHelper(View view) {
            super(view);
            mRoundedImageView = (RoundedImageView) view.findViewById(R.id.photo);
        }
    }

}