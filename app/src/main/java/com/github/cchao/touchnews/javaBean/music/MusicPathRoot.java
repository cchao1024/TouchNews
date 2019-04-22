package com.github.cchao.touchnews.javaBean.music;

/**
 * Created by cchao on 2016/4/19.
 * E-mail:   cchao1024@163.com
 * Description:
 */
/*
{
    "code": 0,
    "status": "success",
    "msg": "数据请求成功",
    "data": {
        "bitRate": 128,
        "fileSize": 3223345,
        "hash": "C23D025EE9ECE593ABD96D7B97DB97B4",
        "fileName": "赵丽颖 - 十年 - 消音版伴奏",
        "url": "http://song1.music.response.itmf.cn/dec993847ececf02c9680de1bda3b9ec/5716235e/G044/M0A/01/01/DJQEAFYXd7SIfnevAAydc18Lb08AAAKhAGS18cADJ2L575.m4a",
        "extName": "m4a",
        "timeLength": 201
    }
}
 */
public class MusicPathRoot {
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
        private int bitRate;

        private int fileSize;

        private String hash;

        private String fileName;

        private String url;

        private String extName;

        private int timeLength;

        public int getBitRate() {
            return this.bitRate;
        }

        public void setBitRate(int bitRate) {
            this.bitRate = bitRate;
        }

        public int getFileSize() {
            return this.fileSize;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }

        public String getHash() {
            return this.hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getFileName() {
            return this.fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getExtName() {
            return this.extName;
        }

        public void setExtName(String extName) {
            this.extName = extName;
        }

        public int getTimeLength() {
            return this.timeLength;
        }

        public void setTimeLength(int timeLength) {
            this.timeLength = timeLength;
        }

    }
}
