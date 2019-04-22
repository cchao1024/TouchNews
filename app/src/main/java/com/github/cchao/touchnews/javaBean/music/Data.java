package com.github.cchao.touchnews.javaBean.music;

/**
 * Created by cchao on 2016/4/19.
 * E-mail:   cchao1024@163.com
 * Description:
 */
/*
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

 */
public class Data {
    private String filename;

    private String songname;

    private int m4afilesize;

    private int filesize;

    private int bitrate;

    private String album_name;

    private int isnew;

    private int duration;

    private String singername;

    private String extname;

    private String hash;

    private String othername;

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getSongname() {
        return this.songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public int getM4afilesize() {
        return this.m4afilesize;
    }

    public void setM4afilesize(int m4afilesize) {
        this.m4afilesize = m4afilesize;
    }

    public int getFilesize() {
        return this.filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getAlbum_name() {
        return this.album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    public int getIsnew() {
        return this.isnew;
    }

    public void setIsnew(int isnew) {
        this.isnew = isnew;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSingername() {
        return this.singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public String getExtname() {
        return this.extname;
    }

    public void setExtname(String extname) {
        this.extname = extname;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getOthername() {
        return this.othername;
    }

    public void setOthername(String othername) {
        this.othername = othername;
    }

}
