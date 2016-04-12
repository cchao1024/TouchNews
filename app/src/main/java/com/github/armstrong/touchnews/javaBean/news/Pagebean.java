package com.github.armstrong.touchnews.javaBean.news;

import java.util.List;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class Pagebean {
        private int allNum;

        private int allPages;

        private List< Contentlist > contentlist;

        private int currentPage;

        private int maxResult;

        public void setAllNum ( int allNum ) {
                this.allNum = allNum;
        }

        public int getAllNum ( ) {
                return this.allNum;
        }

        public void setAllPages ( int allPages ) {
                this.allPages = allPages;
        }

        public int getAllPages ( ) {
                return this.allPages;
        }

        public void setContentlist ( List< Contentlist > contentlist ) {
                this.contentlist = contentlist;
        }

        public List< Contentlist > getContentlist ( ) {
                return this.contentlist;
        }

        public void setCurrentPage ( int currentPage ) {
                this.currentPage = currentPage;
        }

        public int getCurrentPage ( ) {
                return this.currentPage;
        }

        public void setMaxResult ( int maxResult ) {
                this.maxResult = maxResult;
        }

        public int getMaxResult ( ) {
                return this.maxResult;
        }

}
