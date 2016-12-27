package com.zed.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hc on 16-12-27.
 */
public class HotBean {


    /**
     * cover_image_default : http://photos.breadtrip.com/photo_2016_04_07_007a41049d597bb8e5d5b7a8b0a75cd9.jpg?imageView/2/w/960/q/85
     * waypoints : 57
     * wifi_sync : false
     * last_day : 2016-04-04
     * id : 2387287263
     * view_count : 25204
     * privacy : 0
     * day_count : 4
     * index_title :
     * comment_count : 0
     * share_url : trips/2387287263/
     * recommended : false
     * version : 1
     * spot_count : 0
     * mileage : 985.651794614988
     * description :
     * last_modified : 1.46590284118275E9
     * user : {"location_name":"中国_上海","name":"依和","resident_city_id":288678,"mobile":"","gender":2,"avatar_m":"http://photos.breadtrip.com/avatar_0b_7c_fb8c65edfffa2908d52bf996b25918d6bb7b0dfc.jpg-avatar.m","cover":"http://photos.breadtrip.com/user_cover_2016_06_19_2134f24a0093afc0126d34fe4411e858dd0f0d86.jpg-usercover.display","custom_url":"","id":2386701248,"birthday":"","country_num":null,"avatar_s":"http://photos.breadtrip.com/avatar_0b_7c_fb8c65edfffa2908d52bf996b25918d6bb7b0dfc.jpg-avatar.s","country_code":null,"email_verified":false,"is_hunter":false,"cdc2":false,"avatar_l":"http://photos.breadtrip.com/avatar_0b_7c_fb8c65edfffa2908d52bf996b25918d6bb7b0dfc.jpg-avatar.l","email":"","user_desc":"","points":1042}
     * popular_place_str : 韩国,首尔
     * date_complete : 1459765920
     * device : 1
     * date_added : 1459496700
     * cover_image_w640 : http://photos.breadtrip.com/photo_2016_04_07_007a41049d597bb8e5d5b7a8b0a75cd9.jpg?imageView/1/w/640/h/480/q/85
     * name : 「首尔」阴晴有时 牵手即旅行
     * default : false
     * start_point : {"latitude":null,"longitude":null}
     * cover_image_1600 : http://photos.breadtrip.com/photo_2016_04_07_007a41049d597bb8e5d5b7a8b0a75cd9.jpg?imageView/1/w/640/h/480/q/85
     * summary :
     * is_hot_trip : true
     * recommendations : 84
     * cover_image : http://photos.breadtrip.com/photo_2016_04_07_007a41049d597bb8e5d5b7a8b0a75cd9.jpg?imageView/1/w/640/h/480/q/85
     * first_day : 2016-04-01
     * is_featured_trip : false
     */

    private String cover_image_default;
    private int waypoints;
    private boolean wifi_sync;
    private String last_day;
    private long id;
    private int view_count;
    private int privacy;
    private int day_count;
    private String index_title;
    private int comment_count;
    private String share_url;
    private boolean recommended;
    private int version;
    private int spot_count;
    private double mileage;
    private String description;
    private double last_modified;
    private UserBean user;
    private String popular_place_str;
    private int date_complete;
    private int device;
    private int date_added;
    private String cover_image_w640;
    private String name;
    @SerializedName("default")
    private boolean defaultX;
    private StartPointBean start_point;
    private String cover_image_1600;
    private String summary;
    private boolean is_hot_trip;
    private int recommendations;
    private String cover_image;
    private String first_day;
    private boolean is_featured_trip;

    public String getCover_image_default() {
        return cover_image_default;
    }

    public void setCover_image_default(String cover_image_default) {
        this.cover_image_default = cover_image_default;
    }

    public int getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(int waypoints) {
        this.waypoints = waypoints;
    }

    public boolean isWifi_sync() {
        return wifi_sync;
    }

    public void setWifi_sync(boolean wifi_sync) {
        this.wifi_sync = wifi_sync;
    }

    public String getLast_day() {
        return last_day;
    }

    public void setLast_day(String last_day) {
        this.last_day = last_day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(int day_count) {
        this.day_count = day_count;
    }

    public String getIndex_title() {
        return index_title;
    }

    public void setIndex_title(String index_title) {
        this.index_title = index_title;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getSpot_count() {
        return spot_count;
    }

    public void setSpot_count(int spot_count) {
        this.spot_count = spot_count;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLast_modified() {
        return last_modified;
    }

    public void setLast_modified(double last_modified) {
        this.last_modified = last_modified;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getPopular_place_str() {
        return popular_place_str;
    }

    public void setPopular_place_str(String popular_place_str) {
        this.popular_place_str = popular_place_str;
    }

    public int getDate_complete() {
        return date_complete;
    }

    public void setDate_complete(int date_complete) {
        this.date_complete = date_complete;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public int getDate_added() {
        return date_added;
    }

    public void setDate_added(int date_added) {
        this.date_added = date_added;
    }

    public String getCover_image_w640() {
        return cover_image_w640;
    }

    public void setCover_image_w640(String cover_image_w640) {
        this.cover_image_w640 = cover_image_w640;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefaultX() {
        return defaultX;
    }

    public void setDefaultX(boolean defaultX) {
        this.defaultX = defaultX;
    }

    public StartPointBean getStart_point() {
        return start_point;
    }

    public void setStart_point(StartPointBean start_point) {
        this.start_point = start_point;
    }

    public String getCover_image_1600() {
        return cover_image_1600;
    }

    public void setCover_image_1600(String cover_image_1600) {
        this.cover_image_1600 = cover_image_1600;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isIs_hot_trip() {
        return is_hot_trip;
    }

    public void setIs_hot_trip(boolean is_hot_trip) {
        this.is_hot_trip = is_hot_trip;
    }

    public int getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(int recommendations) {
        this.recommendations = recommendations;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getFirst_day() {
        return first_day;
    }

    public void setFirst_day(String first_day) {
        this.first_day = first_day;
    }

    public boolean isIs_featured_trip() {
        return is_featured_trip;
    }

    public void setIs_featured_trip(boolean is_featured_trip) {
        this.is_featured_trip = is_featured_trip;
    }

    public static class UserBean {
        /**
         * location_name : 中国_上海
         * name : 依和
         * resident_city_id : 288678
         * mobile :
         * gender : 2
         * avatar_m : http://photos.breadtrip.com/avatar_0b_7c_fb8c65edfffa2908d52bf996b25918d6bb7b0dfc.jpg-avatar.m
         * cover : http://photos.breadtrip.com/user_cover_2016_06_19_2134f24a0093afc0126d34fe4411e858dd0f0d86.jpg-usercover.display
         * custom_url :
         * id : 2386701248
         * birthday :
         * country_num : null
         * avatar_s : http://photos.breadtrip.com/avatar_0b_7c_fb8c65edfffa2908d52bf996b25918d6bb7b0dfc.jpg-avatar.s
         * country_code : null
         * email_verified : false
         * is_hunter : false
         * cdc2 : false
         * avatar_l : http://photos.breadtrip.com/avatar_0b_7c_fb8c65edfffa2908d52bf996b25918d6bb7b0dfc.jpg-avatar.l
         * email :
         * user_desc :
         * points : 1042
         */

        private String location_name;
        private String name;
        private int resident_city_id;
        private String mobile;
        private int gender;
        private String avatar_m;
        private String cover;
        private String custom_url;
        private long id;
        private String birthday;
        private Object country_num;
        private String avatar_s;
        private Object country_code;
        private boolean email_verified;
        private boolean is_hunter;
        private boolean cdc2;
        private String avatar_l;
        private String email;
        private String user_desc;
        private int points;

        public String getLocation_name() {
            return location_name;
        }

        public void setLocation_name(String location_name) {
            this.location_name = location_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getResident_city_id() {
            return resident_city_id;
        }

        public void setResident_city_id(int resident_city_id) {
            this.resident_city_id = resident_city_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getAvatar_m() {
            return avatar_m;
        }

        public void setAvatar_m(String avatar_m) {
            this.avatar_m = avatar_m;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCustom_url() {
            return custom_url;
        }

        public void setCustom_url(String custom_url) {
            this.custom_url = custom_url;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public Object getCountry_num() {
            return country_num;
        }

        public void setCountry_num(Object country_num) {
            this.country_num = country_num;
        }

        public String getAvatar_s() {
            return avatar_s;
        }

        public void setAvatar_s(String avatar_s) {
            this.avatar_s = avatar_s;
        }

        public Object getCountry_code() {
            return country_code;
        }

        public void setCountry_code(Object country_code) {
            this.country_code = country_code;
        }

        public boolean isEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(boolean email_verified) {
            this.email_verified = email_verified;
        }

        public boolean isIs_hunter() {
            return is_hunter;
        }

        public void setIs_hunter(boolean is_hunter) {
            this.is_hunter = is_hunter;
        }

        public boolean isCdc2() {
            return cdc2;
        }

        public void setCdc2(boolean cdc2) {
            this.cdc2 = cdc2;
        }

        public String getAvatar_l() {
            return avatar_l;
        }

        public void setAvatar_l(String avatar_l) {
            this.avatar_l = avatar_l;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_desc() {
            return user_desc;
        }

        public void setUser_desc(String user_desc) {
            this.user_desc = user_desc;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }

    public static class StartPointBean {
        /**
         * latitude : null
         * longitude : null
         */

        private Object latitude;
        private Object longitude;

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }
    }
}
