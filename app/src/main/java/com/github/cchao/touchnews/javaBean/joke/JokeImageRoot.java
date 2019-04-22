package com.github.cchao.touchnews.javaBean.joke;

import java.util.List;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description:
 */
/*
{
    "showapi_res_code": 0,
    "showapi_res_error": "",
    "showapi_res_body": {
        "allNum": 28928,
        "allPages": 1447,
        "contentlist": [
            {
                "ct": "2016-04-25 02:30:39.704",
                "img": "http://img5.hao123.com/data/3_1d1c26faf76a9523c7c9071f1e65226d_430",
                "title": "非常有生活气息的工艺品",
                "type": 2
            },
            {
                "ct": "2016-04-24 21:30:47.980",
                "img": "http://img0.hao123.com/data/3_c06d0b29050afa070d19c67af372bf88_430",
                "title": "快要被玩坏的节奏呀",
                "type": 2
            }
        ],
        "currentPage": 1,
        "maxResult": 20,
        "ret_code": 0
    }
}

*/
public class JokeImageRoot {
    private int showapi_res_code;

    private String showapi_res_error;

    private Showapi_res_body showapi_res_body;

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public int getShowapi_res_code() {
        return this.showapi_res_code;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public String getShowapi_res_error() {
        return this.showapi_res_error;
    }

    public void setShowapi_res_body(Showapi_res_body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public Showapi_res_body getShowapi_res_body() {
        return this.showapi_res_body;
    }

    public static class Showapi_res_body {
        private int allNum;

        private int allPages;

        private List<Contentlist> contentlist;

        private int currentPage;

        private int maxResult;

        private int ret_code;

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }

        public int getAllNum() {
            return this.allNum;
        }

        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }

        public int getAllPages() {
            return this.allPages;
        }

        public void setContentlist(List<Contentlist> contentlist) {
            this.contentlist = contentlist;
        }

        public List<Contentlist> getContentlist() {
            return this.contentlist;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentPage() {
            return this.currentPage;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }

        public int getMaxResult() {
            return this.maxResult;
        }

        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }

        public int getRet_code() {
            return this.ret_code;
        }

    }

    /**
     * Created by cchao on 2016/4/25.
     * E-mail:   cchao1024@163.com
     * Description:
     */
    public static class Contentlist {
        private String ct;

        private String img;

        private String title;

        private int type;

        public void setCt(String ct) {
            this.ct = ct;
        }

        public String getCt() {
            return this.ct;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg() {
            return this.img;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return this.type;
        }

    }
}
