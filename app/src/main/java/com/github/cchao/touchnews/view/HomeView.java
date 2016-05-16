package com.github.cchao.touchnews.view;

import com.github.cchao.touchnews.javaBean.Weather;

import java.util.List;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description: 主页面View
 */
public interface HomeView {
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
