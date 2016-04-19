package com.github.armstrong.touchnews.javaBean.music;

import java.util.List;

/**
 * Created by cchao on 2016/4/19.
 * E-mail:   cchao1024@163.com
 * Description:  返回的数据里有两个Data键 歌曲信息的Data 在同级文件夹Data类，这个静态内部类是外层的Data
 */
/*
{
    "code": 0,
    "status": "success",
    "msg": "数据请求成功",
    "data": {
        "current_page": "1",
        "keyword": "流行",
        "total_rows": 251,
        "total_page": 26,
        "page_size": "10",
        "data": [
            {
                "filename": "TFBOYS - 青春修炼手册",
                "songname": "青春修炼手册",
                "m4afilesize": 1083528,
                "filesize": 4210101,
                "bitrate": 128,
                "album_name": "青春修炼手册",
                "isnew": 0,
                "duration": 263,
                "singername": "TFBOYS",
                "extname": "mp3",
                "hash": "370ce1f6507bc5f78b49587a31411212",
                "othername": ""
            },
* */
public  class MusicHashRoot {
        private int code;

        private String status;

        private String msg;

        private Data data;

        public void setCode(int code){
                this.code = code;
        }
        public int getCode(){
                return this.code;
        }
        public void setStatus(String status){
                this.status = status;
        }
        public String getStatus(){
                return this.status;
        }
        public void setMsg(String msg){
                this.msg = msg;
        }
        public String getMsg(){
                return this.msg;
        }
        public void setData(Data data){
                this.data = data;
        }
        public Data getData(){
                return this.data;
        }

        public static class Data {
                private String current_page;

                private String keyword;

                private int total_rows;

                private int total_page;

                private String page_size;

                private List< com.github.armstrong.touchnews.javaBean.music.Data> data ;

                public void setCurrent_page(String current_page){
                        this.current_page = current_page;
                }
                public String getCurrent_page(){
                        return this.current_page;
                }
                public void setKeyword(String keyword){
                        this.keyword = keyword;
                }
                public String getKeyword(){
                        return this.keyword;
                }
                public void setTotal_rows(int total_rows){
                        this.total_rows = total_rows;
                }
                public int getTotal_rows(){
                        return this.total_rows;
                }
                public void setTotal_page(int total_page){
                        this.total_page = total_page;
                }
                public int getTotal_page(){
                        return this.total_page;
                }
                public void setPage_size(String page_size){
                        this.page_size = page_size;
                }
                public String getPage_size(){
                        return this.page_size;
                }
                public void setData(List< com.github.armstrong.touchnews.javaBean.music.Data> data){
                        this.data = data;
                }
                public List< com.github.armstrong.touchnews.javaBean.music.Data> getData(){
                        return this.data;
                }

        }
}

