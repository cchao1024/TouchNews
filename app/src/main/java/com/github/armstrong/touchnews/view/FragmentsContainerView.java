package com.github.armstrong.touchnews.view;

import java.util.List;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description: Fragments容器View ,为初始其子Fragments
 */
public interface FragmentsContainerView {
        /**
         * 初始化  FragmentViewpager
         * @param fragments ViewPager数据源
         */
        void initFragment( List fragments ,String[] titles);
}
