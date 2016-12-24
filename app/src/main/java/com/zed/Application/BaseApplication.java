package com.zed.Application;

import android.app.Application;
import android.content.Context;

/**
 * Created by hc on 16-12-23.
 */
public class BaseApplication extends Application {

    private static Context mContext;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    /**
     * 获取context
     *
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

}
