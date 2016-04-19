package com.github.armstrong.touchnews.javaBean.music;

/**
 * Created by cchao on 2016/4/19.
 * E-mail:   cchao1024@163.com
 * Description:
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

        public void setFilename(String filename){
                this.filename = filename;
        }
        public String getFilename(){
                return this.filename;
        }
        public void setSongname(String songname){
                this.songname = songname;
        }
        public String getSongname(){
                return this.songname;
        }
        public void setM4afilesize(int m4afilesize){
                this.m4afilesize = m4afilesize;
        }
        public int getM4afilesize(){
                return this.m4afilesize;
        }
        public void setFilesize(int filesize){
                this.filesize = filesize;
        }
        public int getFilesize(){
                return this.filesize;
        }
        public void setBitrate(int bitrate){
                this.bitrate = bitrate;
        }
        public int getBitrate(){
                return this.bitrate;
        }
        public void setAlbum_name(String album_name){
                this.album_name = album_name;
        }
        public String getAlbum_name(){
                return this.album_name;
        }
        public void setIsnew(int isnew){
                this.isnew = isnew;
        }
        public int getIsnew(){
                return this.isnew;
        }
        public void setDuration(int duration){
                this.duration = duration;
        }
        public int getDuration(){
                return this.duration;
        }
        public void setSingername(String singername){
                this.singername = singername;
        }
        public String getSingername(){
                return this.singername;
        }
        public void setExtname(String extname){
                this.extname = extname;
        }
        public String getExtname(){
                return this.extname;
        }
        public void setHash(String hash){
                this.hash = hash;
        }
        public String getHash(){
                return this.hash;
        }
        public void setOthername(String othername){
                this.othername = othername;
        }
        public String getOthername(){
                return this.othername;
        }

}
