package com.github.cchao.touchnews.ui.fragment;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.presenter.BasePresenter;
import com.github.cchao.touchnews.presenter.WxSelectPresenter;
import com.github.cchao.touchnews.ui.fragment.base.PresentFragment;

/**
 * Created by cchao on 2016/8/18.
 * E-mail:   cchao1024@163.com
 * Description: 微信精选
 */
public class WxSelectFragment extends PresentFragment {

        @Override
        protected int getLayoutId ( ) {
                return R.layout.fragment_wx_select;
        }

        @Override
        protected BasePresenter getPresenter ( ) {
                return new WxSelectPresenter ( );
        }
}
