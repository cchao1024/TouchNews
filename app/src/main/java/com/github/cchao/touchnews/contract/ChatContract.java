package com.github.cchao.touchnews.contract;

/**
 * Created by cchao on 2016/8/29.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public interface ChatContract {
        interface View {
                void onReceiveRespond ( String message );
        }
        interface Presenter {

                void onSendMessage ( String message );

        }

}
