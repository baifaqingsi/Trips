package com.zed.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.VideoView;

import java.net.URL;

/**
 * Created by hc on 16-12-13.
 */
public class CustomVedioView extends VideoView {
    public CustomVedioView(Context context) {
        super(context);
    }

    public CustomVedioView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVedioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    /***
     * 设置播放路径
     * @param uri
     */
    public void playVideo(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("Uri can not be null");
        }
        /**设置播放路径**/
        setVideoURI(uri);
        /**开始播放**/
        start();
    }
}
