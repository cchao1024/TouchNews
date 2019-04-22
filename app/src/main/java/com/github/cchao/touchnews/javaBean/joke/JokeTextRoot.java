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
        "allNum": 23185,
        "allPages": 1160,
        "contentlist": [
            {
                "ct": "2016-04-25 14:30:47.899",
                "text": "漂亮的女同事要结婚，我就问她相中她老公哪点了？她说:我就相中他实诚了！我说:怎么实诚？她说:别人求婚都是单膝跪地，他直接咕咚双膝跪下，磕头如捣蒜，老佛爷！求求你了，嫁给我吧！",
                "title": "教你个求婚新技能",
                "type": 1
            },
            {
                "ct": "2016-04-25 11:30:37.110",
                "text": "我说：“老婆我们离婚吧。房子存款都给你，我净身出户。”　　她把菜刀立案板上说：“来吧，先净身，你就可以走了。”",
                "title": "我打算净身出户",
                "type": 1
            }
        ],
        "currentPage": 1,
        "maxResult": 20,
        "ret_code": 0
    }
}
*/
public class JokeTextRoot {
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

        private String text;

        private String title;

        private int type;

        public void setCt(String ct) {
            this.ct = ct;
        }

        public String getCt() {
            return this.ct;
        }

        public void setText(String img) {
            this.text = img;
        }

        public String getText() {
            return this.text;
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
