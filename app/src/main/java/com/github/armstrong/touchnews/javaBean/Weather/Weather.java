package com.github.armstrong.touchnews.javaBean.Weather;

import java.util.List;

/**
 * Created by CCH on 2016/4/10.
 * 天气JavaBean
 */
public  class Weather {
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