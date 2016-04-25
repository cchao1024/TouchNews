package com.github.armstrong.touchnews.model;

import com.github.armstrong.touchnews.model.i.IChatModel;
import com.github.armstrong.touchnews.presenter.i.IChatPresenter;
import com.github.armstrong.touchnews.util.NetRequestUtil;
import com.github.armstrong.touchnews.util.UriUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class ChatModel implements IChatModel {
        Map< String, String > param = new HashMap<> ( );
        Map< String, String > headers = new HashMap<> ( );
        IChatPresenter mChatPresenter;
        NetRequestUtil.RequestListener mRequestListener;

        public ChatModel ( IChatPresenter chatPresenter, NetRequestUtil.RequestListener requestListener ) {
                mChatPresenter = chatPresenter;
        }

        @Override
        public void onRequestMessage ( String message ) {
                NetRequestUtil.getInstance ( ).getJsonWithHeaders ( UriUtil.URL_CHAT, param, headers, mRequestListener );
        }
}
