package com.zed.bean;

import java.util.List;

/**
 * Created by hc on 17-1-7.
 */
public class HotRecylcerItemBean {


    private int waypoints;
    private boolean wifi_sync;
    private String last_day;
    private long id;
    private String city;
    private int privacy;
    private int day_count;
    private int comment_count;
    private String first_timezone;
    private int shared;
    private String province;
    private double mileage;
    private String description;
    private double last_modified;
    private UserBean user;
    private double date_complete;
    private int device;
    private double date_added;
    private String name;
    private StartPointBean start_point;
    private String country;
    private int recommendations;
    private String cover_image;
    private String first_day;
    private List<CoveredCountriesBean> covered_countries;
    private List<String> city_slug_urls;
    private List<String> cities;
    private List<DaysBean> days;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getFirst_timezone() {
        return first_timezone;
    }

    public void setFirst_timezone(String first_timezone) {
        this.first_timezone = first_timezone;
    }

    public int getShared() {
        return shared;
    }

    public void setShared(int shared) {
        this.shared = shared;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public double getDate_complete() {
        return date_complete;
    }

    public void setDate_complete(double date_complete) {
        this.date_complete = date_complete;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public double getDate_added() {
        return date_added;
    }

    public void setDate_added(double date_added) {
        this.date_added = date_added;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StartPointBean getStart_point() {
        return start_point;
    }

    public void setStart_point(StartPointBean start_point) {
        this.start_point = start_point;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public List<CoveredCountriesBean> getCovered_countries() {
        return covered_countries;
    }

    public void setCovered_countries(List<CoveredCountriesBean> covered_countries) {
        this.covered_countries = covered_countries;
    }

    public List<String> getCity_slug_urls() {
        return city_slug_urls;
    }

    public void setCity_slug_urls(List<String> city_slug_urls) {
        this.city_slug_urls = city_slug_urls;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<DaysBean> getDays() {
        return days;
    }

    public void setDays(List<DaysBean> days) {
        this.days = days;
    }

    public static class UserBean {


        private int gender;
        private boolean email_verified;
        private String location_name;
        private String name;
        private int resident_city_id;
        private String mobile;
        private String avatar_s;
        private String cover;
        private String custom_url;
        private Object country_code;
        private String email;
        private String birthday;
        private Object country_num;
        private String avatar_l;
        private String avatar_m;
        private boolean is_hunter;
        private boolean cdc2;
        private long id;
        private String user_desc;
        private int points;

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public boolean isEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(boolean email_verified) {
            this.email_verified = email_verified;
        }

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

        public String getAvatar_s() {
            return avatar_s;
        }

        public void setAvatar_s(String avatar_s) {
            this.avatar_s = avatar_s;
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

        public Object getCountry_code() {
            return country_code;
        }

        public void setCountry_code(Object country_code) {
            this.country_code = country_code;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getAvatar_l() {
            return avatar_l;
        }

        public void setAvatar_l(String avatar_l) {
            this.avatar_l = avatar_l;
        }

        public String getAvatar_m() {
            return avatar_m;
        }

        public void setAvatar_m(String avatar_m) {
            this.avatar_m = avatar_m;
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

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

    public static class CoveredCountriesBean {
        /**
         * icon : http://media.breadtrip.com/images/icons/passport/CN.png
         * type : 1
         * id : 3793
         * name : 中国
         */

        private String icon;
        private int type;
        private int id;
        private String name;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class DaysBean {

        private String date;
        private int day;
        private List<WaypointsBean> waypoints;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public List<WaypointsBean> getWaypoints() {
            return waypoints;
        }

        public void setWaypoints(List<WaypointsBean> waypoints) {
            this.waypoints = waypoints;
        }

        public static class WaypointsBean {
            /**
             * province :
             * hotel : null
             * photo_s : http://photos.breadtrip.com/photo_2015_12_06_8efcf892db6bfde9a1f76ba32d2121ed.jpg?imageView/1/w/280/h/280/q/75
             * track : false
             * photo : http://photos.breadtrip.com/photo_2015_12_06_8efcf892db6bfde9a1f76ba32d2121ed.jpg?imageView/2/w/960/q/85
             * recommendations : 24
             * photo_1600 : http://photos.breadtrip.com/photo_2015_12_06_8efcf892db6bfde9a1f76ba32d2121ed.jpg?imageView/2/w/1384/h/1384/q/85
             * video : {}
             * date_added : 1.448499E9
             * device : 1
             * photo_info : {"h":900,"w":1600}
             * trip_id : 2387086978
             * id : 2350188462
             * poi : null
             * city :
             * privacy : 0
             * shared : 1
             * country :
             * photo_w640 : http://photos.breadtrip.com/photo_2015_12_06_8efcf892db6bfde9a1f76ba32d2121ed.jpg?imageView/1/w/640/h/480/q/85
             * comments : 8
             * day : 1
             * timezone : Asia/Pontianak
             * recommended : false
             * photo_webtrip : http://photos.breadtrip.com/photo_2015_12_06_8efcf892db6bfde9a1f76ba32d2121ed.jpg?imageView/2/w/640/q/85
             * place : {}
             * local_time : 2015-11-26 07:50:00
             * text : 正值北京超低气温+重度雾霾的时刻，我们幸运的获得面包旅行狮城玩乐季大赏，可以前往与北京有温差却无时差的新加坡，开始我的冬日暖行！（感谢miumiu在大片中友情客串）
             * photo_weblive : http://photos.breadtrip.com/photo_2015_12_06_8efcf892db6bfde9a1f76ba32d2121ed.jpg?imageView/2/w/278/q/75
             * model : null
             * location : null
             */

            private String province;
            private Object hotel;
            private String photo_s;
            private boolean track;
            private String photo;
            private int recommendations;
            private String photo_1600;
            private VideoBean video;
            private double date_added;
            private int device;
            private PhotoInfoBean photo_info;
            private long trip_id;
            private long id;
            private Object poi;
            private String city;
            private int privacy;
            private int shared;
            private String country;
            private String photo_w640;
            private int comments;
            private int day;
            private String timezone;
            private boolean recommended;
            private String photo_webtrip;
            private PlaceBean place;
            private String local_time;
            private String text;
            private String photo_weblive;
            private Object model;
            private Object location;

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public Object getHotel() {
                return hotel;
            }

            public void setHotel(Object hotel) {
                this.hotel = hotel;
            }

            public String getPhoto_s() {
                return photo_s;
            }

            public void setPhoto_s(String photo_s) {
                this.photo_s = photo_s;
            }

            public boolean isTrack() {
                return track;
            }

            public void setTrack(boolean track) {
                this.track = track;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public int getRecommendations() {
                return recommendations;
            }

            public void setRecommendations(int recommendations) {
                this.recommendations = recommendations;
            }

            public String getPhoto_1600() {
                return photo_1600;
            }

            public void setPhoto_1600(String photo_1600) {
                this.photo_1600 = photo_1600;
            }

            public VideoBean getVideo() {
                return video;
            }

            public void setVideo(VideoBean video) {
                this.video = video;
            }

            public double getDate_added() {
                return date_added;
            }

            public void setDate_added(double date_added) {
                this.date_added = date_added;
            }

            public int getDevice() {
                return device;
            }

            public void setDevice(int device) {
                this.device = device;
            }

            public PhotoInfoBean getPhoto_info() {
                return photo_info;
            }

            public void setPhoto_info(PhotoInfoBean photo_info) {
                this.photo_info = photo_info;
            }

            public long getTrip_id() {
                return trip_id;
            }

            public void setTrip_id(long trip_id) {
                this.trip_id = trip_id;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public Object getPoi() {
                return poi;
            }

            public void setPoi(Object poi) {
                this.poi = poi;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getPrivacy() {
                return privacy;
            }

            public void setPrivacy(int privacy) {
                this.privacy = privacy;
            }

            public int getShared() {
                return shared;
            }

            public void setShared(int shared) {
                this.shared = shared;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getPhoto_w640() {
                return photo_w640;
            }

            public void setPhoto_w640(String photo_w640) {
                this.photo_w640 = photo_w640;
            }

            public int getComments() {
                return comments;
            }

            public void setComments(int comments) {
                this.comments = comments;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public boolean isRecommended() {
                return recommended;
            }

            public void setRecommended(boolean recommended) {
                this.recommended = recommended;
            }

            public String getPhoto_webtrip() {
                return photo_webtrip;
            }

            public void setPhoto_webtrip(String photo_webtrip) {
                this.photo_webtrip = photo_webtrip;
            }

            public PlaceBean getPlace() {
                return place;
            }

            public void setPlace(PlaceBean place) {
                this.place = place;
            }

            public String getLocal_time() {
                return local_time;
            }

            public void setLocal_time(String local_time) {
                this.local_time = local_time;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getPhoto_weblive() {
                return photo_weblive;
            }

            public void setPhoto_weblive(String photo_weblive) {
                this.photo_weblive = photo_weblive;
            }

            public Object getModel() {
                return model;
            }

            public void setModel(Object model) {
                this.model = model;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public static class VideoBean {
            }

            public static class PhotoInfoBean {
                /**
                 * h : 900
                 * w : 1600
                 */

                private int h;
                private int w;

                public int getH() {
                    return h;
                }

                public void setH(int h) {
                    this.h = h;
                }

                public int getW() {
                    return w;
                }

                public void setW(int w) {
                    this.w = w;
                }
            }

            public static class PlaceBean {
            }
        }
    }
}
