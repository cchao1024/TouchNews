package com.github.cchao.touchnews.model;

import com.github.cchao.touchnews.model.i.IChatModel;
import com.github.cchao.touchnews.presenter.i.IChatPresenter;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.UrlUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class ChatModel implements IChatModel {
        Map< String, String > param = new HashMap<> ( );
        IChatPresenter mChatPresenter;
        NetRequestUtil.RequestListener mRequestListener;

        public ChatModel ( IChatPresenter chatPresenter, NetRequestUtil.RequestListener requestListener ) {
                mChatPresenter = chatPresenter;
                mRequestListener= requestListener;
                param.put ( "key", UrlUtil.TURING_KEY );
        }

        @Override
        public void onRequestMessage ( String message ) {
                /*“key”: “APIKEY”,
                “info”: “今天天气怎么样”，
                “loc”：“北京市中关村”，
                “userid”：“12345678”*/
                param.put ( "info", EncodeString(message ));
                param.put ( "userid","12345678" );
                NetRequestUtil.getInstance ( ).getJson ( UrlUtil.URL_CHAT, param, mRequestListener );
        }
        public  String EncodeString(String str) {
                try {
                        return URLEncoder.encode(str, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                        return "";
                }

        }

}
