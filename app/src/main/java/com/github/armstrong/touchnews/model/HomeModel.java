package com.github.armstrong.touchnews.model;

import com.github.armstrong.touchnews.model.i.IHomeModel;
import com.github.armstrong.touchnews.presenter.i.IHomePresenter;
import com.github.armstrong.touchnews.ui.fragment.ImageContainerFragment;
import com.github.armstrong.touchnews.ui.fragment.NewsContainerFragment;
import com.github.armstrong.touchnews.util.NetRequestUtil;
import com.github.armstrong.touchnews.util.UriUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cchao on 2016/3/31.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class HomeModel implements IHomeModel {
        IHomePresenter mHomePresenter;
        List mFragments;
        Map< String, String > param = new HashMap<>( );
        Map< String, String > headers = new HashMap<> ( );
        NetRequestUtil.RequestListener requestListener;
        public HomeModel ( IHomePresenter homePresenter, NetRequestUtil.RequestListener requestListener ) {
                this.requestListener=requestListener;
                mHomePresenter = homePresenter;
                headers.put ( "apikey", "1bb91df70ccde8148a2c3da582ca9ff2" );
        }
        @Override
        public List getFragments ( ) {
                mFragments=new ArrayList (  );
                mFragments.add ( new NewsContainerFragment () );
                mFragments.add ( new ImageContainerFragment () );
                mFragments.add ( null);
                mFragments.add ( null);
                return mFragments;
        }

        @Override
        public void loadNavigation() {
                param.put ( "city", "广州" );
                NetRequestUtil.getInstance().getJsonWithHeaders(UriUtil.URL_WEATHER,param,headers,requestListener);
        }

}
