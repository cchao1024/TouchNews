package com.github.armstrong.touchnews.widget;

import android.content.Context;
import android.support.annotation.BoolRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.armstrong.touchnews.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by cchao on 2016/4/26.
 * E-mail:   cchao1024@163.com
 * Description: 一条聊天信息的View
 */
public class ChatItemView extends LinearLayout {

        @Bind ( R.id.iv_chat_item )
        ImageView mImageView;
        @Bind ( R.id.tv_chat_item )
        TextView mTextView;

        public ChatItemView ( Context context ) {
                this ( context, null );
        }

        public ChatItemView ( Context context, AttributeSet attrs ) {
                super ( context, attrs );
                 LayoutInflater.from ( context ).inflate ( R.layout.item_chat_view, this );
                ButterKnife.bind ( this, this );
        }

        /**
         * @param isRespond 设置自定义聊天内容布局是否为返回的聊天语句样式
         * @param message   content
         */
        public void setItem ( boolean isRespond, String message ) {
                if ( ! isRespond ) {
                        //是发出的信息则将头像放在右边
                        this.removeView ( mImageView );
                        this.addView ( mImageView);
                        mImageView.setImageResource ( R.drawable.icon_music );
                        mTextView.setBackgroundResource ( R.drawable.bg_chat_request );
                        this.setGravity ( Gravity.RIGHT );
                }
                mTextView.setText ( message );
        }
}
