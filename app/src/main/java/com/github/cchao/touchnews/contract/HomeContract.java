package com.github.cchao.touchnews.contract;

import com.github.cchao.touchnews.javaBean.Weather;

import java.util.List;

/**
 * Created by cchao on 2016/8/29.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public interface HomeContract {
    interface View {
        /**
         * 初始化Home 的主界面Viewpager
         *
         * @param fragments ViewPager数据源
         */
        void setFragmentPager(List fragments);

        /**
         * 初始化Home 的抽屉数据，如天气显示
         */
        void setNavigation(Weather weather);
    }

    interface Presenter {
        void getFragments();

        void getNavigation();
    }
}
