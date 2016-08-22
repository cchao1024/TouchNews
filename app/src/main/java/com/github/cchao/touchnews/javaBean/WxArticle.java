package com.github.cchao.touchnews.javaBean;

import java.util.List;

/**
 * Created by cchao on 2016/8/19.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class WxArticle {
        private int showapi_res_code;
        private String showapi_res_error;

        private ShowapiResBodyBean showapi_res_body;

        public int getShowapi_res_code ( ) { return showapi_res_code;}

        public void setShowapi_res_code ( int showapi_res_code ) { this.showapi_res_code = showapi_res_code;}

        public String getShowapi_res_error ( ) { return showapi_res_error;}

        public void setShowapi_res_error ( String showapi_res_error ) { this.showapi_res_error = showapi_res_error;}

        public ShowapiResBodyBean getShowapi_res_body ( ) { return showapi_res_body;}

        public void setShowapi_res_body ( ShowapiResBodyBean showapi_res_body ) { this.showapi_res_body = showapi_res_body;}

        public static class ShowapiResBodyBean {
                private int ret_code;

                private PagebeanBean pagebean;

                public int getRet_code ( ) { return ret_code;}

                public void setRet_code ( int ret_code ) { this.ret_code = ret_code;}

                public PagebeanBean getPagebean ( ) { return pagebean;}

                public void setPagebean ( PagebeanBean pagebean ) { this.pagebean = pagebean;}

                public static class PagebeanBean {
                        private int allPages;
                        private int currentPage;
                        private int allNum;
                        private int maxResult;
                        /**
                         * id : 57b67d396e36480d093bff52
                         * typeName : 段子手
                         * title : 我只是近视，怎么就成了段子一样的人生｜不服
                         * contentImg : http://mmbiz.qpic.cn/mmbiz_jpg/MuRjYtYZecVCmIbGXLZO182Sibw5UDQRIibiaEdE9npibXoiaH60ibeOlTLeRu6qoYAzQY1MKZr5VbkicmEQPJSBRBYKw/0?wx_fmt=jpeg
                         * userLogo : http://mmbiz.qpic.cn/mmbiz/MuRjYtYZecXMfe9dWU2qC5SS4QWPepy8pozb5lkuL2gazvTu86nT0Sog864YTOhGwXjYRunEWy413bHJsIJaew/0?wx_fmt=png
                         * userName : 新晚报
                         * date : 2016-08-19 06:13:29
                         * typeId : 2
                         * url : http://mp.weixin.qq.com/s?__biz=MjM5NTU1MTY5Mg==&mid=2653983216&idx=5&sn=98ac03e312067ac1ddb3959c7b0d655b#rd
                         * weixinNum : xinwanbao
                         * userLogo_code : http://open.weixin.qq.com/qr/code/?username=xinwanbao
                         */

                        private List< ContentlistBean > contentlist;

                        public int getAllPages ( ) { return allPages;}

                        public void setAllPages ( int allPages ) { this.allPages = allPages;}

                        public int getCurrentPage ( ) { return currentPage;}

                        public void setCurrentPage ( int currentPage ) { this.currentPage = currentPage;}

                        public int getAllNum ( ) { return allNum;}

                        public void setAllNum ( int allNum ) { this.allNum = allNum;}

                        public int getMaxResult ( ) { return maxResult;}

                        public void setMaxResult ( int maxResult ) { this.maxResult = maxResult;}

                        public List< ContentlistBean > getContentlist ( ) { return contentlist;}

                        public void setContentlist ( List< ContentlistBean > contentlist ) { this.contentlist = contentlist;}

                        public static class ContentlistBean {
                                private String id;
                                private String typeName;
                                private String title;
                                private String contentImg;
                                private String userLogo;
                                private String userName;
                                private String date;
                                private String typeId;
                                private String url;
                                private String weixinNum;
                                private String userLogo_code;

                                public String getId ( ) { return id;}

                                public void setId ( String id ) { this.id = id;}

                                public String getTypeName ( ) { return typeName;}

                                public void setTypeName ( String typeName ) { this.typeName = typeName;}

                                public String getTitle ( ) { return title;}

                                public void setTitle ( String title ) { this.title = title;}

                                public String getContentImg ( ) { return contentImg;}

                                public void setContentImg ( String contentImg ) { this.contentImg = contentImg;}

                                public String getUserLogo ( ) { return userLogo;}

                                public void setUserLogo ( String userLogo ) { this.userLogo = userLogo;}

                                public String getUserName ( ) { return userName;}

                                public void setUserName ( String userName ) { this.userName = userName;}

                                public String getDate ( ) { return date;}

                                public void setDate ( String date ) { this.date = date;}

                                public String getTypeId ( ) { return typeId;}

                                public void setTypeId ( String typeId ) { this.typeId = typeId;}

                                public String getUrl ( ) { return url;}

                                public void setUrl ( String url ) { this.url = url;}

                                public String getWeixinNum ( ) { return weixinNum;}

                                public void setWeixinNum ( String weixinNum ) { this.weixinNum = weixinNum;}

                                public String getUserLogo_code ( ) { return userLogo_code;}

                                public void setUserLogo_code ( String userLogo_code ) { this.userLogo_code = userLogo_code;}
                        }
                }
        }
}
