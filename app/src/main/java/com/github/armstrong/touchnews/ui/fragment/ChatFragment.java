package com.github.armstrong.touchnews.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.presenter.ChatPresenter;
import com.github.armstrong.touchnews.presenter.i.IChatPresenter;
import com.github.armstrong.touchnews.ui.fragment.base.BaseLazyFragment;
import com.github.armstrong.touchnews.view.IChatView;
import com.github.armstrong.touchnews.widget.ChatItemView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by cchao on 2016/4/25.
 * E-mail:   cchao1024@163.com
 * Description: 聊天机器人
 */
public class ChatFragment extends BaseLazyFragment implements IChatView,View.OnLayoutChangeListener {
        @Bind ( R.id.scroll_chat )
        ScrollView mScrollView;
        @Bind ( R.id.lyt_chat )
        LinearLayout mLinearContent;
        @Bind ( R.id.chat_edit )
        EditText mEditText;
        @Bind ( R.id.chat_send )
        TextView mSend;
        View mRootView;
        IChatPresenter mChatPresenter;

        @Override
        public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
                super.onCreateView ( inflater, container, savedInstanceState );
                mRootView = inflater.inflate ( R.layout.fragment_chat, null );
                ButterKnife.bind ( this, mRootView );
                initiation ( );
                return mRootView;
        }

        private void initiation ( ) {
                mEditText.addTextChangedListener ( new TextWatcher ( ) {
                        @Override
                        public void beforeTextChanged ( CharSequence s, int start, int count, int after ) {

                        }

                        @Override
                        public void onTextChanged ( CharSequence s, int start, int before, int count ) {
                                if ( count != 0 ) {
                                        mSend.setBackgroundResource ( R.drawable.bg_chat_send_normal );
                                        mSend.setTextColor ( getResources ( ).getColor ( R.color.textWhite ) );
                                } else {
                                        mSend.setBackgroundResource ( R.drawable.bg_chat_send_disabled );
                                        mSend.setTextColor ( getResources ( ).getColor ( R.color.textPrimaryGrey ) );
                                }
                        }

                        @Override
                        public void afterTextChanged ( Editable s ) {

                        }
                } );
                mRootView.getViewTreeObserver ( ).addOnGlobalLayoutListener ( new ViewTreeObserver.OnGlobalLayoutListener ( ) {
                                                                                      @Override
                                                                                      public void onGlobalLayout ( ) {
                                                                                              int heightDiff = mRootView.getHeight ( ) -
                                                                                                      mRootView.getHeight ( );
                                                                                              if ( heightDiff > 100 ) {
                                                                                                      // 如果高度差超过100像素，就很有可能是有软键盘...
                                                                                                      scrollToBottom ( );
                                                                                              } else {
                                                                                              }
                                                                                      }
                                                                              }

                );
        }

        private void scrollToBottom ( ) {
                new Handler ( ).post ( new Runnable ( ) {
                        @Override
                        public void run ( ) {
                                mScrollView.fullScroll ( ScrollView.FOCUS_DOWN );
                        }
                } );
        }

        @Override
        public void onViewCreated ( View view, Bundle savedInstanceState ) {
                super.onViewCreated ( view, savedInstanceState );
        }

        @Override
        public void onFirstUserVisible ( ) {
                super.onFirstUserVisible ( );
                mChatPresenter = new ChatPresenter ( this );
                mChatPresenter.onSendMessage ( "现在时间" );
        }

        @OnClick ( R.id.chat_send )
        public void click ( View v ) {
                switch ( v.getId ( ) ) {
                        case R.id.chat_send:
                                mSend.setBackgroundResource ( R.color.textPrimaryGrey );
                                String message = mEditText.getText ( ).toString ( );
                                mEditText.setText ( "" );
                                if ( ! TextUtils.isEmpty ( message ) ) {
                                        /*TextView textView=new TextView (  mContext);
                                        textView.setText ( message );
                                        textView.setCompoundDrawablesWithIntrinsicBounds ( 0,0,R.drawable.icon_music,0);
                                        textView.setBackgroundResource ( R.drawable.icon_chat_request_0 );*/
                                        ChatItemView itemView = new ChatItemView ( mContext );
                                        itemView.setItem ( false, message );
                                        mLinearContent.addView ( itemView, new ViewGroup.LayoutParams ( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                                                .LayoutParams.WRAP_CONTENT ) );
                                        mChatPresenter.onSendMessage ( message );

                                }
                                break;
                }
        }

        @Override
        public void onReceiveRespond ( String message ) {
               /* TextView textView=new TextView (  mContext);
                textView.setText ( message );
                textView.setCompoundDrawablesWithIntrinsicBounds ( 0,0,R.drawable.icon_music,0);
                textView.setBackgroundResource ( R.drawable.bg_chat_respond);*/
                ChatItemView itemView = new ChatItemView ( mContext );
                itemView.setItem ( true, message );
                mLinearContent.addView ( itemView, new ViewGroup.LayoutParams ( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT ) );
                scrollToBottom ( );
        }
        @Override
        public void onLayoutChange(View v, int left, int top, int right,
                                   int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                int keyHeight = 10;
                //old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值

//      System.out.println(oldLeft + " " + oldTop +" " + oldRight + " " + oldBottom);
//      System.out.println(left + " " + top +" " + right + " " + bottom);


                //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
                if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){

//                        Toast.makeText(MainActivity.this, "监听到软键盘弹起...", Toast.LENGTH_SHORT).show();
                        scrollToBottom ();

                }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){

//                        Toast.makeText(MainActivity.this, "监听到软件盘关闭...", Toast.LENGTH_SHORT).show();

                }

        }
}
