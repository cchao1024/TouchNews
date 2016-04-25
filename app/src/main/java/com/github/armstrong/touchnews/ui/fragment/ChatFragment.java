package com.github.armstrong.touchnews.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.presenter.ChatPresenter;
import com.github.armstrong.touchnews.presenter.i.IChatPresenter;
import com.github.armstrong.touchnews.ui.fragment.base.BaseLazyFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 聊天机器人
 */
public class ChatFragment extends BaseLazyFragment {

        @Bind ( R.id.chat_editmessage )
        EditText mEditText;
        @Bind ( R.id.chat_send )
        Button mSendButton;
        View mRootView;
        IChatPresenter mChatPresenter;

        @Override
        public View onCreateView ( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState ) {
                mRootView=inflater.inflate ( R.layout.fragment_chat,null );
                ButterKnife.bind ( this,mRootView );
                return mRootView;

        }

        @Override
        public void onViewCreated ( View view,  Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
        }

        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mChatPresenter=new ChatPresenter ();
                mChatPresenter.onSendMessage ("你好");
        }
}
