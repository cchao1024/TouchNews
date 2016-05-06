package com.github.armstrong.touchnews.javaBean;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by CCH on 2016/4/10.
 * 天气JavaBean
 "HeWeather data service 3.0": ［

 "status": "ok", //接口状态，参考http://www.heweather.com/documents/api
 "basic": {  //基本信息
 "city": "北京",  //城市名称
 "cnty": "中国",  //国家
 "id": "CN101010100",  //城市ID，参见http://www.heweather.com/documents/cn-city-list
 "lat": "39.904000",  //城市维度
 "lon": "116.391000",  //城市经度
 "update": {  //更新时间
 "loc": "2015-07-02 14:44", //当地时间
 "utc": "2015-07-02 06:46"  //UTC时间
 }
 },
 "now": { //实况天气
 "cond": { //天气状况
 "code": "100", //天气状况代码
 "txt": "晴" //天气状况描述
 },
 "fl": "30", //体感温度
 "hum": "20%", //相对湿度（%）
 "pcpn": "0.0", //降水量（mm）
 "pres": "1001", //气压
 "tmp": "32", //温度
 "vis": "10", //能见度（km）
 "wind": { //风力风向
 "deg": "10", //风向（360度）
 "dir": "北风", //风向
 "sc": "3级", //风力
 "spd": "15" //风速（kmph）
 }
 },

 "aqi": { //空气质量，仅限国内部分城市，国际城市无此字段
 "city": {
 "aqi": "30", //空气质量指数
 "co": "0", //一氧化碳1小时平均值(ug/m³)
 "no2": "10", //二氧化氮1小时平均值(ug/m³)
 "o3": "94", //臭氧1小时平均值(ug/m³)
 "pm10": "10", //PM10 1小时平均值(ug/m³)
 "pm25": "7", //PM2.5 1小时平均值(ug/m³)
 "qlty": "优", //空气质量类别
 "so2": "3" //二氧化硫1小时平均值(ug/m³)
 }
 },

 "daily_forecast": [ //7天天气预报
 {
 "date": "2015-07-02", //预报日期
 "astro": { //天文数值
 "sr": "04:50", //日出时间
 "ss": "19:47" //日落时间
 },
 "cond": { //天气状况
 "code_d": "100", //白天天气状况代码，参考http://www.heweather.com/documents/condition-code
 "code_n": "100", //夜间天气状况代码
 "txt_d": "晴", //白天天气状况描述
 "txt_n": "晴" //夜间天气状况描述
 },
 "hum": "14", //相对湿度（%）
 "pcpn": "0.0", //降水量（mm）
 "pop": "0", //降水概率
 "pres": "1003", //气压
 "tmp": { //温度
 "max": "34℃", //最高温度
 "min": "18℃" //最低温度
 },
 "vis": "10", //能见度（km）
 "wind": { //风力风向
 "deg": "339", //风向（360度）
 "dir": "东南风", //风向
 "sc": "3-4", //风力
 "spd": "15" //风速（kmph）
 }
 },
 ......  //略

 ],
 "hourly_forecast": [ //每三小时天气预报，全能版为每小时预报
 {
 "date": "2015-07-02 01:00", //时间
 "hum": "43", //相对湿度（%）
 "pop": "0", //降水概率
 "pres": "1003", //气压
 "tmp": "25", //温度
 "wind": { //风力风向
 "deg": "320", //风向（360度）
 "dir": "西北风", //风向
 "sc": "微风", //风力
 "spd": "12" //风速（kmph）
 }
 },
 ......  //略

 ],

 "suggestion": { //生活指数，仅限国内城市，国际城市无此字段
 "comf": { //舒适度指数
 "brf": "较不舒适", //简介
 "txt": "白天天气多云，同时会感到有些热，不很舒适。" //详细描述
 },
 "cw": { //洗车指数
 "brf": "较适宜",
 "txt": "较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"
 },
 "drsg": { //穿衣指数
 "brf": "炎热",
 "txt": "天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。"
 },
 "flu": { //感冒指数
 "brf": "少发",
 "txt": "各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"
 },
 "sport": { //运动指数
 "brf": "较适宜",
 "txt": "天气较好，户外运动请注意防晒。推荐您进行室内运动。"
 },
 "trav": { //旅游指数
 "brf": "较适宜",
 "txt": "天气较好，温度较高，天气较热，但有微风相伴，还是比较适宜旅游的，不过外出时要注意防暑防晒哦！"
 },
 "uv": { //紫外线指数
 "brf": "中等",
 "txt": "属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"
 }
 }
 }
 ］
 ｝
 */


public  class Weather{
    private Aqi aqi;

    private Basic basic;

    private List<Daily_forecast> Daily_forecast;

    private List<Hourly_forecast> Hourly_forecast;

    private Now now;

    private String status;

    private Suggestion suggestion;

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    public Aqi getAqi() {
        return this.aqi;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Basic getBasic() {
        return this.basic;
    }

    public void setDaily_forecast(List<Daily_forecast> daily_forecast) {
        this.Daily_forecast = daily_forecast;
    }

    public List<Daily_forecast> getDaily_forecast() {
        return this.Daily_forecast;
    }

    public void setHourly_forecast(List<Hourly_forecast> hourly_forecast) {
        this.Hourly_forecast = hourly_forecast;
    }

    public List<Hourly_forecast> getHourly_forecast() {
        return this.Hourly_forecast;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public Now getNow() {
        return this.now;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public Suggestion getSuggestion() {
        return this.suggestion;
    }


    public  static class City {
        private String aqi;

        private String co;

        private String no2;

        private String o3;

        private String pm10;

        private String pm25;

        private String qlty;

        private String so2;

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getAqi() {
            return this.aqi;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getCo() {
            return this.co;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getNo2() {
            return this.no2;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getO3() {
            return this.o3;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm10() {
            return this.pm10;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm25() {
            return this.pm25;
        }

        public void setQlty(String qlty) {
            this.qlty = qlty;
        }

        public String getQlty() {
            return this.qlty;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getSo2() {
            return this.so2;
        }

    }

    public static class Aqi {
        private City city;

        public void setCity(City city) {
            this.city = city;
        }

        public City getCity() {
            return this.city;
        }

    }

    public static class Update {
        private String loc;

        private String utc;

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public String getLoc() {
            return this.loc;
        }

        public void setUtc(String utc) {
            this.utc = utc;
        }

        public String getUtc() {
            return this.utc;
        }

    }

    public static class Basic {
        private String city;

        private String cnty;

        private String id;

        private String lat;

        private String lon;

        private Update update;

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return this.city;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getCnty() {
            return this.cnty;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLat() {
            return this.lat;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLon() {
            return this.lon;
        }

        public void setUpdate(Update update) {
            this.update = update;
        }

        public Update getUpdate() {
            return this.update;
        }

    }

    public static class Cond {
        private String code;

        private String txt;

        public void setCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getTxt() {
            return this.txt;
        }

    }

    public static class Wind {
        private String deg;

        private String dir;

        private String sc;

        private String spd;

        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getDeg() {
            return this.deg;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getDir() {
            return this.dir;
        }

        public void setSc(String sc) {
            this.sc = sc;
        }

        public String getSc() {
            return this.sc;
        }

        public void setSpd(String spd) {
            this.spd = spd;
        }

        public String getSpd() {
            return this.spd;
        }

    }

    public static class Now {
        private Cond cond;

        private String fl;

        private String hum;

        private String pcpn;

        private String pres;

        private String tmp;

        private String vis;

        private Wind wind;

        public void setCond(Cond cond) {
            this.cond = cond;
        }

        public Cond getCond() {
            return this.cond;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getFl() {
            return this.fl;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getHum() {
            return this.hum;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPcpn() {
            return this.pcpn;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getPres() {
            return this.pres;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getTmp() {
            return this.tmp;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getVis() {
            return this.vis;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Wind getWind() {
            return this.wind;
        }

    }

    public static class Comf {
        private String brf;

        private String txt;

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getBrf() {
            return this.brf;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getTxt() {
            return this.txt;
        }

    }

    public static class Cw {
        private String brf;

        private String txt;

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getBrf() {
            return this.brf;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getTxt() {
            return this.txt;
        }

    }

    public static class Drsg {
        private String brf;

        private String txt;

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getBrf() {
            return this.brf;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getTxt() {
            return this.txt;
        }

    }

    public static class Flu {
        private String brf;

        private String txt;

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getBrf() {
            return this.brf;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getTxt() {
            return this.txt;
        }

    }

    public static class Sport {
        private String brf;

        private String txt;

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getBrf() {
            return this.brf;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getTxt() {
            return this.txt;
        }

    }

    public static class Trav {
        private String brf;

        private String txt;

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getBrf() {
            return this.brf;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getTxt() {
            return this.txt;
        }

    }

    public static class Uv {
        private String brf;

        private String txt;

        public void setBrf(String brf) {
            this.brf = brf;
        }

        public String getBrf() {
            return this.brf;
        }

        public void setTxt(String txt) {
            this.txt = txt;
        }

        public String getTxt() {
            return this.txt;
        }

    }

    public static class Suggestion {
        private Comf comf;

        private Cw cw;

        private Drsg drsg;

        private Flu flu;

        private Sport sport;

        private Trav trav;

        private Uv uv;

        public void setComf(Comf comf) {
            this.comf = comf;
        }

        public Comf getComf() {
            return this.comf;
        }

        public void setCw(Cw cw) {
            this.cw = cw;
        }

        public Cw getCw() {
            return this.cw;
        }

        public void setDrsg(Drsg drsg) {
            this.drsg = drsg;
        }

        public Drsg getDrsg() {
            return this.drsg;
        }

        public void setFlu(Flu flu) {
            this.flu = flu;
        }

        public Flu getFlu() {
            return this.flu;
        }

        public void setSport(Sport sport) {
            this.sport = sport;
        }

        public Sport getSport() {
            return this.sport;
        }

        public void setTrav(Trav trav) {
            this.trav = trav;
        }

        public Trav getTrav() {
            return this.trav;
        }

        public void setUv(Uv uv) {
            this.uv = uv;
        }

        public Uv getUv() {
            return this.uv;
        }

    }

    public static class Astro {
        private String sr;

        private String ss;

        public void setSr(String sr) {
            this.sr = sr;
        }

        public String getSr() {
            return this.sr;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }

        public String getSs() {
            return this.ss;
        }

    }

    public static class Tmp {
        private String max;

        private String min;

        public void setMax(String max) {
            this.max = max;
        }

        public String getMax() {
            return this.max;
        }

        public void setMin(String min) {
            this.min = min;
        }

        public String getMin() {
            return this.min;
        }

    }

    public static class Daily_forecast {
        private Astro astro;

        private Cond cond;

        private String date;

        private String hum;

        private String pcpn;

        private String pop;

        private String pres;

        private Tmp tmp;

        private String vis;

        private Wind wind;

        public void setAstro(Astro astro) {
            this.astro = astro;
        }

        public Astro getAstro() {
            return this.astro;
        }

        public void setCond(Cond cond) {
            this.cond = cond;
        }

        public Cond getCond() {
            return this.cond;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDate() {
            return this.date;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getHum() {
            return this.hum;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPcpn() {
            return this.pcpn;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPop() {
            return this.pop;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getPres() {
            return this.pres;
        }

        public void setTmp(Tmp tmp) {
            this.tmp = tmp;
        }

        public Tmp getTmp() {
            return this.tmp;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getVis() {
            return this.vis;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Wind getWind() {
            return this.wind;
        }

    }

    public static class Hourly_forecast {
        private String date;

        private String hum;

        private String pop;

        private String pres;

        private String tmp;

        private Wind wind;

        public void setDate(String date) {
            this.date = date;
        }

        public String getDate() {
            return this.date;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getHum() {
            return this.hum;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPop() {
            return this.pop;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getPres() {
            return this.pres;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getTmp() {
            return this.tmp;
        }

        public void setWind(Wind wind) {
            this.wind = wind;
        }

        public Wind getWind() {
            return this.wind;
        }

    }
}