package com.zed.utils;

import android.util.Log;

import com.zed.application.BaseApplication;

/**
 * Created by hc on 17-1-5.
 */
public class LogUtils {

    /**
     * 日志输出时的TAG
     */

    private static String mTag = "Trips";

    /**
     * 用于记时的变量
     */

    private static long mTimestamp = 0;

    /**
     * 以级别为 v 的形式输出LOG
     */

    public static void v(String msg) {
        if (BaseApplication.getLogToggle()) {
            Log.v(mTag, msg);
        }
    }


    /**
     * 以级别为 d 的形式输出LOG
     */

    public static void d(String msg) {

        if (BaseApplication.getLogToggle()) {


            Log.d(mTag, msg);

        }

    }


    /**
     * 以级别为 i 的形式输出LOG
     */

    public static void i(String msg) {

        if (BaseApplication.getLogToggle()) {
            Log.i(mTag, msg);

        }

    }


    /**
     * 以级别为 w 的形式输出LOG
     */

    public static void w(String msg) {

        if (BaseApplication.getLogToggle()) {


            Log.w(mTag, msg);

        }

    }


    /**
     * 以级别为 w 的形式输出Throwable
     */

    public static void w(Throwable tr) {

        if (BaseApplication.getLogToggle()) {


            Log.w(mTag, "", tr);

        }

    }


    /**
     * 以级别为 w 的形式输出LOG信息和Throwable
     */

    public static void w(String msg, Throwable tr) {


        if (BaseApplication.getLogToggle() && null != msg) {

            Log.w(mTag, msg, tr);

        }

    }


    /**
     * 以级别为 e 的形式输出LOG
     */

    public static void e(String msg) {

        if (BaseApplication.getLogToggle()) {
            Log.e(mTag, msg);
        }

    }


    /**
     * 以级别为 e 的形式输出Throwable
     */

    public static void e(Throwable tr) {

        if (BaseApplication.getLogToggle()) {
            Log.e(mTag, "", tr);
        }

    }

}
