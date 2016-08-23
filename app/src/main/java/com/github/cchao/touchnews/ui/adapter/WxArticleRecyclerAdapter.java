package com.github.cchao.touchnews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.cchao.touchnews.R;
import com.github.cchao.touchnews.contants.Keys;
import com.github.cchao.touchnews.javaBean.WxArticle.ShowapiResBodyBean.PagebeanBean.ContentlistBean;
import com.github.cchao.touchnews.ui.activity.WebActivity;
import com.github.cchao.touchnews.util.ImageUtil;

import java.util.List;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description: 微信精选Adapter
 */
public class WxArticleRecyclerAdapter extends RecyclerView.Adapter< RecyclerView.ViewHolder > {
        private static final int TYPE_ITEM = 0;
        private static final int TYPE_FOOTER = 1;
        public Context mContext;
        public List< ContentlistBean > mData;
        public LayoutInflater mLayoutInflater;

//        private View.OnClickListener mOnClickListener;

        public WxArticleRecyclerAdapter ( Context context, List< ContentlistBean > data ) {
                mContext = context;
                mData = data;
                mLayoutInflater = LayoutInflater.from ( context );
        }

        /**
         * 获取当前滑动到的view 类型
         *
         * @param position 当前滑动位置
         * @return 内容或者footView
         */
        @Override
        public int getItemViewType ( int position ) {
                if ( position + 1 == getItemCount ( ) ) {
                        return TYPE_FOOTER;
                } else {
                        return TYPE_ITEM;
                }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder ( ViewGroup parent, int viewType ) {
                if ( viewType == TYPE_ITEM ) {
                        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.item_news, parent, false );
                        return new MViewHolder ( view );
                } else {
                        //最后放置一个加载更多的 footView
                        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.list_view_footer, parent, false );
                        return new RecyclerView.ViewHolder ( view ) { };
                }
        }

        @Override
        public void onBindViewHolder ( RecyclerView.ViewHolder holder, int position ) {
                if ( holder instanceof MViewHolder ) {
                        final ContentlistBean contentEntity = mData.get ( position );
                        ( ( MViewHolder ) holder ).mTitle.setText ( contentEntity.getTypeName ( ) );
                        ( ( MViewHolder ) holder ).mDescription.setText ( contentEntity.getTitle ( ) );
                        ( ( MViewHolder ) holder ).mViewRoot.setOnClickListener ( new View.OnClickListener ( ) {
                                @Override
                                public void onClick ( View v ) {
                                        //跳转显示详情
                                        Intent intent = new Intent ( mContext, WebActivity.class );
                                        intent.putExtra ( Keys.URL, contentEntity.getUrl ( ) );
                                        mContext.startActivity ( intent );
                                }
                        } );
                        ImageUtil.displayImage ( mContext, contentEntity.getContentImg ( ), ( ( MViewHolder ) holder ).mImage );

                }
        }

        @Override
        public int getItemCount ( ) {
                //多一个放加载更多
                return mData.size ( ) + 1;
        }

        public class MViewHolder extends RecyclerView.ViewHolder {
                public TextView mTitle;//标题
                public TextView mDescription;//描述内容
                public ImageView mImage;//预览图
                public View mViewRoot;

                public MViewHolder ( View view ) {
                        super ( view );
                        mViewRoot = view.findViewById ( R.id.layout_content );
                        mTitle = ( TextView ) view.findViewById ( R.id.tv_title );
                        mDescription = ( TextView ) view.findViewById ( R.id.tv_description );
                        mImage = ( ImageView ) view.findViewById ( R.id.imageView );
                }
        }
}
