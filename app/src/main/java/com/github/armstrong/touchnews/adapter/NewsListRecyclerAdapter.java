package com.github.armstrong.touchnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.armstrong.touchnews.R;
import com.github.armstrong.touchnews.javaBean.news.Contentlist;
import com.github.armstrong.touchnews.ui.activity.HomeActivity;
import com.github.armstrong.touchnews.ui.activity.NewsDetailActivity;
import com.github.armstrong.touchnews.util.ImageUtil;

import java.util.List;

/**
 * Created by cchao on 2016/4/5.
 * E-mail:   cchao1024@163.com
 * Description:
 */
public class NewsListRecyclerAdapter extends RecyclerView.Adapter< RecyclerView.ViewHolder > {
        private static final int TYPE_ITEM = 0;
        private static final int TYPE_FOOTER = 1;
        public Context mContext;
        public List< Contentlist > mData;
        public LayoutInflater mLayoutInflater;

//        private View.OnClickListener mOnClickListener;

        public NewsListRecyclerAdapter ( Context context, List< Contentlist > data ) {
                mContext = context;
                mData = data;
                mLayoutInflater = LayoutInflater.from ( context );
        }

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
                        View view = LayoutInflater.from ( parent.getContext ( ) ).inflate ( R.layout.list_view_footer, parent, false );
                        return new RecyclerView.ViewHolder ( view){
                                @Override
                                public String toString ( ) {
                                        return super.toString ( );
                                }
                        };
                }
        }

        @Override
        public void onBindViewHolder ( RecyclerView.ViewHolder holder, int position ) {
                if(holder instanceof MViewHolder) {
                        Contentlist contentEntity = mData.get ( position );
                        ((MViewHolder)holder).mTitle.setText ( contentEntity.getTitle ( ) );
                        ((MViewHolder)holder).mDescription.setText ( contentEntity.getDesc ( ) );
                        ImageUtil.displayImage ( mContext, contentEntity.getImageurls ( ).get ( 0 ).getUrl ( ), ((MViewHolder)holder).mImage );
                }
        }

        @Override
        public int getItemCount ( ) {
                return mData.size ( )+1;
        }

        public class MViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
                public TextView mTitle;
                public TextView mDescription;
                public ImageView mImage;

                public MViewHolder ( View view ) {
                        super ( view );
                        mTitle = ( TextView ) view.findViewById ( R.id.tv_title );
                        mDescription = ( TextView ) view.findViewById ( R.id.tv_description );
                        mImage = ( ImageView ) view.findViewById ( R.id.imageView );
                        view.setOnClickListener ( this );
                }

                @Override
                public void onClick ( View v ) {
                        Intent intent = new Intent ( mContext, NewsDetailActivity.class );
                        intent.putExtra ( "contentList", mData.get ( this.getLayoutPosition ( ) ) );
//                     View transitionView = view.findViewById(R.id.ivNews);
                        ActivityOptionsCompat options =
                                ActivityOptionsCompat.makeSceneTransitionAnimation ( ( HomeActivity ) mContext,
                                        mImage, mContext.getString ( R.string.transition__img ) );

                        ActivityCompat.startActivity ( ( HomeActivity ) mContext, intent, options.toBundle ( ) );

                }
        }
}
