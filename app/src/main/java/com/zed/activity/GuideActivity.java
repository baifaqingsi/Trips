package com.zed.activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zed.fragment.HotFragment;
import com.zed.trips.R;
import com.zed.view.CustomVedioView;

public class GuideActivity extends BaseActivity {

    private CustomVedioView guideCvv;
    private Button guidebtn;
    private ImageView guideiv;
    private SharedPreferences trips;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           switch (msg.what){
               case 1:
                   if (isFirstGuide()){
                   initEnevt();
               } else {
                   startActivity(new Intent(GuideActivity.this,MainActivity.class));
                       Log.d("hc","else");
                       finish();
               }
           }
        }
    };


    @Override
    protected int getlayoutID() {
        return R.layout.activity_guide;
    }

    private void initEnevt() {
        guideiv.setVisibility(View.GONE);
        guideCvv.setVisibility(View.VISIBLE);
        guidebtn.setVisibility(View.VISIBLE);
        guideCvv.playVideo(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.guide_video));
        guideCvv.start();
        //循环播放
        guideCvv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                startActivity();
            }
        });
        guidebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guideCvv != null) {
                    guideCvv.stopPlayback();
                }
                startActivity();
            }
        });

        /////////动态注册广播

        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        HotFragment hotFragment = new HotFragment();
        BroadcastReceiver netReceiver = hotFragment.NetReceiver;
        registerReceiver(netReceiver, mFilter);
    }

    @Override
    protected void initView() {

        guideCvv = (CustomVedioView) findViewById(R.id.guide_cvv);
        guidebtn = (Button) findViewById(R.id.guide_btn);
        guideiv = (ImageView) findViewById(R.id.guide_iv);
        trips = getSharedPreferences("Trips", MODE_PRIVATE);
        mHandler.postDelayed(mRunnable, 1000); // 在Handler中执行子线程并延迟500 ms。
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(1);
        }
    };

    /***
     * 是否第一次进入
     * @return
     */
    private boolean isFirstGuide(){
        return trips.getBoolean("isFirstGuide", true);
    }


    /***
     * 进入主界面
     */
    private void startActivity(){
        startActivity(new Intent(GuideActivity.this,MainActivity.class));
        trips.edit().putBoolean("isFirstGuide",false).commit();
        finish();
    }
}
