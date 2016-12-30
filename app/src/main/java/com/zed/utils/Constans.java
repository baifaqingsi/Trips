package com.zed.utils;

/**
 * Created by hc on 16-12-23.
 */
public class Constans {
    public static final String QQ_APP_ID = "222222";

    public static final int UPDATE = 111;

    //基准url
    public static final String TRIPS_BASE_URL = "http://api.breadtrip.com/";

    //热门游记首页api
    public static final String TRIPS_HOT_URL = TRIPS_BASE_URL + "v2/index/";

    public static final String WEB_VIEW_URL = "web_view_url";


    /**
     * 热门城市首页api
     *
     * @param start   开始位置
     * @param count   请求数量
     * @param country 国家代码
     * @return 热门城市首页api
     */
    public static final String getBreadOrder(int start, int count, String country) {
        return "http://web.breadtrip.com/product/topics/more/?start="
                + start + "&count=" + count + "&country=" + country;
    }
}
