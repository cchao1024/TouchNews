package com.github.armstrong.touchnews.javaBean.music;

/**
 * Created by cchao on 2016/4/18.
 * E-mail:   cchao1024@163.com
 * Description: 音乐的Hash 实体数据
 */
public class MusicInfoEntity {
        /*"data": {
                        "bitRate": 128,
                        "hash": "370CE1F6507BC5F78B49587A31411212",
                        "fileName": "TFBOYS - 青春修炼手册",
                        "fileSize": 4210101,
                        "url": "http://song1.music.response.itmf.cn/e910975bb4819f4ad301f9252d139e1d/57148e6e/G010/M02/0D/10/Sg0DAFULHliAHkJtABCC1wRsoR0534.m4a",
                        "extName": "m4a",
                        "timeLength": 263
        }*/
        private int bitRate;

        private String hash;

        private String fileName;

        private int fileSize;

        private String url;

        private String extName;

        private int timeLength;

        public void setBitRate ( int bitRate ) {
                this.bitRate = bitRate;
        }

        public int getBitRate ( ) {
                return this.bitRate;
        }

        public void setHash ( String hash ) {
                this.hash = hash;
        }

        public String getHash ( ) {
                return this.hash;
        }

        public void setFileName ( String fileName ) {
                this.fileName = fileName;
        }

        public String getFileName ( ) {
                return this.fileName;
        }

        public void setFileSize ( int fileSize ) {
                this.fileSize = fileSize;
        }

        public int getFileSize ( ) {
                return this.fileSize;
        }

        public void setUrl ( String url ) {
                this.url = url;
        }

        public String getUrl ( ) {
                return this.url;
        }

        public void setExtName ( String extName ) {
                this.extName = extName;
        }

        public String getExtName ( ) {
                return this.extName;
        }

        public void setTimeLength ( int timeLength ) {
                this.timeLength = timeLength;
        }

        public int getTimeLength ( ) {
                return this.timeLength;
        }
}
