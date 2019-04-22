package com.github.cchao.touchnews.javaBean.music;

/**
 * Created by cchao on 2016/4/21.
 * E-mail:   cchao1024@163.com
 * Description: 歌手信息
 */
/*
{
    "code": 0,
    "status": "success",
    "msg": "数据请求成功",
    "data": {
        "singername": "陈奕迅",
        "image": "http://img1.music.response.itmf.cn/uploadpic/pass/softhead/400/20160418/20160418100509394.jpg"
    }
}
*/
public class MusicSingerRoot {
    private int code;

    private String status;

    private String msg;

    private Data data;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private String singername;

        private String image;

        public String getSingername() {
            return this.singername;
        }

        public void setSingername(String singername) {
            this.singername = singername;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
}
