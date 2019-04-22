package com.github.cchao.touchnews.presenter;

import com.android.volley.VolleyError;
import com.apkfuns.logutils.LogUtils;
import com.github.cchao.touchnews.contract.ChatContract;
import com.github.cchao.touchnews.util.Keys;
import com.github.cchao.touchnews.util.NetRequestUtil;
import com.github.cchao.touchnews.util.UrlUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 聊天机器人Presenter
 */
public class ChatPresenter implements ChatContract.Presenter {
    Map<String, String> param;
    ChatContract.View mView;

    public ChatPresenter(ChatContract.View view) {
        mView = view;
        param = new HashMap<>();
        param.put(Keys.KEY, UrlUtil.TURING_KEY);
    }

    @Override
    public void onSendMessage(String message) {
        onRequestMessage(message);
    }

    private void onRequestMessage(String message) {
                /*“key”: “APIKEY”,
                “info”: “今天天气怎么样”，
                “loc”：“北京市中关村”，
                “userid”：“12345678”*/

        param.put(Keys.INFO, EncodeString(message));
        param.put("userid", "12345678");
        NetRequestUtil.getInstance().getJson(UrlUtil.URL_CHAT, param, new NetRequestUtil.RequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                LogUtils.i(response);
                try {
                    if (response.getString("code").equals("100000")) {
                        mView.onReceiveRespond(response.getString("text"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError error) {
            }
        });
    }

    private String EncodeString(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }

    }
}
