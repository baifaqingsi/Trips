package com.zed.bean;

import com.google.gson.Gson;

/**
 * Created by hc on 16-12-28.
 */
public class ViewPagerBean {
    /**
     * platform : android
     * image_url : http://photos.breadtrip.com/covers_2016_11_09_4568427174a6f7050a435ed007aec4e2.jpg?imageView/2/w/960/
     * html_url : http://web.breadtrip.com/mobile/product_topic/2387718844/
     */

    public String platform;
    public String image_url;
    public String html_url;

    public static ViewPagerBean objectFromData(String str) {

        return new Gson().fromJson(str, ViewPagerBean.class);
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

}
